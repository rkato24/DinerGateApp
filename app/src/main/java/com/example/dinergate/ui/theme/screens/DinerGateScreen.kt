package com.example.dinergate.ui.theme.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import android.location.Location
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.dinergate.network.GpsLocation

/*

DinerGateApp : MainActivityからコールされる関数。
各種画面へのナビゲートおよびvielModelによる変数の保持や位置情報の取得を行う。

 */

enum class DinerGateScreen() {
    Start,
    SearchResult,
    ShopDetail
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DinerGateApp(
    viewModel: DinerGateViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
){
    // Get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()
    // Get the name of the current screen
    val currentScreen = DinerGateScreen.valueOf(
        backStackEntry?.destination?.route ?: DinerGateScreen.Start.name
    )
    val gpsLocation = GpsLocation()
    val currentLocation: Location? = gpsLocation.getCurrentLocation()
    //不具合：Gpsから現在位置を取得できておらず、currentLocationがnullのままになっている
    //一時的な対応として、GpsLocationで得られる緯度・経度の代わりに
    //ハードコードされた値をviewModelに渡す
    //本来は下の2行のように書きたい
    //viewModel.setCurrentLatitude(currentLocation.getLatitude())
    //viewModel.setCurrentLongitude(currentLocation.getLongitude())
    viewModel.setCurrentLatitude(34.8424)
    viewModel.setCurrentLongitude(135.7895)

    Scaffold(
        topBar = {
            DinerGateAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }

    ) {innerPadding ->
        val uiState by viewModel.uiState.collectAsState()
        val apiViewModel: HotPepperApiViewModel = viewModel()

        NavHost(
            navController = navController,
            startDestination = DinerGateScreen.Start.name,
            modifier = Modifier.padding(innerPadding)
        ) {

            composable(route = DinerGateScreen.Start.name) {
                when (apiViewModel.apiUiState) {
                    is ApiUiState.Success -> viewModel.setHotPepperApiResult((apiViewModel.apiUiState as ApiUiState.Success).data)
                    is ApiUiState.Loading -> {}
                    is ApiUiState.Error -> {
                        Text(text = "失敗しました")
                    }
                }
                StartScreen(
                    onSelectionChanged = { viewModel.setRangeId(it) },
                    onSearchButtonClicked = {
                        apiViewModel.getHotPepperApiResult(
                            lat = uiState.currentLatitude,
                            lng = uiState.currentLongitude,
                            range = uiState.rangeId )
                        navController.navigate(DinerGateScreen.SearchResult.name)
                    },
                )
            }

            composable(route = DinerGateScreen.SearchResult.name) {
                SearchResultScreen(
                    HPApiSearchResult = uiState.hotPepperApiResult,
                    lat = uiState.currentLatitude,
                    lng = uiState.currentLongitude,
                    onCardClicked = {
                        viewModel.setShopFocusIndex(it)
                        navController.navigate(DinerGateScreen.ShopDetail.name)
                    }
                )
            }

            composable(route = DinerGateScreen.ShopDetail.name) {
                ShopDetailScreen(
                    details = uiState.hotPepperApiResult.results.shop[uiState.shopFocusIndex],
                    lat = uiState.currentLatitude,
                    lng = uiState.currentLongitude
                )
            }
        }
    }
}

@Composable
fun DinerGateAppBar(
    currentScreen: DinerGateScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(text = "DinerGate - レストランを検索") },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = null
                    )
                }
            }
        }
    )
}
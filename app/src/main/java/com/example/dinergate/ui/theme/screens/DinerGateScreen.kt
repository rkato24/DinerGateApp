package com.example.dinergate.ui.theme.screens

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.dinergate.dummyResult

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
                    else -> {}
                }
                StartScreen(
                    hotPepperApiResult = uiState.hotPepperApiResult,
                    onSearchButtonClicked = {

                        navController.navigate(DinerGateScreen.SearchResult.name)
                    }
                )
            }

            composable(route = DinerGateScreen.SearchResult.name) {
                SearchResultScreen(
                    HPApiSearchResult = uiState.hotPepperApiResult,
                    onCardClicked = {
                        viewModel.setShopFocusIndex(it)
                        navController.navigate(DinerGateScreen.ShopDetail.name)
                    }
                )
            }

            composable(route = DinerGateScreen.ShopDetail.name) {
                ShopDetailScreen(
                    details = uiState.hotPepperApiResult.results.shop[uiState.shopFocusIndex]
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
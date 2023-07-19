package com.example.dinergate.ui.theme.screens

import android.widget.ArrayAdapter
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.dinergate.R
import com.example.dinergate.model.HotPepperApiResult
import com.example.dinergate.model.Shop
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import android.app.Application
import android.content.Context
import android.location.Location
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.sp
import com.example.dinergate.dummyResult
import com.example.dinergate.network.GpsLocation
import com.example.dinergate.network.stateToQuery
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationServices

@Composable
fun StartScreen(
    hotPepperApiResult: HotPepperApiResult,
    onSearchButtonClicked: (HotPepperApiResult) -> Unit,
    onSelectionChanged: (Int) -> Unit = {},
    Id: Int,
    modifier: Modifier = Modifier.padding(32.dp)
) {
    val gpsLocation = GpsLocation()

    var selectedValue by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = "近くのレストランを検索します")
        Text(text = "現在地からの検索半径を指定してください")
        //Spacer(modifier = Modifier.height(16.dp ))
        val rangeID = listOf("300m", "500m", "1000m", "2000m", "3000m")
        rangeID.forEachIndexed { index, item ->
            Row(
                modifier = Modifier.selectable(
                    selected = selectedValue == item,
                    onClick = {
                        selectedValue = item
                        onSelectionChanged(index + 1)
                    }
                ),
                verticalAlignment = Alignment.CenterVertically
            ){
                RadioButton(
                    selected = selectedValue == item,
                    onClick = {
                        selectedValue = item
                        onSelectionChanged(index + 1)
                    }
                )
                Text(item)
            }
        }
        Text(text = Id.toString())
        SearchButton(
            onClick = { onSearchButtonClicked(hotPepperApiResult) }
        )
        //Text(text = gpsLocation.getGpsLocation().toString())
    }

}

/**
 *
 * @OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchKeywordField() {
OutlinedTextField (
value = "",
onValueChange = {}
)
}


val adapter = ArrayAdapter<CharSequence>(
this,
R.array.rangeSpinnerItems,
android.R.layout.simple_spinner_item
)
 *
 *
 *
 *
 */

@Composable
fun SearchButton(

    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier.widthIn(min = 250.dp)
    ) {
        Text(text = "検索")
    }
}


@Preview(showBackground = true)
@Composable
fun StartScreenPreview(){
    StartScreen(

        hotPepperApiResult = dummyResult,

        onSearchButtonClicked = {},
        onSelectionChanged = {},
        Id = 3,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    )

}
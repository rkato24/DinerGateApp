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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.sp
import com.example.dinergate.network.GpsLocation
import com.example.dinergate.network.stateToQuery
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationServices

@Composable
fun StartScreen(
    apiUiState: String,
    onSearchButtonClicked: () -> Unit,
    modifier: Modifier = Modifier.padding(32.dp)
) {
    val gpsLocation = GpsLocation()

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = "近くのレストランを検索します")
        //Spacer(modifier = Modifier.height(16.dp ))
        SearchButton(
            onClick = { onSearchButtonClicked() }
        )
        //Text(text = gpsLocation.getGpsLocation().toString())
        Text(text = apiUiState)
        Text(text = stateToQuery(
            lat = 0.0,
            lng = 0.0,
            range = 5
        ))
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

        apiUiState = "aaa",

        onSearchButtonClicked = {},
        modifier = Modifier.fillMaxSize().padding(16.dp)
    )

}
package com.example.dinergate.ui.theme.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.dinergate.R
import com.example.dinergate.dummyResult
import com.example.dinergate.model.HotPepperApiResult
import com.example.dinergate.model.Shop
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

@Composable
fun SearchResultScreen(
    HPApiSearchResult: HotPepperApiResult,
    onCardClicked: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        items(HPApiSearchResult.results.shop.size) { index ->
            SearchResultCard(
                detail = HPApiSearchResult.results.shop[index]
            )
            Text(text = "詳しく見る",
                modifier = Modifier.clickable { onCardClicked (index) })
            Divider(thickness = dimensionResource(R.dimen.thickness_divider))
        }
    }

}

@Composable
fun SearchResultCard(
    detail: Shop
) {
    Row(
        modifier = Modifier
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(detail.photo.mobile.s)
                .build(),
            contentDescription = stringResource(R.string.shopPhoto),
            modifier = Modifier
                .size(100.dp)
                .padding(2.dp)
        )
        Column(){
            Text(text = detail.name)
            Text(text = detail.access)
        }
    }
}

@Preview
@Composable
fun SearchResultCardPreview(){
    SearchResultCard(detail = dummyResult.results.shop[0])
}

@Preview
@Composable
fun SearchResultPreview(){
    SearchResultScreen(HPApiSearchResult = dummyResult, onCardClicked = {})
}
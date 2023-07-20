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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.dinergate.R
import com.example.dinergate.dummyResult
import com.example.dinergate.model.HotPepperApiResult
import com.example.dinergate.model.Shop
import com.example.dinergate.model.getDistanceBetween

/*

SearchResultScreen : 店舗検索結果を表示するコンポ―ザブル
グルメサーチAPIから得られたデータ(HPApiSearchResult)と、
緯度経度(lat, lng)を値として受け取り、
緯度経度から計算された現在地との距離を含む
店舗一覧とその情報を表示する。
<詳しく見る>テキストがクリックされたとき、onCardClicked関数にそのテキストに対応するインデックスを渡す。
これにより、HPApiSearchResult.results.shopの何番目がクリックされたかの情報をviewModelに渡し、
そのインデックスを元に対応する店舗の詳細画面を表示することができる。

 */

@Composable
fun SearchResultScreen(
    HPApiSearchResult: HotPepperApiResult,
    lat: Double,
    lng: Double,
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
                detail = HPApiSearchResult.results.shop[index],
                lat = lat,
                lng = lng
            )
            Row(

            ) {
                Text(
                    text = "<詳しく見る>",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable { onCardClicked (index) }
                )
            }
            Divider(thickness = dimensionResource(R.dimen.thickness_divider))
        }
    }

}

@Composable
fun SearchResultCard(
    detail: Shop,
    lat: Double,
    lng: Double
) {
    Row(
        modifier = Modifier
    ) {
        Box (
            modifier = Modifier
                .padding(2.dp)
                .clip(RoundedCornerShape(16.dp))
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
        }

        Column(){
            Text(
                text = detail.name,
                fontWeight = FontWeight.Bold
            )
            Text(text = detail.access)
            Text(text = "ここから約${getDistanceBetween(lat, lng, detail.lat, detail.lng)}m")
        }
    }
}

@Preview
@Composable
fun SearchResultCardPreview(){
    SearchResultCard(detail = dummyResult.results.shop[0], 0.0, 0.0)
}

@Preview
@Composable
fun SearchResultPreview(){
    SearchResultScreen(HPApiSearchResult = dummyResult, lat = 0.0, lng = 0.0, onCardClicked = {})
}


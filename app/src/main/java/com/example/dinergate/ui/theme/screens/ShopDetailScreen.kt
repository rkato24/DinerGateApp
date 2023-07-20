package com.example.dinergate.ui.theme.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.dinergate.R
import com.example.dinergate.dummyResult
import com.example.dinergate.model.Shop
import com.example.dinergate.model.getDistanceBetween

/*

ShopDetailScreen : 店舗詳細画面を表示するコンポ―ザブル
検索結果画面で選択された店舗の情報をもつShopデータクラスと、
現在地の緯度経度の値を受け取る。
緯度経度から計算された現在地との距離を含む
店舗に関する各種情報を表示する。

 */

@Composable
fun ShopDetailScreen(
    modifier: Modifier = Modifier,
    details: Shop,
    lat: Double = 34.8424,
    lng: Double = 135.7895
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        item{
            Text(
                text = details.name,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyLarge
            )
        }
        item{
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
            )
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(details.photo.mobile.l)
                    .build(),
                contentDescription = stringResource(R.string.shopPhoto),
                modifier = modifier
                    .size(400.dp)
                    .padding(4.dp)
                    .fillMaxWidth()
            )
        }
        item{
            Text(
                text = details.genre.name,
                fontSize = 18.sp
            )
        }
        item{
            Row(
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = details.genre.catch,
                    fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
                )
            }
        }
        item{
            Divider(thickness = dimensionResource(R.dimen.thickness_divider))
        }
        item{
            Text(text = "住所 : ${details.address}")
        }
        item{
            Divider(thickness = dimensionResource(R.dimen.thickness_divider))
        }
        item{
            Text(text = "アクセス : ${details.access}")
        }
        item{
            Divider(thickness = dimensionResource(R.dimen.thickness_divider))
        }
        item{
            Text(text = "お店までの距離 : 約${getDistanceBetween(lat, lng, details.lat, details.lng)}m")
        }
        item{
            Divider(thickness = dimensionResource(R.dimen.thickness_divider))
        }
        item{
            Text(text = "予算 : ${details.budget.average}")
        }
        /*
        budgetMemoフィールドは空のString("")である場合があるので、
        その場合はハイフンを表示する。
         */
        item{
            Text(text = "料金備考 : ${
                if (details.budgetMemo != "") details.budgetMemo
                else "-"
            }")
        }
        item{
            Divider(thickness = dimensionResource(R.dimen.thickness_divider))
        }
        item{
            Text(text = "営業日時 : ${details.open}")
        }
        item{
            Text(text = "定休日 : ${details.close}")
        }
        item{
            Divider(thickness = dimensionResource(R.dimen.thickness_divider))
        }
        item{
            Text(text = "総席数 : ${details.capacity}")
        }
    }
}

@Preview
@Composable
fun ShopDetailPreview(){
    ShopDetailScreen(details = dummyResult.results.shop[0], lat = 0.0, lng = 0.0)
}
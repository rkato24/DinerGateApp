package com.example.dinergate.ui.theme.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

/*

StartScreen : アプリ開始時の検索範囲を指定する画面コンポーザブル
ラジオボタンで検索範囲を指定させたのち、指定された検索範囲に対応するインデックスをviewModelに渡す。
検索ボタンが押されると、指定された検索範囲と取得された緯度経度に基づいてAPIコールを行い、
結果をもとに検索結果画面(SearchResultScreen)に遷移する。

 */

@Composable
fun StartScreen(
    modifier: Modifier = Modifier,
    onSearchButtonClicked: () -> Unit,
    onSelectionChanged: (Int) -> Unit = {}
) {

    var selectedValue by rememberSaveable { mutableStateOf("") }
    val rangeID = listOf("300m", "500m", "1000m", "2000m", "3000m")

    Column(
        modifier = modifier.padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text(
            text = "Welcome to DinerGate",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic
            )
        Spacer(Modifier.height(16.dp))
        Text(text = "近くのレストランを検索します")
        Text(text = "現在地からの検索半径を指定してください")
        Spacer(Modifier.height(16.dp ))
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
        Spacer(Modifier.height(16.dp ))
        SearchButton(
            onClick = { onSearchButtonClicked() }
        )
    }

}

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


        onSearchButtonClicked = {},
        onSelectionChanged = {},
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    )

}
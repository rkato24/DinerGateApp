package com.example.dinergate.data

import com.example.dinergate.dummyResult
import com.example.dinergate.model.HotPepperApiResult

/*

アプリ全体にわたって保持しておく状態のデータクラス

val hotPepperApiResult: HotPepperApiResult = dummyResult
    グルメサーチAPIから得られたデータをHotPepperApiResultデータクラス形式に変換したもの
    ダミーデータで初期化→setHotPepperApiResultで更新される
val shopFocusIndex: Int = 0,
    検索結果画面で、表示されるList<Shop>のうち何番目のものが選択されたかのインデックスを保持する
    検索結果画面で詳細画面に移動する操作が行われるたびにsetShopFocusIndexで更新される
val currentLatitude: Double = 34.8424,
val currentLongitude: Double = 135.7895,
    それぞれGpsLocationで得られた端末の緯度・経度を保持する
    setCurrentLatitude, setCurrentLongitudeで更新可能
val rangeId: Int = 3
    スタート画面で、指定された検索半径に対応するインデックスを保持する
    対応関係は
    ・1→300m
    ・2→500m
    ・3→1000m
    ・4→2000m
    ・5→3000m
    初期値は3で、ラジオボタンが選択されていない状態で検索ボタンが押されると
    半径1000mを選択して計算したこととなる
    （初期状態で1000mが選択されているようになるのが望ましい）

 */

data class DinerGateUiState (
    val hotPepperApiResult: HotPepperApiResult = dummyResult,
    val shopFocusIndex: Int = 0,
    val currentLatitude: Double = 0.0,
    val currentLongitude: Double = 0.0,
    val rangeId: Int = 3
)




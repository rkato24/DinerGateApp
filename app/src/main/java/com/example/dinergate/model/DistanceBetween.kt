package com.example.dinergate.model

import android.location.Location
import kotlin.math.roundToInt

/*

getDistanceBetween : 2点の緯度・経度を受け取り、少数第一位で四捨五入した2点の距離(m)を返す

 */

fun getDistanceBetween(
    latitude1: Double, longitude1: Double,
    latitude2: Double, longitude2: Double
): Int {
    val results = FloatArray(3)

    Location.distanceBetween(latitude1, longitude1, latitude2, longitude2, results)

    // 0を指定することで２点の距離を返す
    return results[0].roundToInt()
}
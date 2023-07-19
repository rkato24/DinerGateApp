package com.example.dinergate.data

import com.example.dinergate.model.HotPepperApiResult
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

data class DinerGateUiState (
    val hotPepperApiResult: HotPepperApiResult,
    val shopFocusIndex: Int = 0,
    //val searchResultPage: Int = 1
    //val currentLatitude: Double = 34.8424,
    val currentLatitude: Double = 34.00,
    val currentLongitude: Double = 135.7895,
    val rangeId: Int = 3
)




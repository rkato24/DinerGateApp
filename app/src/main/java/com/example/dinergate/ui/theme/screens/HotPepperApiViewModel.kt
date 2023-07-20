package com.example.dinergate.ui.theme.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dinergate.model.HotPepperApiResult
import com.example.dinergate.network.HotPepperApi
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface ApiUiState {
    data class Success(val data: HotPepperApiResult) : ApiUiState
    object Error : ApiUiState
    object Loading : ApiUiState
}

class HotPepperApiViewModel() : ViewModel() {

    var apiUiState: ApiUiState by mutableStateOf(ApiUiState.Loading)
        private set

    fun getHotPepperApiResult(
        lat: Double = 34.67,
        lng: Double = 135.5,
        range: Int = 5,
        order: Int = 4
    ) {
        viewModelScope.launch {
            apiUiState = try {
                val listResult = HotPepperApi.retrofitService.getData(
                    lat = lat,
                    lng = lng,
                    range = range
                )
                ApiUiState.Success(listResult)
            } catch (e: IOException) {
                ApiUiState.Error
            }
        }
    }
}

package com.example.dinergate.ui.theme.screens

import androidx.lifecycle.ViewModel
import com.example.dinergate.data.DinerGateUiState
import com.example.dinergate.model.HotPepperApiResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/*

アプリ全体にわたって値を保持するviewModelの定義
uiStateに値をセットするための各関数も定義

 */
class DinerGateViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(DinerGateUiState())
    val uiState: StateFlow<DinerGateUiState> = _uiState.asStateFlow()

    fun setHotPepperApiResult(ApiResult: HotPepperApiResult) {
        _uiState.update { currentState ->
            currentState.copy(
                hotPepperApiResult = ApiResult
            )
        }
    }

    fun setShopFocusIndex(index: Int) {
        _uiState.update { currentState ->
            currentState.copy(
                shopFocusIndex = index
            )
        }
    }

    fun setCurrentLatitude(lat: Double) {
        _uiState.update { currentState ->
            currentState.copy(
                currentLatitude = lat
            )
        }
    }

    fun setCurrentLongitude(lng: Double) {
        _uiState.update { currentState ->
            currentState.copy(
                currentLongitude = lng
            )
        }
    }

    fun setRangeId(id: Int) {
        _uiState.update { currentState ->
            currentState.copy(
                rangeId = id
            )
        }
    }

}
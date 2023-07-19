package com.example.dinergate.ui.theme.screens

import androidx.lifecycle.ViewModel
import com.example.dinergate.data.DinerGateUiState
import com.example.dinergate.dummyResult
import com.example.dinergate.model.HotPepperApiResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DinerGateViewModel: ViewModel() {


    private val _uiState = MutableStateFlow(DinerGateUiState(hotPepperApiResult = dummyResult))
    val uiState: StateFlow<DinerGateUiState> = _uiState.asStateFlow()

    fun setShopFocusIndex(index: Int) {
        _uiState.update { currentState ->
            currentState.copy(
                shopFocusIndex = index
            )

        }
    }

    fun setHotPepperApiResult(ApiResult: HotPepperApiResult) {
        _uiState.update { currentState ->
            currentState.copy(
                hotPepperApiResult = ApiResult
            )

        }
    }

}
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

class HotPepperApiViewModel : ViewModel() {
    /** The mutable State that stores the status of the most recent request */
    var apiUiState: ApiUiState by mutableStateOf(ApiUiState.Loading)
        private set

    /**
     * Call getMarsPhotos() on init so we can display status immediately.
     */
    init {
        getHotPepperApiResult()
    }

    /**
     * Gets Mars photos information from the Mars API Retrofit service and updates the
     * [MarsPhoto] [List] [MutableList].
     */
    fun getHotPepperApiResult() {
        viewModelScope.launch {
            apiUiState = try {
                val listResult = HotPepperApi.retrofitService.getData()
                ApiUiState.Success(listResult)
            } catch (e: IOException) {
                ApiUiState.Error
            }

        }
    }
}

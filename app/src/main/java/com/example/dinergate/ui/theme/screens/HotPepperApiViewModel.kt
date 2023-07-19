package com.example.dinergate.ui.theme.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dinergate.network.HotPepperApi
import kotlinx.coroutines.launch

class HotPepperApiViewModel : ViewModel() {
    /** The mutable State that stores the status of the most recent request */
    var ApiUiState: String by mutableStateOf("")
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
            val listResult = HotPepperApi.retrofitService.getData()
            ApiUiState = listResult
        }
    }
}

package com.example.dinergate.ui.theme.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

/**
 * UI state for the Home screen
 */
sealed interface HotPepperUiState {
    data class Success(val HotPepperApiResult: String) : HotPepperUiState
    object Error : HotPepperUiState
    object Loading : HotPepperUiState
}


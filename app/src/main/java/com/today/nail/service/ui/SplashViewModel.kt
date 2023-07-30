package com.today.nail.service.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration

@HiltViewModel
class SplashViewModel @Inject constructor() : ViewModel() {

    private val _canMoveToOnBoard = MutableStateFlow(false)
    val canMoveToOnBoard = _canMoveToOnBoard.asStateFlow()

    init {
        viewModelScope.launch {
            delay(1000)
            _canMoveToOnBoard.emit(true)
        }
    }
}
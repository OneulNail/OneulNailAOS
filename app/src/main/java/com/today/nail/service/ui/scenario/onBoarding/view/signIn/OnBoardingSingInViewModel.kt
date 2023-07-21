package com.today.nail.service.ui.scenario.onBoarding.view.signIn

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class OnBoardingSingInViewModel @Inject constructor(): ViewModel() {
    private val _userId = MutableStateFlow<String>("")
    val userId = _userId.asStateFlow()

    private val _password = MutableStateFlow<String>("")
    val password = _password.asStateFlow()

    fun updateId(value : String) {
        _userId.value = value
    }

    fun updatePassword(value : String) {
        _password.value = value
    }
}
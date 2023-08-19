package com.today.nail.service.ui.scenario.onBoarding.view.register

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class OnBoardingRegisterViewModel @Inject constructor() : ViewModel() {
    val nameFieldValue = MutableStateFlow("")
    val nickNameFieldValue = MutableStateFlow("")
    val passwordFieldValue = MutableStateFlow("")
    val passwordRecheckFieldValue = MutableStateFlow("")

    fun updateNameField(value : String) {
        nameFieldValue.value = value
    }
    fun updateNickNameField(value : String) {
        nickNameFieldValue.value = value
    }
    fun updatePasswordField(value : String) {
        passwordFieldValue.value = value
    }
    fun updatePasswordRecheckField(value : String) {
        passwordRecheckFieldValue.value = value
    }

}
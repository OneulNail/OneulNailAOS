package com.today.nail.service.ui.scenario.onBoarding.view.phoneVerify

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingPhoneVerifyViewModel @Inject constructor() : ViewModel() {
    private val _verifyCodeSendState = MutableStateFlow(false)
    val verifyCodeSendState = _verifyCodeSendState.asStateFlow()

    private val _canMoveToRegisterView = MutableStateFlow(false)
    val canMoveToRegisterView = _canMoveToRegisterView.asStateFlow()

    val verifyFieldValue = MutableStateFlow("")
    val phoneNumFieldValue = MutableStateFlow("")

    init {
        viewModelScope.launch {
            verifyFieldValue.collectLatest {
                _canMoveToRegisterView.value = it.length == 6
            }
        }
    }

    fun updatePhoneNumField(value : String) {
        if(value.length <= 11) {
            phoneNumFieldValue.value = value
        }
    }

    fun updateVerifyField(value : String) {
        if(value.length <= 6) {
            verifyFieldValue.value = value
        }
    }

    fun requestVerifyCode(
        onSuccess : () -> Unit,
        onFail : () -> Unit
    ) {
        if(phoneNumFieldValue.value.length == 11) {
            onSuccess()
            this._verifyCodeSendState.value = true
        } else {
            onFail()
        }
    }
}
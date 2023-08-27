package com.today.nail.service.ui.scenario.onBoarding.view.phoneVerify

import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.today.nail.service.ui.TopLevelViewModel
import com.today.nail.service.ui.scenario.onBoarding.view.register.OnBoardingRegisterViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingPhoneVerifyViewModel @Inject constructor(
    topLevelViewModel: TopLevelViewModel
) : ViewModel() {
    private val _verifyCodeSendState = MutableStateFlow(false)
    val verifyCodeSendState = _verifyCodeSendState.asStateFlow()

    private val _canMoveToRegisterView = MutableStateFlow(false)
    val canMoveToRegisterView = _canMoveToRegisterView.asStateFlow()

    val verifyFieldValue = MutableStateFlow("")
    private val phoneNumFieldValue = topLevelViewModel.phoneNumFieldValue

    init {
        viewModelScope.launch {
            verifyFieldValue.collectLatest {
                _canMoveToRegisterView.value = it.length == 6
            }
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
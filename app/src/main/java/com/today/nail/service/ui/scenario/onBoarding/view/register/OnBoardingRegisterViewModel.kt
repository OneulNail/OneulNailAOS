package com.today.nail.service.ui.scenario.onBoarding.view.register

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.today.nail.service.data.ServiceConnector
import com.today.nail.service.data.onBoard.repository.OnBoardingRepository
import com.today.nail.service.data.onBoard.repository.OnBoardingRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class OnBoardingRegisterViewModel @Inject constructor() : ViewModel() {
    val nameFieldValue = MutableStateFlow("")
    val nickNameFieldValue = MutableStateFlow("")
    val passwordFieldValue = MutableStateFlow("")
    val passwordRecheckFieldValue = MutableStateFlow("")
    val _phoneNum = MutableStateFlow("")
    private val _isCheckedAllAgree = mutableStateOf(false)
    val isCheckedAllAgree: State<Boolean> = _isCheckedAllAgree

    private val _isCheckedFirstAgree = mutableStateOf(false)
    val isCheckedFirstAgree: State<Boolean> = _isCheckedFirstAgree

    private val _isCheckedSecondAgree = mutableStateOf(false)
    val isCheckedSecondAgree: State<Boolean> = _isCheckedSecondAgree

    val onBoardingRepository: OnBoardingRepository = OnBoardingRepositoryImpl(ServiceConnector.onBoardService)
    private val _registerResult = MutableLiveData<Boolean>()

    fun updatePhoneNum(value: String) {
        _phoneNum.value = value
        Log.i("OnBoardingRegisterViewModel", "Phone number updated: $value")
    }

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
    fun toggleAllAgree() {
        _isCheckedAllAgree.value = !_isCheckedAllAgree.value
        _isCheckedFirstAgree.value = !_isCheckedFirstAgree.value
        _isCheckedSecondAgree.value = !_isCheckedSecondAgree.value

    }

    fun toggleFirstAgree() {
        _isCheckedFirstAgree.value = !_isCheckedFirstAgree.value
        if (_isCheckedAllAgree.value == true) {
            _isCheckedAllAgree.value = !_isCheckedAllAgree.value
        }
    }

    fun toggleSecondAgree() {
        _isCheckedSecondAgree.value = !_isCheckedSecondAgree.value
        if (_isCheckedAllAgree.value == true) {
            _isCheckedAllAgree.value = !_isCheckedAllAgree.value
        }
    }
suspend fun performRegister(): Boolean {
//        val mobileNo = _phoneNum.value
        val mobileNo = "01012341234"
        val password = passwordFieldValue.value
        val name = nameFieldValue.value
        val role = "USER" // 사용자 역할을 지정하는 로직이 필요

        try {
            // 회원가입 요청
            Log.d("회원가입", mobileNo)
            Log.i("회원가입", password)
            Log.i("회원가입", name)
            Log.i("회원가입", role)
            val registerResult = onBoardingRepository.userRegister(mobileNo, password, name, role)

            // 회원가입 성공 시의 처리
            if (registerResult.msg == "회원가입이 완료되었습니다.") {
                Log.i("회원가입", "성공")
                return true
            }
        } catch (e: Exception) {
            // 회원가입 실패 시의 처리
            Log.i("회원가입", "서버 응답: $e")
            return false
        }

        return false
    }


}
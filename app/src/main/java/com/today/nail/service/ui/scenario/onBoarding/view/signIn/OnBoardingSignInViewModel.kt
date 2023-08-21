package com.today.nail.service.ui.scenario.onBoarding.view.signIn

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.today.nail.service.data.ServiceConnector
import com.today.nail.service.data.onBoard.repository.OnBoardingRepository
import com.today.nail.service.data.onBoard.repository.OnBoardingRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class OnBoardingSignInViewModel @Inject constructor(): ViewModel() {
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

    val onBoardingRepository: OnBoardingRepository = OnBoardingRepositoryImpl(ServiceConnector.onBoardService)

    private val _loginResult = MutableLiveData<Boolean>()
    val loginResult: LiveData<Boolean> = _loginResult

    suspend fun performLogin() {
        val mobileNo = userId.value
        val password = password.value
        try {
            val loginResult = onBoardingRepository.userLogin(mobileNo, password)
            _loginResult.value = true
        } catch (e: Exception) {
            _loginResult.value = false
        }
    }
}
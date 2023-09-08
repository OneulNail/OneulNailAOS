package com.today.nail.service.ui.scenario.onBoarding.view.signIn

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import com.today.nail.service.data.ServiceConnector
import com.today.nail.service.data.onBoard.dto.login.UserLoginResDTO
import com.today.nail.service.data.onBoard.repository.OnBoardingRepository
import com.today.nail.service.data.onBoard.repository.OnBoardingRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ActivityContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@HiltViewModel
@SuppressLint("StaticFieldLeak")
class OnBoardingSignInViewModel @Inject constructor(
    //이게 맞는지..
    private val application: Application
): ViewModel() {
    private val applicationContext: Context = application.applicationContext

    private val _userId = MutableStateFlow<String>("")
    val userId = _userId.asStateFlow()

    private val _password = MutableStateFlow<String>("")
    val password = _password.asStateFlow()

    fun updateId(value: String) {
        _userId.value = value
    }

    fun updatePassword(value: String) {
        _password.value = value
    }

    val onBoardingRepository: OnBoardingRepository =
        OnBoardingRepositoryImpl(ServiceConnector.makeOnBoardService())

    /**
     *  자체 로그인
     */
    suspend fun performLogin(
        onSuccess: () -> Unit,
        onFail : () -> Unit
    ) {
        val mobileNo = userId.value
        val password = password.value
        runCatching {
            onBoardingRepository.userLogin(mobileNo, password)
        }.onSuccess { res ->
            Log.d("caz tst", "login response : $res")
            //res.token // 이런 데이터를 viewMOdel 에서 저장을 하든 뭐든 한다.
            if (res.message == "사용자 로그인 성공") {
                Log.d("자체 로그인", "성공")
            }
            //loginResult.token == ?
            onSuccess()
        }.onFailure {res ->
            Log.d("자체 로그인", "실패, 서버응답:$res")
            onFail()
        }
    }


    /**
     *  카카오 로그인
     */
    fun kakaoLogin(
        onSuccess: (result : String) -> Unit,
        onFail : () -> Unit,
    ) {
        viewModelScope.launch {
            handleKakaoLogin(
                onSuccess,
                onFail,
            )
        }
    }

    private suspend  fun handleKakaoLogin(
        onSuccess: (String) -> Unit,
        onFail: () -> Unit
    ): Boolean =

        suspendCoroutine<Boolean> { continuation ->
            // 로그인 조합 예제
             val TAG = "KakaoLogin"
             // 카카오계정으로 로그인 공통 callback 구성
             // 카카오톡으로 로그인 할 수 없어 카카오계정으로 로그인할 경우 사용됨
             val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
                 if (error != null) {
                     Log.e(TAG, "카카오계정으로 로그인 실패", error)
                     continuation.resume(false)
                     onFail()
                 } else if (token != null) {
                     Log.i(TAG, "카카오계정으로 로그인 성공 ${token.accessToken}")
                     continuation.resume(true)
                     onSuccess("나는 천재야")
                 }
             }

             // 카카오톡이 설치되어 있으면 카카오톡으로 로그인, 아니면 카카오계정으로 로그인
             if (UserApiClient.instance.isKakaoTalkLoginAvailable(applicationContext)) {
                 UserApiClient.instance.loginWithKakaoTalk(applicationContext) { token, error ->
                     if (error != null) {
                         Log.e(TAG, "카카오톡으로 로그인 실패", error)

                         // 사용자가 카카오톡 설치 후 디바이스 권한 요청 화면에서 로그인을 취소한 경우,
                         // 의도적인 로그인 취소로 보고 카카오계정으로 로그인 시도 없이 로그인 취소로 처리 (예: 뒤로 가기)
                         if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                             return@loginWithKakaoTalk
                         }

                         // 카카오톡에 연결된 카카오계정이 없는 경우, 카카오계정으로 로그인 시도
                         UserApiClient.instance.loginWithKakaoAccount(applicationContext, callback = callback)
                     } else if (token != null) {
                         Log.i(TAG, "카카오톡으로 로그인 성공 ${token.accessToken}")
                     }
                 }
             }
             else {
                 UserApiClient.instance.loginWithKakaoAccount(applicationContext, callback = callback)
             }
        }
}
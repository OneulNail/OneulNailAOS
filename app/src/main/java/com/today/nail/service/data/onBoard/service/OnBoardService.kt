package com.today.nail.service.data.onBoard.service

import com.google.gson.annotations.SerializedName
import com.today.nail.service.data.onBoard.dto.login.UserLoginReqDTO
import com.today.nail.service.data.onBoard.dto.login.UserLoginResDTO
import com.today.nail.service.data.onBoard.dto.register.UserRegisterReqDTO
import com.today.nail.service.data.onBoard.dto.register.UserRegisterResDTO
import com.today.nail.service.data.onBoard.dto.social.UserSocialInfoResDTO
import com.today.nail.service.data.onBoard.dto.social.UserSocialLoginResDTO
import com.today.nail.service.data.onBoard.dto.styleTaste.UserVerifyResDTO
import com.today.nail.service.data.onBoard.dto.verify.UserStyleTasteReqDTO
import com.today.nail.service.data.onBoard.dto.verify.UserStyleTasteResDTO
import retrofit2.http.Body
import retrofit2.http.POST

interface OnBoardService {
    /**
     *  자체 로그인
     */
    @POST("user/login")
    suspend fun postUserLogin(
        @Body requestBody : UserLoginReqDTO
    ) : UserLoginResDTO

    /**
     * 자체 회원가입
     */
    @POST("user/register")
    suspend fun postUserRegister(
        @Body requsetBody : UserRegisterReqDTO
    ) : UserRegisterResDTO

    /**
     * 소셜 로그인
     */
    @POST("user/social/login")
    suspend fun postUserSocialLogin() : UserSocialLoginResDTO

    /**
     * 소셜 로그인 추가 정보
     */
    @POST("user/social/login/info")
    suspend fun postUserSocialInfo() : UserSocialInfoResDTO

    /**
     * 인증번호
     */
    @POST("user/verify")
    suspend fun postUserVerify() : UserVerifyResDTO

    /**
     * 선호 스타일 등록
     */
    @POST("user/style/taste")
    suspend fun postUserStyleTaste(
        @Body reqestBody : UserStyleTasteReqDTO
    ) : UserStyleTasteResDTO
}
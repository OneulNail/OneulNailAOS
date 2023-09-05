package com.today.nail.service.data.onBoard.repository

import com.today.nail.service.data.onBoard.dto.login.UserLoginResDTO
import com.today.nail.service.data.onBoard.dto.register.UserRegisterResDTO
import com.today.nail.service.data.onBoard.dto.social.UserSocialLoginResDTO
import com.today.nail.service.data.onBoard.dto.styleTaste.UserVerifyResDTO
import com.today.nail.service.data.onBoard.dto.verify.UserStyleTasteResDTO
import com.today.nail.service.data.onBoard.service.OnBoardService

interface OnBoardingRepository {
    suspend fun userLogin(
        mobileNo : String,
        password : String
    ) : UserLoginResDTO

    suspend fun userRegister(
        //email 추가
        email: String,
        mobileNo: String,
        password: String,
        name : String,
        role : String
    ) : UserRegisterResDTO

    suspend fun userSocialLogin() : UserSocialLoginResDTO

//    suspend fun userSocialInfo() : UserSocialInfoResDTO

    suspend fun userVerify() : UserVerifyResDTO

    suspend fun userStyleTaste(
        styleFirst : String,
        styleSecond : String,
        styleThird : String
    ) : UserStyleTasteResDTO
}
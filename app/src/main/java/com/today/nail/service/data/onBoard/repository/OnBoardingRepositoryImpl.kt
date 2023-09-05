package com.today.nail.service.data.onBoard.repository

import com.today.nail.service.data.ServiceConnector
import com.today.nail.service.data.onBoard.dto.login.UserLoginReqDTO
import com.today.nail.service.data.onBoard.dto.login.UserLoginResDTO
import com.today.nail.service.data.onBoard.dto.register.UserRegisterReqDTO
import com.today.nail.service.data.onBoard.dto.register.UserRegisterResDTO
import com.today.nail.service.data.onBoard.dto.social.UserSocialLoginResDTO
import com.today.nail.service.data.onBoard.dto.styleTaste.UserVerifyResDTO
import com.today.nail.service.data.onBoard.dto.verify.UserStyleTasteReqDTO
import com.today.nail.service.data.onBoard.dto.verify.UserStyleTasteResDTO
import com.today.nail.service.data.onBoard.service.OnBoardService

class OnBoardingRepositoryImpl(
    private val onBoardService: OnBoardService
) : OnBoardingRepository {
    override suspend fun userLogin(email: String, password: String): UserLoginResDTO =
        onBoardService.postUserLogin(
            UserLoginReqDTO(
                email = email,
                password = password
            )
        )

    override suspend fun userRegister(
        email: String,
        mobileNo: String,
        password: String,
        name: String,
        role: String
    ): UserRegisterResDTO = onBoardService.postUserRegister(
        UserRegisterReqDTO(
            email = email,
            phoneNum = mobileNo,
            password = password,
            name = name,
            role = role,
        )
    )

    override suspend fun userSocialLogin(): UserSocialLoginResDTO =
        onBoardService.postUserSocialLogin()

//    override suspend fun userSocialInfo(): UserSocialInfoResDTO = onBoardService.postUserSocialInfo()

    override suspend fun userVerify(): UserVerifyResDTO = onBoardService.postUserVerify()

    override suspend fun userStyleTaste(
        styleFirst: String,
        styleSecond: String,
        styleThird: String
    ): UserStyleTasteResDTO = onBoardService.postUserStyleTaste(
        UserStyleTasteReqDTO(
            styleFirst = styleFirst,
            styleSecond = styleSecond,
            styleThird = styleThird
        )
    )
}
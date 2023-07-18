package com.today.nail.service.ui.scenario.onBoarding.navigationGraph

import android.net.Uri
import com.google.gson.Gson

sealed class OnBoardingRoutes(val routes: String) {
    //시작
    object Start : OnBoardingRoutes("start")

    //로그인
    object SignIn : OnBoardingRoutes("sign_in") {
        /**
         * 아래 방식은 나중에 우리가 navigation graph 를 통해서 값을 넘겨줄때 사용할 겁니다. 주로 TopLevelViewModel 에 상태를 저장해서 진행하겠지만
         * 간단한 param 들은 넘겨서 사용해도 무방합니다.
         * 그럴때를 위해 간단한 몇가지 값들을 넘겨주기 위한 설계입니다.
         */

//        private const val AUTHORIZE_STATUS = "authorizeStatus"
//        fun createRoute(
//            authorizeStatus: String
//        ): String {
//            return routes.replace("{$AUTHORIZE_STATUS}", authorizeStatus)
//        }
    }

    // 회원가입
    object Register : OnBoardingRoutes("register")

    // 휴대폰 인증
    object PhoneVerify : OnBoardingRoutes("phone_verify")

    // 선호 스타일 선택
    object FavoriteStyle : OnBoardingRoutes("favorite_style")

    // 선호 스타일 결과
    object FavoriteStyleResult : OnBoardingRoutes("favorite_style_result")
}
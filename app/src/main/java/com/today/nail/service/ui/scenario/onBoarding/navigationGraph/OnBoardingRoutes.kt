package com.today.nail.service.ui.scenario.onBoarding.navigationGraph

import android.net.Uri
import com.google.gson.Gson

sealed class OnBoardingRoutes(val routes: String) {
    object MainGraph : OnBoardingRoutes("main")

    object MenuGraph : OnBoardingRoutes("menu_graph?authorizeStatus={authorizeStatus}") {
        private const val AUTHORIZE_STATUS = "authorizeStatus"

        fun createRoute(
            authorizeStatus: String
        ): String {
            return routes.replace("{$AUTHORIZE_STATUS}", authorizeStatus)
        }
    }

    // 전화로 호출 Graph
    object CenterCallGraph : OnBoardingRoutes("center_call_graph?callCenterList={callCenterList}") {
        private const val CALL_CENTER_LIST = "callCenterList"

        fun createRoute(
            callCenterList: List<String>?
        ): String {
            return routes.replace("{$CALL_CENTER_LIST}", Gson().toJson(callCenterList))
        }
    }

    // 로그인 Graph
    object LoginGraph : OnBoardingRoutes("login_graph")
    // 예약 내역 graph
    object SettingGraph : OnBoardingRoutes("setting_graph")
}
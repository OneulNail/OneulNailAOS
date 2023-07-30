package com.today.nail.service.ui

sealed class TopLevelNavigationRoutes(val routes: String) {
    object SplashGraph : TopLevelNavigationRoutes("splash_top")
    object MainGraph : TopLevelNavigationRoutes("main_graph_top")
    object OnBoardingGraph : TopLevelNavigationRoutes("onboarding_top")
    object HomeGraph : TopLevelNavigationRoutes("home_top")
}
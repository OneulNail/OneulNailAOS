package com.today.nail.service.ui

sealed class TopLevelNavigationRoutes(val routes: String) {
    object SplashGraph : TopLevelNavigationRoutes("splash")
    object MainGraph : TopLevelNavigationRoutes("main_graph")
    object HomeGraph : TopLevelNavigationRoutes("home")
}
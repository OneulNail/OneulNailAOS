package com.today.nail.service.ui.scenario.home.navigationGraph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.autocrypt.move.kcallpax.extension.fadeComposable

import com.today.nail.service.ui.TopLevelNavigationRoutes
import com.today.nail.service.ui.TopLevelViewModel
import com.today.nail.service.ui.scenario.home.view.BannerScreen
import com.today.nail.service.ui.scenario.home.view.CategoryItemScreen
import com.today.nail.service.ui.scenario.home.view.FirstScreen

fun NavGraphBuilder.homeNavigationGraphWithFade(
    navHostController: NavHostController,
    activityViewModel: TopLevelViewModel
) {
    val fadeDuration = 300
    val slideDuration = 400
    navigation(
        route = TopLevelNavigationRoutes.MainGraph.routes,
        startDestination = "FirstScreen"
    ) {
        fadeComposable(
            duration = fadeDuration,
            route = "FirstScreen",
        ) {
            composable(route = "FirstScreen",) {
                FirstScreen(navController = navHostController)
            }
            composable("BannerScreen") {
                BannerScreen(navController = navHostController)
            }
            composable("CategoryItemScreen") {
                CategoryItemScreen(navController = navHostController)
            }
        }
    }
}
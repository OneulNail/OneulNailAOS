package com.today.nail.service.ui.scenario.onBoarding.navigationGraph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigation
import com.autocrypt.move.kcallpax.extension.fadeComposable
import com.today.nail.service.ui.TopLevelNavigationRoutes
import com.today.nail.service.ui.TopLevelViewModel

fun NavGraphBuilder.onBoardingNavigationGraph(
    navHostController: NavHostController,
    activityViewModel: TopLevelViewModel
) {
    val fadeDuration = 300
    val slideDuration = 400

    navigation(
        route = TopLevelNavigationRoutes.MainGraph.routes,
        startDestination = OnBoardingRoutes.MainGraph.routes
    ) {
        fadeComposable(
            duration = fadeDuration,
            route = OnBoardingRoutes.MainGraph.routes,
        ) { backStackEntry ->


        }
    }
}
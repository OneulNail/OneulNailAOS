package com.today.nail.service.ui.scenario.onBoarding.navigationGraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigation
import com.autocrypt.move.kcallpax.extension.fadeComposable
import com.today.nail.service.ui.TopLevelNavigationRoutes
import com.today.nail.service.ui.TopLevelViewModel
import com.today.nail.service.ui.scenario.onBoarding.view.start.OnBoardingStartView


fun NavGraphBuilder.onBoardingNavigationGraph(
    navHostController: NavHostController,
    activityViewModel: TopLevelViewModel
) {
    val fadeDuration = 300

    navigation(
        route = TopLevelNavigationRoutes.MainGraph.routes,
        startDestination = OnBoardingRoutes.Start.routes
    ) {
        fadeComposable(
            duration = fadeDuration,
            route = OnBoardingRoutes.Start.routes,
        ) {
            OnBoardingStartView(
                activityViewModel = activityViewModel,
                navHostController = navHostController
            )
        }
    }
}
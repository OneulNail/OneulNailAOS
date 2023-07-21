package com.today.nail.service.ui.scenario.onBoarding.navigationGraph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigation
import com.autocrypt.move.kcallpax.extension.fadeComposable
import com.autocrypt.move.kcallpax.extension.slideComposable
import com.today.nail.service.ui.TopLevelNavigationRoutes
import com.today.nail.service.ui.TopLevelViewModel
import com.today.nail.service.ui.scenario.onBoarding.view.phoneVerify.OnBoardingPhoneVerifyView
import com.today.nail.service.ui.scenario.onBoarding.view.register.OnBoardingRegisterView
import com.today.nail.service.ui.scenario.onBoarding.view.signIn.OnBoardingSignView
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

        fadeComposable(
            duration = fadeDuration,
            route = OnBoardingRoutes.SignIn.routes
        ) {
            OnBoardingSignView(
                activityViewModel = activityViewModel ,
                navHostController = navHostController
            )
        }

        fadeComposable(
            duration = fadeDuration,
            route = OnBoardingRoutes.PhoneVerify.routes
        ) {
            OnBoardingPhoneVerifyView(
                activityViewModel = activityViewModel,
                navController = navHostController
            )
        }

        slideComposable(
            duration = fadeDuration,
            route = OnBoardingRoutes.Register.routes
        ) {
            OnBoardingRegisterView(
                activityViewModel = activityViewModel,
                navController = navHostController)
        }


    }
}
package com.today.nail.service.ui

import android.window.SplashScreen
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.today.nail.service.ui.scenario.reuseComponent.view.nailItemDetail.ReUseComponentNailItemDetailViewModel


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun TopLevelNavHost(
    modifier: Modifier,
    navHostController: NavHostController,
    topLevelViewModel: TopLevelViewModel,
    onFinish: () -> Unit
) {
    AnimatedNavHost(
        navController = navHostController,
        startDestination = TopLevelNavigationRoutes.SplashGraph.routes,
        modifier = modifier
    ) {
        composable(
            route = TopLevelNavigationRoutes.SplashGraph.routes
        ) { _ ->

        }

        composable(
            route = TopLevelNavigationRoutes.MainGraph.routes
        ) {
        }
    }

}


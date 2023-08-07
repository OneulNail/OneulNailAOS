package com.today.nail.service.ui.scenario.home.navigationGraph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigation
import com.autocrypt.move.kcallpax.extension.fadeComposable

import com.today.nail.service.ui.TopLevelNavigationRoutes
import com.today.nail.service.ui.TopLevelViewModel
import com.today.nail.service.ui.scenario.home.view.BannerScreen
import com.today.nail.service.ui.scenario.home.view.CategoryItemScreen
import com.today.nail.service.ui.scenario.home.view.HomeScreen
import com.today.nail.service.ui.scenario.reuseComponent.view.nailItemDetail.ItemDetailScreen
import com.today.nail.service.ui.scenario.reuseComponent.view.nailItemDetail.ReservationScreen
import com.today.nail.service.ui.scenario.reuseComponent.view.nailItemDetail.ReservationView

fun NavGraphBuilder.homeNavigationGraphWithFade(
    navHostController: NavHostController,
    activityViewModel: TopLevelViewModel
) {
    val fadeDuration = 300
    val slideDuration = 400
    navigation(
        route = TopLevelNavigationRoutes.HomeGraph.routes,
        startDestination = HomeRoute.Home.routes
    ) {
        fadeComposable(
            duration = fadeDuration,
            route = HomeRoute.Home.routes,
        ) {
            HomeScreen(navController = navHostController)
        }

        fadeComposable(
            duration = fadeDuration,
            route = HomeRoute.Banner.routes,
        ) {
            BannerScreen(navController = navHostController)
        }

        fadeComposable(
            duration = fadeDuration,
            route = HomeRoute.CategoryItem.routes,
        ) {
            CategoryItemScreen(navController = navHostController)
        }

        fadeComposable(
            duration = fadeDuration,
            HomeRoute.ItemDetail.routes
        ) {
            ItemDetailScreen(onCall  = {}, onInquire  = {}
            ) {}
        }
        fadeComposable(
            duration = fadeDuration,
            HomeRoute.Reservation.routes
        ) {
            ReservationView(
                navHostController = navHostController,
                activityViewModel = activityViewModel
            )
        }
    }

}
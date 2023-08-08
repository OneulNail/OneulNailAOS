package com.today.nail.service.ui.scenario.home.navigationGraph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigation
import com.autocrypt.move.kcallpax.extension.fadeComposable

import com.today.nail.service.ui.TopLevelNavigationRoutes
import com.today.nail.service.ui.TopLevelViewModel
import com.today.nail.service.ui.scenario.home.view.homeBannerView.BannerScreen
import com.today.nail.service.ui.scenario.home.view.homeCategoryView.CategoryItemScreen
import com.today.nail.service.ui.scenario.home.view.homeCategoryView.HomeCategoryItemView
import com.today.nail.service.ui.scenario.home.view.homeItemView.HomeItemView
import com.today.nail.service.ui.scenario.home.view.homeView.HomeView
import com.today.nail.service.ui.scenario.reuseComponent.view.nailItemDetail.DetailView
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
            HomeView(activityViewModel, navController = navHostController)
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
            HomeCategoryItemView(navController = navHostController)
        }
        fadeComposable(
            duration = fadeDuration,
            route = HomeRoute.Item.routes,
        ) {
            HomeItemView(navController = navHostController)
        }

        fadeComposable(
            duration = fadeDuration,
            HomeRoute.ItemDetail.routes
        ) {
            DetailView( navController = navHostController,
                activityViewModel = activityViewModel)
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
package com.today.nail.service.ui.scenario.home.navigationGraph

sealed class HomeRoute(val routes : String) {

    object Home : HomeRoute("home")

    object Banner : HomeRoute("banner")

    object CategoryItem : HomeRoute("category_item")

    object Item : HomeRoute("item")

    object ItemDetail : HomeRoute("item_detail")

    object Reservation : HomeRoute("reservation")
}
package com.today.nail.service.ui.scenario.home.navigationGraph

sealed class HomeRoute(val routes : String) {

    object Home : HomeRoute("home")

    object Banner : HomeRoute("banner")

    object CategoryItem : HomeRoute("category_item")

    object Item : HomeRoute("item")

    object NailShop : HomeRoute("nail_shop")

    object Favorite : HomeRoute("favorite")

    object MyPage : HomeRoute("my_page")

    object ItemDetail : HomeRoute("item_detail")

    object Reservation : HomeRoute("reservation")
}
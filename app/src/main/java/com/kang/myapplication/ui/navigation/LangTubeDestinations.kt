package com.kang.myapplication.ui.navigation

sealed class LangTubeDestinations(val route: String) {
    object Home : LangTubeDestinations("home")
    object Search : LangTubeDestinations("search")
    object Settings : LangTubeDestinations("settings")
    object Player : LangTubeDestinations("player/{videoId}") {
        fun createRoute(videoId: String) = "player/$videoId"
    }
}

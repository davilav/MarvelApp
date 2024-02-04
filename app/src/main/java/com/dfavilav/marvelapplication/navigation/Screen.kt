package com.dfavilav.marvelapplication.navigation

sealed class Screen(val route: String) {

    data object Splash : Screen("splash_screen")
    data object Home : Screen("home_screen")
    data object Search : Screen("search_screen")
    data object Comic : Screen("comic_screen/{characterId}") {
        fun passCharacterId(characterId: Int): String {
            return "comic_screen/$characterId"
        }
    }
}

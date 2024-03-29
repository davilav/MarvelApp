package com.dfavilav.marvelapplication.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import coil.annotation.ExperimentalCoilApi
import com.dfavilav.marvelapplication.presentation.screens.comic.ComicScreen
import com.dfavilav.marvelapplication.presentation.screens.detail.DetailsScreen
import com.dfavilav.marvelapplication.presentation.screens.home.HomeScreen
import com.dfavilav.marvelapplication.presentation.screens.search.SearchScreen
import com.dfavilav.marvelapplication.presentation.screens.splash.SplashScreen
import com.dfavilav.marvelapplication.util.Constants.COMIC_DETAILS_ARGUMENT_KEY
import com.dfavilav.marvelapplication.util.Constants.DETAILS_ARGUMENT_KEY

@ExperimentalMaterialApi
@ExperimentalCoilApi
@ExperimentalAnimationApi
@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(route = Screen.Splash.route) {
            SplashScreen(navController = navController)
        }
        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(route = Screen.Search.route) {
            SearchScreen(navController = navController)
        }
        composable(
            route = Screen.Comic.route,
            arguments = listOf(navArgument(DETAILS_ARGUMENT_KEY) {
                type = NavType.IntType
            })
        ) {
            ComicScreen(navController = navController)
        }
        composable(
            route = Screen.Details.route,
            arguments = listOf(navArgument(COMIC_DETAILS_ARGUMENT_KEY) {
                type = NavType.IntType
            })
        ) {
            DetailsScreen(navController = navController)
        }
    }
}
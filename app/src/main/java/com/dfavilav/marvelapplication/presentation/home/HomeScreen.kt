package com.dfavilav.marvelapplication.presentation.home

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.dfavilav.marvelapplication.navigation.Screen
import com.dfavilav.marvelapplication.presentation.common.HomeListContent

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@ExperimentalCoilApi
@Composable
fun HomeScreen(
    navController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val activity = LocalContext.current as Activity
    val allHeroes = homeViewModel.getAllHeroes.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            HomeTopBar(
                onSearchClicked = {
                    navController.navigate(Screen.Splash.route)
                }
            )
        },
        content = {
            HomeListContent(
                heroes = allHeroes,
                navController = navController
            )
        }
    )
}

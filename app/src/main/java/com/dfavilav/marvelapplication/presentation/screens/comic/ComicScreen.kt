package com.dfavilav.marvelapplication.presentation.screens.comic

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.dfavilav.marvelapplication.presentation.common.ComicListContent
import com.dfavilav.marvelapplication.presentation.common.HomeListContent


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@ExperimentalCoilApi
@Composable
fun ComicScreen(
    navController: NavHostController,
    comicViewModel: ComicViewModel = hiltViewModel()
) {
    val activity = LocalContext.current as Activity
    val allComics = comicViewModel.allComics.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            ComicTopBar()
        },
        content = {
            ComicListContent(
                    comics = allComics,
                    navController = navController
                )
        }
    )
}
package com.dfavilav.marvelapplication.presentation.common

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import coil.annotation.ExperimentalCoilApi
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.dfavilav.marvelapplication.R
import com.dfavilav.marvelapplication.domain.model.Thumbnail
import com.dfavilav.marvelapplication.domain.model.comic.Comic
import com.dfavilav.marvelapplication.navigation.Screen
import com.dfavilav.marvelapplication.presentation.components.ShimmerEffect
import com.dfavilav.marvelapplication.ui.theme.CHARACTER_ITEM_HEIGHT
import com.dfavilav.marvelapplication.ui.theme.LARGE_PADDING
import com.dfavilav.marvelapplication.ui.theme.MEDIUM_PADDING
import com.dfavilav.marvelapplication.ui.theme.MarvelRed
import com.dfavilav.marvelapplication.ui.theme.SMALL_PADDING
import com.dfavilav.marvelapplication.ui.theme.topAppBarContentColor
import com.dfavilav.marvelapplication.util.createUrlWithExtension

@ExperimentalCoilApi
@Composable
fun ComicListContent(
    comics: LazyPagingItems<Comic>,
    navController: NavHostController
) {
    val result = handlePagingResult(comics)

    if (result) {
        LazyColumn(
            contentPadding = PaddingValues(all = SMALL_PADDING),
            verticalArrangement = Arrangement.spacedBy(SMALL_PADDING)
        ) {
            items(
                items = comics.itemSnapshotList.items,
                key = { hero ->
                    hero.id
                }
            ) { characters ->
                ComicItem(characters, navController = navController)
            }
        }
    }
}

@Composable
fun handlePagingResult(
    characters: LazyPagingItems<Comic>
): Boolean {
    characters.apply {
        val error = when {
            loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
            loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
            loadState.append is LoadState.Error -> loadState.append as LoadState.Error
            else -> null
        }

        return when {
            loadState.refresh is LoadState.Loading -> {
                ShimmerEffect()
                false
            }
            error != null -> {
                ComicEmptyScreen(error, characters)
                false
            }
            characters.itemCount < 1 -> {
                ComicEmptyScreen()
                false
            }
            else -> true
        }
    }
}

@ExperimentalCoilApi
@Composable
fun ComicItem(
    comic: Comic,
    navController: NavHostController
) {
    Box(
        modifier = Modifier
            .height(CHARACTER_ITEM_HEIGHT)
            .clickable { navController.navigate(Screen.Details.passComicId(comic.id)) },
        contentAlignment = Alignment.BottomStart
    ) {
        Surface(shape = RoundedCornerShape(size = LARGE_PADDING)) {
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(data = comic.thumbnail?.extension?.let {
                        comic.thumbnail?.path?.createUrlWithExtension(
                            it
                        )
                    })
                    .placeholder(drawableResId = R.drawable.hulk_logo)
                    .error(drawableResId = R.drawable.hulk_logo)
                    .build(),
                contentDescription = "",
                contentScale = ContentScale.Crop
            )
        }
        Surface(
            modifier = Modifier
                .fillMaxHeight(0.2f)
                .fillMaxWidth(),
            color = MarvelRed,
            shape = RoundedCornerShape(
                bottomStart = LARGE_PADDING,
                bottomEnd = LARGE_PADDING
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(all = MEDIUM_PADDING)
            ) {
                comic.title?.let {
                    Text(
                        text = it,
                        color = MaterialTheme.colors.topAppBarContentColor,
                        fontSize = MaterialTheme.typography.h6.fontSize,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

@ExperimentalCoilApi
@Composable
@Preview
fun ComicItemPreview() {
    ComicItem(
        comic = Comic(
            id = 1,
            title =  "Iron Man",
            description =  "Iron Man",
            thumbnail = Thumbnail(
                "",
                ""
            )
        ),
        navController = rememberNavController()
    )
}

@ExperimentalCoilApi
@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
fun ComicItemDarkPreview() {
    ComicItem(
        comic = Comic(
            id = 1,
            title =  "Iron Man",
            description = "Iron Man",
            thumbnail = Thumbnail(
                "",
                ""
            )
        ),
        navController = rememberNavController()
    )
}

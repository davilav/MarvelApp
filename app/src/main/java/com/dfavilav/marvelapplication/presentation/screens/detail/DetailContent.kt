package com.dfavilav.marvelapplication.presentation.screens.detail

import android.app.Activity
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.dfavilav.marvelapplication.R
import com.dfavilav.marvelapplication.domain.model.comic.Comic
import com.dfavilav.marvelapplication.ui.theme.EXPANDED_RADIUS_LEVEL
import com.dfavilav.marvelapplication.ui.theme.EXTRA_LARGE_PADDING
import com.dfavilav.marvelapplication.ui.theme.INFO_ICON_SIZE
import com.dfavilav.marvelapplication.ui.theme.LARGE_PADDING
import com.dfavilav.marvelapplication.ui.theme.MIN_SHEET_HEIGHT
import com.dfavilav.marvelapplication.ui.theme.SMALL_PADDING
import com.dfavilav.marvelapplication.ui.theme.titleColor
import com.dfavilav.marvelapplication.util.Constants.MIN_BACKGROUND_IMAGE_HEIGHT
import com.dfavilav.marvelapplication.util.createUrlWithExtension

@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun DetailsContent(
    navController: NavHostController,
    selectedComic: Comic?,
    colors: Map<String, String>
) {
    val activity = LocalContext.current as Activity

    var vibrant by remember { mutableStateOf("#000000") }
    var darkVibrant by remember { mutableStateOf("#000000") }
    var onDarkVibrant by remember { mutableStateOf("#ffffff") }

    LaunchedEffect(key1 = selectedComic) {
        vibrant = colors["vibrant"]!!
        darkVibrant = colors["darkVibrant"]!!
        onDarkVibrant = colors["onDarkVibrant"]!!
    }

    SideEffect {
        activity.window.statusBarColor =
            Color(android.graphics.Color.parseColor(darkVibrant)).toArgb()
    }

    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    )

    val currentSheetFraction = scaffoldState.currentSheetFraction

    val radiusAnim by animateDpAsState(
        targetValue = if (currentSheetFraction == 1f) EXTRA_LARGE_PADDING
        else EXPANDED_RADIUS_LEVEL,
        label = "Radius Animation"
    )

    BottomSheetScaffold(
        sheetShape = RoundedCornerShape(
            topStart = radiusAnim,
            topEnd = radiusAnim
        ),
        scaffoldState = scaffoldState,
        sheetPeekHeight = MIN_SHEET_HEIGHT,
        sheetContent = {
            selectedComic?.let {
                BottomSheetContent(
                    selectedComic = it,
                    sheetBackgroundColor = Color(android.graphics.Color.parseColor(darkVibrant)),
                    contentColor = Color(android.graphics.Color.parseColor(onDarkVibrant))
                )
            }
        },
        content = {
            selectedComic?.let { comic ->
                comic.thumbnail?.extension?.let { image ->
                    comic.thumbnail?.path?.createUrlWithExtension(image)?.let {
                        BackgroundContent(
                            comicImage = it,
                            imageFraction = currentSheetFraction,
                            backgroundColor = Color(android.graphics.Color.parseColor(darkVibrant)),
                            onCloseClicked = {
                                navController.popBackStack()
                            }
                        )
                    }
                }
            }
        })
}


@Composable
fun BottomSheetContent(
    selectedComic: Comic,
    sheetBackgroundColor: Color = MaterialTheme.colors.surface,
    contentColor: Color = MaterialTheme.colors.titleColor
) {
    Column(
        modifier = Modifier
            .background(sheetBackgroundColor)
            .padding(all = LARGE_PADDING),
        verticalArrangement = Arrangement.Top
    ) {
        selectedComic.title?.let {
            Text(
                modifier = Modifier.align((Alignment.CenterHorizontally)),
                text = it,
                color = contentColor,
                fontSize = MaterialTheme.typography.h4.fontSize,
                fontWeight = FontWeight.Bold
            )
        }
        selectedComic.description?.let {
            Text(
                modifier = Modifier
                    .weight(4f).align((Alignment.CenterHorizontally)),
                text = it,
                color = contentColor,
                fontSize = MaterialTheme.typography.h6.fontSize,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@ExperimentalCoilApi
@Composable
fun BackgroundContent(
    comicImage: String,
    imageFraction: Float = 1f,
    backgroundColor: Color = MaterialTheme.colors.surface,
    onCloseClicked: () -> Unit
) {
    val imageUrl = remember { comicImage }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(
                    fraction = (imageFraction + MIN_BACKGROUND_IMAGE_HEIGHT)
                        .coerceAtMost(1.0f)
                )
                .align(Alignment.TopCenter),
            model = ImageRequest.Builder(LocalContext.current)
                .data(data = imageUrl)
                .error(drawableResId = R.drawable.ic_marvel)
                .build(),
            contentDescription = "",
            contentScale = ContentScale.Crop
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(
                modifier = Modifier.padding(all = SMALL_PADDING),
                onClick = { onCloseClicked() }
            ) {
                Icon(
                    modifier = Modifier.size(INFO_ICON_SIZE),
                    imageVector = Icons.Default.Close,
                    contentDescription = "",
                    tint = Color.White
                )
            }
        }
    }
}

@ExperimentalMaterialApi
val BottomSheetScaffoldState.currentSheetFraction: Float
    get() {
        val fraction = bottomSheetState.progress
        val targetValue = bottomSheetState.targetValue
        val currentValue = bottomSheetState.currentValue

        return when {
            currentValue == BottomSheetValue.Collapsed && targetValue == BottomSheetValue.Collapsed -> 1f
            currentValue == BottomSheetValue.Expanded && targetValue == BottomSheetValue.Expanded -> 0f
            currentValue == BottomSheetValue.Collapsed && targetValue == BottomSheetValue.Expanded -> 1f - fraction
            currentValue == BottomSheetValue.Expanded && targetValue == BottomSheetValue.Collapsed -> 0f + fraction
            else -> fraction
        }
    }

@Composable
@Preview
fun BottomSheetContentPreview() {
    BottomSheetContent(
        selectedComic = Comic(
            id = 1,
            title = "Sasuke",
            description = "Hola"
        )
    )
}
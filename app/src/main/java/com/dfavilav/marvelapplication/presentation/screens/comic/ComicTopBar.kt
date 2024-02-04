package com.dfavilav.marvelapplication.presentation.screens.comic

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dfavilav.marvelapplication.R
import com.dfavilav.marvelapplication.ui.theme.White

@Composable
fun ComicTopBar() {
    TopAppBar(
        backgroundColor = White,
        title = {
            Column(
                Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_marvel),
                    contentDescription = "",
                    alignment = Alignment.Center,
                    modifier = Modifier
                        .size(100.dp, 100.dp)
                        .absolutePadding(1.dp, 1.dp, 1.dp, 1.dp)
                )
            }
        },
    )
}

@Composable
@Preview
fun ComicTopBarPreview() {
    ComicTopBar()
}

package com.it.news.presentation.view.home


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.it.news.R

@Composable
fun HomeContentImageview(imageURL: String, modifier: Modifier) {
    AsyncImage(
        modifier = modifier,
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageURL)
            .crossfade(true)
            .build(),
        placeholder = painterResource(id = R.drawable.ic_launcher_background),
        contentScale = ContentScale.Crop,
        contentDescription = ""
    )
}
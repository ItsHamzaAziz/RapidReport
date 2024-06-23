package com.example.rapidreport.presentation.component

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.rapidreport.R

@Composable
fun ImageHolder(
    imageUrl: String?,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model = ImageRequest
            .Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)        // When image does from placeholder to actual image then cross fade transition will occur
            .build(),
        contentDescription = "Image",
        contentScale = ContentScale.Crop,
        modifier = modifier
            .clip(RoundedCornerShape(2.dp))
            .fillMaxWidth()
            .aspectRatio(16 / 9f),
        placeholder = painterResource(R.drawable.loading_image),
        error = painterResource(R.drawable.resource_default)
    )
}
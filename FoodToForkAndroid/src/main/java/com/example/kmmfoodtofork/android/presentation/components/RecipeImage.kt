package com.example.kmmfoodtofork.android.presentation.components

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest

const val RECIPE_HEIGHT = 260

@Composable
fun RecipeImage(url: String, contentDescription: String) {
/*    Box {
        SubcomposeAsyncImage(
            model = url,
            contentDescription = contentDescription,
            modifier = Modifier
                .fillMaxWidth()
                .height(RECIPE_HEIGHT.dp), contentScale = ContentScale.Crop, onState = {
                when (it) {
                    is AsyncImagePainter.State.Loading -> {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(RECIPE_HEIGHT.dp)
                        ) {
                            //empty for white background
                        }
                    }
                    is AsyncImagePainter.State.Error -> {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(RECIPE_HEIGHT.dp)
                        ) {
                            //error for white background
                        }
                    }
                    is AsyncImagePainter.State.Success -> {

                    }
                    is AsyncImagePainter.State.Empty -> {

                    }
                }
            }
        ) {


        }
    }*/
    Box {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(RECIPE_HEIGHT.dp),
            contentScale = ContentScale.Crop,
            model = ImageRequest.Builder(LocalContext.current)
                .data(url)
                .crossfade(true)
                .build(),
            contentDescription = contentDescription
        )
    }

}

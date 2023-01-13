package com.example.kmmfoodtofork.android.presentation.navigation.recipe_list.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp

@Composable
fun LoadingRecipeListShimmer(imageHeight: Dp, padding: Dp) {
    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val cardHeightPx = with(LocalDensity.current) { imageHeight.minus(padding).toPx() }
        val cardWidthPx = with(LocalDensity.current) { maxWidth.minus((padding.times(2))).toPx() }
        val gradientWidth = 0.2f.times(cardHeightPx)

        val infiniteTransition = rememberInfiniteTransition()
        val xCardShimmer = infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = cardWidthPx.plus(gradientWidth),
            animationSpec = infiniteRepeatable(
                animation = tween(1300, easing = LinearEasing, delayMillis = 300),
                repeatMode = RepeatMode.Restart
            )
        )

        val yCardShimmer = infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = cardHeightPx.plus(gradientWidth),
            animationSpec = infiniteRepeatable(
                animation = tween(1300, easing = LinearEasing, delayMillis = 300),
                repeatMode = RepeatMode.Restart
            )
        )

        val colors = listOf(
            Color.LightGray.copy(alpha = .9f),
            Color.LightGray.copy(alpha = .3f),
            Color.LightGray.copy(alpha = .9f),
        )

        LazyColumn {
            items(5) {
                ShimmerRecipeCard(
                    colors = colors,
                    xShimmer = xCardShimmer.value,
                    yShimmer = yCardShimmer.value,
                    cardHeight = imageHeight,
                    gradientWidth = gradientWidth,
                    padding = padding.value
                )
            }
        }
    }
}

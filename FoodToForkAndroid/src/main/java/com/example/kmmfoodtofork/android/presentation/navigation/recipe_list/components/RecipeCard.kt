package com.example.kmmfoodtofork.android.presentation.navigation.recipe_list.components

import android.content.Context
import android.service.autofill.OnClickAction
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.text.HtmlCompat
import com.example.kmmfoodtofork.android.presentation.components.RecipeImage
import com.example.kmmfoodtofork.domain.model.Recipe

@Composable
fun RecipeCard(
    recipe: Recipe, onClick: () -> Unit
) {
    Card(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .padding(bottom = 6.dp, top = 6.dp)
            .fillMaxWidth()
            .clickable {
                onClick.invoke()
            },
        elevation = 8.dp
    ) {
        Column {
            RecipeImage(url = recipe.featuredImage, contentDescription = recipe.title)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 12.dp,
                        bottom = 12.dp, start = 8.dp, end = 8.dp
                    )
            ) {
                Text(
                    text = HtmlCompat.fromHtml(recipe.title, HtmlCompat.FROM_HTML_MODE_LEGACY)
                        .toString(),
                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .wrapContentWidth(Alignment.Start),
                    style = MaterialTheme.typography.h3
                )

                Text(
                    text = recipe.rating.toString(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.End)
                        .align(CenterVertically),
                    style = MaterialTheme.typography.h5
                )
            }
        }

    }

}

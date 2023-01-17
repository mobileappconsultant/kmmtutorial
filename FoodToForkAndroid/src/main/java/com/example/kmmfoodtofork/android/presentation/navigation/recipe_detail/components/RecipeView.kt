package com.example.kmmfoodtofork.android.presentation.navigation.recipe_detail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.text.HtmlCompat
import com.example.kmmfoodtofork.android.presentation.components.RecipeImage
import com.example.kmmfoodtofork.domain.model.Recipe
import com.example.kmmfoodtofork.domain.model.util.DatetimeUtil

@OptIn(ExperimentalStdlibApi::class)
@Composable
fun RecipeView(recipe: Recipe) {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        item {
            RecipeImage(url = recipe.featuredImage, contentDescription = recipe.title)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 4.dp)
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
                            .align(Alignment.CenterVertically),
                        style = MaterialTheme.typography.h5
                    )
                }
                val datetimeUtil = remember {
                    DatetimeUtil()
                }
                val stringBuilder = remember {
                    java.lang.StringBuilder()
                }
                stringBuilder.append("Updated at ")
                stringBuilder.append(datetimeUtil.humanizeDatetime(recipe.dateUpdated))
                stringBuilder.append("by ")
                stringBuilder.append(recipe.publisher)
                Text(
                    text = stringBuilder.toString(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    style = MaterialTheme.typography.caption
                )
                recipe.ingredients.forEach { ingredient ->
                    Text(
                        text = ingredient,
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 4.dp)
                    )
                }


            }
        }
    }
}

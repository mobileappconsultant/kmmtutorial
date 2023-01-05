package com.example.kmmfoodtofork.datasourc.network

import com.example.kmmfoodtofork.datasource.network.model.RecipeDto
import com.example.kmmfoodtofork.domain.model.Recipe
import com.example.kmmfoodtofork.domain.model.util.DatetimeUtil
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig

expect class KtorClientFactory {
    fun build(): HttpClient
}


fun RecipeDto.toRecipe(): Recipe {
    val datetimeUtil = DatetimeUtil()
    return Recipe(
        id = pk,
        title = title,
        publisher = publisher,
        image = featured_image,
        rating = rating,
        sourceUrl = source_url,
        description = description,
     /*   cookingInstructions = cooking_instructions,*/
        ingredients = ingredients,
        date_added = datetimeUtil.toLocalDate(long_date_added.toDouble()),
        date_updated = datetimeUtil.toLocalDate(long_date_updated.toDouble()),
        long_date_updated = long_date_updated,
        long_date_added = long_date_added
    )
}

fun List<RecipeDto>.toList(): List<Recipe> {
    return map { it.toRecipe() }
}

package com.example.kmmfoodtofork.datasourc.network

import com.example.kmmfoodtofork.datasource.network.model.RecipeDto
import com.example.kmmfoodtofork.domain.model.Recipe
import com.example.kmmfoodtofork.domain.model.util.DatetimeUtil
import io.ktor.client.HttpClient


expect class KtorClientFactory {
    fun build(): HttpClient
}

fun RecipeDto.toRecipe(): Recipe {
    val datetimeUtil = DatetimeUtil()
    return Recipe(
        id = pk,
        title = title,
        featuredImage = featuredImage,
        rating = rating,
        publisher = publisher,
        sourceUrl = sourceUrl,
        ingredients = ingredients,
        dateAdded = datetimeUtil.toLocalDate(longDateAdded.toDouble()),
        dateUpdated = datetimeUtil.toLocalDate(longDateUpdated.toDouble()),
    )
}

fun List<RecipeDto>.toList(): List<Recipe> {
    return map { it.toRecipe() }
}

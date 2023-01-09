package com.example.kmmfoodtofork.datasource.cache

import com.example.kmmfoodtofork.domain.model.Recipe
import com.example.kmmfoodtofork.domain.model.util.DatetimeUtil
import com.squareup.sqldelight.db.SqlDriver

class RecipeDatabaseFactory(private val driverFactory: DriverFactory) {
    fun createDatabase(): RecipeDatabase {
        return RecipeDatabase(driverFactory.createDriver())
    }
}


expect class DriverFactory {
    fun createDriver(): SqlDriver
}


fun Recipe_Entity.toRecipe(): Recipe? {
    val datetimeUtil = DatetimeUtil()
    return Recipe(
        id = id.toInt(),
        title = title,
        publisher = publisher,
        featuredImage = featured_image,
        rating = rating.toInt(),
        sourceUrl = source_url,
        ingredients = ingredients.convertIngredientsToList(),
        dateAdded = datetimeUtil.toLocalDate(date_added),
        dateUpdated = datetimeUtil.toLocalDate(date_updated)
    )
}

fun List<String>.convertIngredientsToString(): String {
    val stringBuilder = StringBuilder()
    this.forEach { stringBuilder.append(it.plus(",")) }
    return stringBuilder.toString()
}

fun String.convertIngredientsToList(): List<String> {
    return this.split(",").toList()
}



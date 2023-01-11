package com.example.kmmfoodtofork.datasourc.network

import com.example.kmmfoodtofork.datasource.cache.Recipe_Entity
import com.example.kmmfoodtofork.datasource.cache.convertIngredientsToList
import com.example.kmmfoodtofork.datasource.network.model.RecipeDto
import com.example.kmmfoodtofork.datasource.network.model.RecipeSearchResponse
import com.example.kmmfoodtofork.domain.model.Recipe
import com.example.kmmfoodtofork.domain.model.util.DatetimeUtil
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.accept
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.URLBuilder
import io.ktor.http.URLProtocol
import io.ktor.http.encodedPath
import io.ktor.utils.io.core.use

class RecipeServiceImpl(
    private val httpclient: HttpClient,
    private val baseUrl: String
) : RecipeService {

    override suspend fun search(page: Int, query: String): List<Recipe> {
        val url = URLBuilder().apply {
            host = "food2fork.ca"
            port = 80
            protocol = URLProtocol("https", 80)
            encodedPath = "api/recipe/search"
            parameters.append("page", page.toString())
            parameters.append("query", query)
        }
        return httpclient.use {
            it.get {
                accept(ContentType.Application.Json)
                url(url.build())
                header("Authorization", TOKEN)
                header("Content-Type", "application/json; charset=UTF-8")
            }.body<RecipeSearchResponse>().result.map { it.toRecipe() }
        }
    }

    override suspend fun getRecipe(id: Int): Recipe {
        val url = URLBuilder().apply {
            host = "food2fork.ca"
            port = 80
            protocol = URLProtocol("https", 80)
            encodedPath = "api/recipe/get"
            parameters.append("id", id.toString())
        }


        return httpclient.get {
            accept(ContentType.Application.Json)
            url(url.build())
            header("Authorization", TOKEN)
            header("Content-Type", "application/json; charset=UTF-8")
        }.body<RecipeDto>().toRecipe()
    }


    fun Recipe_Entity.toRecipe(): Recipe {
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
            dateUpdated = datetimeUtil.toLocalDate(date_updated),
        )
    }

    fun List<Recipe_Entity>.toRecipeList(): List<Recipe> {
        return map { it.toRecipe() }
    }

    companion object {
        val TOKEN = "Token 9c8b06d329136da358c2d00e76946b0111ce2c48"
        val RECIPE_PAGINATION_PAGE_SIZE = 30

    }
}

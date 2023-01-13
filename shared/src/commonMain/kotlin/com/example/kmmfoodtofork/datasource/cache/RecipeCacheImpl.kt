package com.example.kmmfoodtofork.datasource.cache

import com.example.kmmfoodtofork.datasourc.network.RecipeServiceImpl.Companion.RECIPE_PAGINATION_PAGE_SIZE
import com.example.kmmfoodtofork.domain.model.Recipe
import com.example.kmmfoodtofork.domain.model.util.DatetimeUtil

class RecipeCacheImpl(
    private val recipeDatabase: RecipeDatabase,
    private val datetimeUtil: DatetimeUtil
) : RecipeCache {
    private val queries: RecipeDbQueries = recipeDatabase.recipeDbQueries
    override fun insertRecipe(recipe: Recipe) {
        queries.insertRecipe(
            id = recipe.id.toLong(),
            title = recipe.title,
            publisher = recipe.publisher,
            featured_image = recipe.featuredImage,
            rating = recipe.rating.toLong(),
            source_url = recipe.sourceUrl,
            recipe.ingredients.convertIngredientsToString(),
            date_added = datetimeUtil.toEpochMilliseconds(recipe.dateAdded),
            date_updated = datetimeUtil.toEpochMilliseconds(recipe.dateUpdated)
        )
    }

    override fun insert(recipes: List<Recipe>) {
        recipes.forEach { insertRecipe(it) }
    }

/*    override fun search(query: String, page: Int): List<Recipe> {
        return queries.searchRecipes(
            query = query, pageSize = RECIPE_PAGINATION_PAGE_SIZE.toLong(),
            offset = (page.minus(1).times(RECIPE_PAGINATION_PAGE_SIZE)).toLong()
        ).executeAsList().map { it.toRecipe()!! }
    }*/

    override fun search(query: String, page: Int): List<Recipe> {
        return queries.searchRecipes(
            query = query,
            pageSize = RECIPE_PAGINATION_PAGE_SIZE.toLong(),
            offset = 5/*((page - 1) * RECIPE_PAGINATION_PAGE_SIZE).toLong()*/
        ).executeAsList().map { it.toRecipe()!! }
    }

    override fun getAll(page: Int): List<Recipe> {
        return queries.getAllRecipes(
            pageSize = page.toLong(),
            (page.minus(1).times(RECIPE_PAGINATION_PAGE_SIZE)).toLong()
        ).executeAsList()
            .map { it.toRecipe()!! }
    }

    override fun get(recipeId: Int): Recipe? {
            return queries.getRecipeById(id = recipeId.toLong()).executeAsOne().toRecipe()
        }
    }







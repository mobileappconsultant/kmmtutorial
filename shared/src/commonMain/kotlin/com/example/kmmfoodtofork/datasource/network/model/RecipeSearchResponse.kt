package com.example.kmmfoodtofork.datasource.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecipeSearchResponse(
    @SerialName("count") var count: Int,
    @SerialName("results") var result: List<RecipeDto>
)

package com.example.kmmfoodtofork.datasource.network.model

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecipeDto(
    @SerialName("pk") val pk: Int,
    @SerialName("title") val title: String,
    @SerialName("publisher") val publisher: String,
    @SerialName("featured_image") val featured_image: String,
    @SerialName("rating") val rating: Int,
    @SerialName("source_url") val source_url: String,
    @SerialName("description") val description: String,
   /* @SerialName("cooking_instructions") val cooking_instructions: String="",*/
    @SerialName("ingredients") val ingredients: List<String> = listOf(),
    @SerialName("date_added") val date_added: LocalDateTime,
    @SerialName("date_updated") val date_updated: LocalDateTime,
    @SerialName("long_date_updated") val long_date_updated: Long,
    @SerialName("long_date_added") val long_date_added: Long
)

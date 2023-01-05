package com.example.kmmfoodtofork.domain.model

import kotlinx.datetime.LocalDateTime


data class Recipe(
    val id: Int,
    val title: String,
    val publisher: String,
    val image: String,
    val rating: Int,
    val sourceUrl: String,
    val description: String,
/*    val cookingInstructions: String,*/
    val ingredients: List<String> = listOf(),
    val date_added: LocalDateTime,
    val date_updated: LocalDateTime,
    val long_date_updated: Long,
    val long_date_added: Long
)


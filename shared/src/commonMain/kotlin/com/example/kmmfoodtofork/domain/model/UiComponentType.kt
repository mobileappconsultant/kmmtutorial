package com.example.kmmfoodtofork.domain.model

sealed class UiComponentType {
    object Dialog : UiComponentType()
    object None : UiComponentType()//like logging for example
}

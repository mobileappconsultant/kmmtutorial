package com.example.kmmfoodtofork.android.presentation.navigation.recipe_detail

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.kmmfoodtofork.android.presentation.navigation.Screen

@Composable
fun RecipeDetailScreen(recipeId : Int?){
    if(recipeId==null){
        Text("ERROR")
    }else{
        Text(text = "RecipeDetailScreen:${recipeId}")
    }
}

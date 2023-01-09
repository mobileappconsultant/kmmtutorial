package com.example.kmmfoodtofork.android

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.example.kmmfoodtofork.android.presentation.navigation.Navigation
import com.example.kmmfoodtofork.datasourc.network.KtorClientFactory
import com.example.kmmfoodtofork.datasourc.network.RecipeServiceImpl
import com.example.kmmfoodtofork.domain.model.util.DatetimeUtil
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

const val BASE_URL = "https://food2fork.ca/api/recipe/"
const val TOKEN = "Token 9c8b06d329136da358c2d00e76946b0111ce2c48"

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    val ktorClientFactory = KtorClientFactory().build()

    @OptIn(ExperimentalStdlibApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Navigation()
          /*  CoroutineScope(IO).launch {
                var recipes = RecipeServiceImpl(ktorClientFactory, BASE_URL).search(1, "Chicken")
                recipes.forEach { recipe ->
                    Log.d("RESPONSE", recipe.id.toString())
                    Log.d("RESPONSE", recipe.title.toString())
                    Log.d("RESPONSE", DatetimeUtil().humanizeDatetime(recipe.dateUpdated))
                }

            }*/
        }
    }
}

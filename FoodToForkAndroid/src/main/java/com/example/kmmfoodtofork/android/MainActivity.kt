package com.example.kmmfoodtofork.android

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.example.kmmfoodtofork.android.presentation.navigation.Navigation
import com.example.kmmfoodtofork.datasourc.network.KtorClientFactory
import com.example.kmmfoodtofork.datasource.network.model.RecipeTemp
import dagger.hilt.android.AndroidEntryPoint
import io.ktor.client.request.accept
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.URLBuilder
import io.ktor.http.URLProtocol
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

const val BASE_URL = "https://food2fork.ca/api/recipe/"
const val TOKEN = "Token 9c8b06d329136da358c2d00e76946b0111ce2c48"

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    val ktorClientFactory = KtorClientFactory().build()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Navigation()
            CoroutineScope(IO).launch {
                val recipeId = 1551
                val url = URLBuilder().apply {
                    host = "food2fork.ca"
                    port = 80
                    protocol = URLProtocol("https", 80)
                    encodedPath = "api/recipe/get"
                    parameters.append("id", recipeId.toString())
                }
                try {
                    var recipe = ktorClientFactory.get<RecipeTemp> {
                       // accept(ContentType.Application.Json)
                        url(url.build())
                        header("Authorization", TOKEN)
                      //  header("Content-Type", "application/json; charset=UTF-8")

                    }
                    Log.d("RESPONSE", recipe.toString())
                } catch (exception: Exception) {
                    Log.e("Error", exception.message.toString())
                }

            }
        }
    }
}

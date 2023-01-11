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

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @OptIn(ExperimentalStdlibApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Navigation()
        }
    }
}

package com.example.kmmfoodtofork.datasourc.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.kotlinx.serializer.KotlinxSerializer
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration

actual class KtorClientFactory {

    actual fun build(): HttpClient {

        return HttpClient(CIO) {
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }
            install(ContentNegotiation) {
                Json {
                    prettyPrint = true
                    isLenient = true
                }
            }


        }
    }

}

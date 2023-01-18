package com.example.kmmfoodtofork.datasource.network

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import kotlinx.serialization.json.Json
import io.ktor.serialization.kotlinx.json.*

actual class KtorClientFactory {

    actual fun build() =
        HttpClient() {
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }
            install(ContentNegotiation) {
               json(Json {
                   prettyPrint = true
                   isLenient = true
                   ignoreUnknownKeys=true
               })
            }
        }
}


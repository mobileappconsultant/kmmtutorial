package com.example.kmmfoodtofork.datasource.cache

import android.content.Context
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.android.AndroidSqliteDriver

actual class DriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver =
        AndroidSqliteDriver(RecipeDatabase.Schema, context, "recipes.db")
}

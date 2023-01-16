plugins {
    kotlin(KotlinPlugins.multiplatform)
    id(Plugins.androidLibrary)
    id(Plugins.sqlDelight)
    kotlin(KotlinPlugins.serialization) version Kotlin.version
}

kotlin {
    android()

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(Kotlinx.datetime)
                implementation(Ktor.content_negotiation)
                implementation(Ktor.logging)
                implementation(Ktor.core)
                implementation(Ktor.clientSerialization)
                implementation(Ktor.serialization_kotlinx)
                implementation(SQLDelight.runtime)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(Ktor.ktor_client_android_okhttp)
                implementation(Ktor.clientSerialization)
                implementation(SQLDelight.androidDriver)
            }
        }
        val androidTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
                implementation(Ktor.ktor_darwin)
                implementation(SQLDelight.nativeDriver)
            }
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}
android {
    namespace = "com.example.kmmfoodtofork"
    compileSdk = 32
    defaultConfig {
        minSdk = 23
        targetSdk = 32
    }
}



sqldelight {
    database("RecipeDatabase") {
        packageName = "com.example.kmmfoodtofork.datasource.cache"
        sourceFolders = listOf("sqldelight")
    }
}

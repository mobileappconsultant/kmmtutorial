import org.jetbrains.kotlin.config.JvmAnalysisFlags.useIR

plugins {
    id(Plugins.androidApplication)
    kotlin(KotlinPlugins.android)
    kotlin(KotlinPlugins.kapt)
    kotlin(KotlinPlugins.serialization) version Kotlin.version
    id(Plugins.hilt)
}

android {
    compileSdk = Application.compileSdk
    defaultConfig {
        applicationId = Application.appId
        minSdk = Application.minSdk
        targetSdk = Application.targetSdk
        versionCode = Application.versionCode
        versionName = Application.versionName
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        // kotlinCompilerExtensionVersion = Compose.composeVersion
        kotlinCompilerExtensionVersion = "1.3.2"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

}

dependencies {
    implementation(project(":shared"))

    implementation(AndroidX.appCompat)


    implementation(Compose.runtime)
    implementation(Compose.runtimeLiveData)
    implementation(Compose.ui)
    implementation(Compose.material)
    implementation(Compose.uiTooling)
    implementation(Compose.foundation)
    //implementation(Compose.compiler)
    implementation("androidx.compose.compiler:compiler:1.3.2")
    implementation(Compose.constraintLayout)
    implementation(Compose.activity)
    implementation(Compose.navigation)
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.5.1")
    implementation(Compose.hilt_navigation)

    implementation ("com.google.code.gson:gson:2.9.0")
    implementation(Google.material)
    implementation(Hilt.hiltAndroid)
    implementation(Hilt.hiltNavigation)
    kapt(Hilt.hiltCompiler)

    implementation(Kotlinx.datetime)

    implementation(Ktor.android)
    implementation ("io.ktor:ktor-client-serialization:1.5.0")


    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")



    debugImplementation(SquareUp.leakCanary)

    // implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.0")

}

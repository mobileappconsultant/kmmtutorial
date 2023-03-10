object Compose {
    const val composeVersion = "1.0.1"
    const val hilt_compose_version = "1.0.0"
    const val runtime = "androidx.compose.runtime:runtime:${composeVersion}"
    const val runtimeLiveData = "androidx.compose.runtime:runtime-livedata:${composeVersion}"
    const val ui = "androidx.compose.ui:ui:${composeVersion}"
    const val material = "androidx.compose.material:material:${composeVersion}"
    const val uiTooling = "androidx.compose.ui:ui-tooling:${composeVersion}"
    const val foundation = "androidx.compose.foundation:foundation:${composeVersion}"
    const val compiler = "androidx.compose.compiler:compiler:${composeVersion}"

    private const val constraintLayoutComposeVersion = "1.0.1"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout-compose:${constraintLayoutComposeVersion}"

    private const val composeActivitiesVersion = "1.3.1"
    const val activity = "androidx.activity:activity-compose:${composeActivitiesVersion}"

    private const val composeNavigationVersion = "2.5.3"
    const val navigation = "androidx.navigation:navigation-compose:${composeNavigationVersion}"

    const val hilt_navigation = "androidx.hilt:hilt-navigation-compose:${hilt_compose_version}"
}

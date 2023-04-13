object Versions {

    // KTX
    const val CORE = "1.10.0"
    const val LIFECYCLE_RUNTIME = "2.6.1"

    // COMPOSE
    const val COMPOSE = "1.4.1"
    const val MATERIAL3 = "1.1.0-beta02"
    const val ACTIVITY = "1.7.0"

    // TEST
    const val JUNIT = "4.13.2"

    // Android Test
    const val ESPRESSO_CORE = "3.5.1"
    const val ANDROID_JUNIT = "1.1.5"
}

object KTX {
    const val CORE = "androidx.core:core-ktx:${Versions.CORE}"
    const val LIFECYCLE_RUNTIME = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.LIFECYCLE_RUNTIME}"
}

object Compose {
    const val UI = "androidx.compose.ui:ui:${Versions.COMPOSE}"
    const val PREVIEW = "androidx.compose.ui:ui-tooling-preview:${Versions.COMPOSE}"
    const val MATERIAL3 = "androidx.compose.material3:material3:${Versions.MATERIAL3}"
    const val ACTIVITY = "androidx.activity:activity-compose:${Versions.ACTIVITY}"
}

object UnitTest {
    const val JUNIT = "junit:junit:${Versions.JUNIT}"
}

object AndroidTest {
    const val ANDROID_JUNIT = "androidx.test.ext:junit:${Versions.ANDROID_JUNIT}"
    const val COMPOSE_JUNIT = "androidx.compose.ui:ui-test-junit4:${Versions.COMPOSE}"
    const val ESPRESSO_CORE = "androidx.test.espresso:espresso-core:${Versions.ESPRESSO_CORE}"
}

object Debug {
    const val COMPOSE_TOOLING = "androidx.compose.ui:ui-tooling:${Versions.COMPOSE}"
    const val COMPOSE_TEST_MANIFEST = "androidx.compose.ui:ui-test-manifest:${Versions.COMPOSE}"
}
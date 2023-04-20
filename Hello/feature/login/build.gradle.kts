import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    kotlin("android")
    id("com.android.library")
}
android {
    namespace = "com.purple.hello.feature.groups"
    compileSdk = AppConfig.compileSdk

    defaultConfig {
        minSdk = AppConfig.minSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
        manifestPlaceholders["kakaoApiKey"] = getApiKey("KAKAO_API_KEY")
        buildConfigField("String", "KAKAO_NATIVE_KEY", getApiKey("KAKAO_NATIVE_KEY"))
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = KotlinOptions.jvmTarget
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = ComposeOptions.kotlinCompilerExtensionVersion
    }
}

dependencies {
    implementation(project(":core:designsystem"))

    implementation(composeDependencies)
    implementation(appDependencies)

    debugImplementation(composeDebug)
    testImplementation(defaultUnitTest)
    androidTestImplementation(defaultAndroidTest)

    implementation(socialLogin)
}

fun getApiKey(propertyKey: String): String {
    return gradleLocalProperties(rootDir).getProperty(propertyKey)
}

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
}

android {
    compileSdk = 32

    defaultConfig {
        minSdk = 23
        targetSdk = 32

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.0"
    }
    namespace = "com.project.marvelapp.core.designsystem"
}

dependencies {
    implementation(Kotlin.COROUTINES_CORE)
    implementation(Kotlin.KOTLIN_STDLIB)

    //compose coil
    implementation(Libraries.COIL)
    implementation(Libraries.COIL_COMPOSE)

    //compose
    implementation(platform("androidx.compose:compose-bom:2023.01.00"))

    implementation(Compose.COMPOSE_UI)
    implementation(Compose.COMPOSE_MATERIAL)
    implementation(Compose.COMPOSE_MATERIAL_3)
    implementation(Compose.COMPOSE_MATERIAL_ICON_EXTENDED)
    implementation(Compose.COMPOSE_LIFECYCLE_RUNTIME)
    implementation(Compose.COMPOSE_UI_TOOLING_PREVIEW)
    debugImplementation(Compose.COMPOSE_UI_TOOLING_DEBUG)

    implementation(Compose.COMPOSE_LIFECYCLE_RUNTIME)
    implementation(Compose.COMPOSE_ACTIVITY)
}
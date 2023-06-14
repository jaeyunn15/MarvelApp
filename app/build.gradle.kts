plugins {
    id ("com.android.application")
    id ("org.jetbrains.kotlin.android")
    id ("kotlin-kapt")
    id ("dagger.hilt.android.plugin")
}

android {
    namespace = "com.project.marvelapp"
    compileSdk = 33

    defaultConfig {
        minSdk = Versions.MIN_SDK_VERSION
        targetSdk = Versions.TARGET_SDK_VERSION
        versionCode = Versions.VERSION_CODE
        versionName = Versions.VERSION_NAME

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    packagingOptions {
        exclude("META-INF/gradle/incremental.annotation.processors")
    }
}

dependencies {
    implementation(project(":feature:favorite"))
    implementation(project(":feature:search"))

    implementation(project(":core:designsystem"))
    implementation(project(":core:data"))
    implementation(project(":core:domain"))
    implementation(project(":core:testing"))

    implementation(AndroidX.CORE_KTX)
    implementation(AndroidX.APP_COMPAT)
    implementation(AndroidX.CONSTRAINTLAYOUT)
    implementation(AndroidX.FRAGMENT_KTX)
    implementation(AndroidX.LIFECYCLE_RUNTIME_KTX)
    implementation(AndroidX.LIFECYCLE_VIEWMODEL_KTX)
    implementation(AndroidX.LIFECYCLE_LIVEDATA_KTX)

    //Activity extension, referring with  "by viewModels"
    implementation(AndroidX.ACTIVITY_KTX)

    implementation(Google.MATERIAL)

    //retrofit + gson
    implementation(Libraries.RETROFIT)
    implementation(Libraries.RETROFIT_CONVERTER_GSON)

    //okhttp
    implementation(Libraries.OKHTTP)
    implementation(Libraries.OKHTTP_LOGGING_INTERCEPTOR)

    //Coroutines
    implementation(Kotlin.COROUTINES_ANDROID)

    //Glide
    implementation(Libraries.GLIDE)
    kapt (Libraries.KAPT_GLIDE_COMPILER)

    //Paging
    implementation(AndroidX.PAGING)

    //Hilt
    implementation(Google.HILT_ANDROID)
    kapt(Google.HILT_ANDROID_COMPILER)

    //preference
    implementation(AndroidX.PREFERENCE)

    //compose coil
    implementation(Libraries.COIL)

    //hilt
    implementation(Google.HILT_COMPOSE)
    implementation(Google.NAVIGATION_COMPOSE)

    implementation(Google.DATASTORE_CORE)
    implementation(Google.DATASTORE_PREFERENCE)

    implementation(Libraries.SPLASH_SCREEN)

    //compose
    implementation(platform("androidx.compose:compose-bom:2023.01.00"))

    implementation(Compose.COMPOSE_UI)
    implementation(Compose.COMPOSE_ANIMATION)
    implementation(Compose.COMPOSE_MATERIAL)
    implementation(Compose.COMPOSE_MATERIAL_3)
    implementation(Compose.COMPOSE_MATERIAL_ICON_EXTENDED)
    implementation(Compose.COMPOSE_LIFECYCLE_RUNTIME)
    implementation(Compose.COMPOSE_UI_TOOLING_PREVIEW)
    debugImplementation(Compose.COMPOSE_UI_TOOLING_DEBUG)

    implementation(Compose.COMPOSE_LIFECYCLE_RUNTIME)
    implementation(Compose.COMPOSE_ACTIVITY)

    implementation(Accompanist.NAVIGATION_ANIMATION)
}
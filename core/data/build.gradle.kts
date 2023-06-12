plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id ("dagger.hilt.android.plugin")
    id("kotlin-kapt")
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
    namespace = "com.project.marvelapp.core.data"
}

dependencies {
    implementation(project(":core:domain"))

    implementation(AndroidX.CORE_KTX)

    //kotlin
    implementation(Kotlin.KOTLIN_STDLIB)

    //Coroutines
    implementation(Kotlin.COROUTINES_ANDROID)

    //retrofit
    implementation(Libraries.RETROFIT)
    implementation(Libraries.RETROFIT_CONVERTER_GSON)

    //okHttp
    implementation(Libraries.OKHTTP)
    implementation(Libraries.OKHTTP_LOGGING_INTERCEPTOR)
    implementation(Libraries.OKHTTP_URLCONNECTION)

    //hilt
    implementation(Google.HILT_ANDROID)
    implementation(project(mapOf("path" to ":core:domain")))
    kapt(Google.HILT_ANDROID_COMPILER)

    implementation(AndroidX.PREFERENCE)

    testImplementation(UnitTest.JUNIT)
    testImplementation(UnitTest.MOCKK)
    testImplementation(UnitTest.KOTLIN_TEST)
    testImplementation(UnitTest.KOTLIN_TEST_JUNIT)
    testImplementation(UnitTest.CORE_TESTING)
    testImplementation(UnitTest.COROUTINE_TEST)
    androidTestImplementation(UnitTest.ANDROID_JUNIT_EXT)
}
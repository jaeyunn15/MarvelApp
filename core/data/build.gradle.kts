import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id ("dagger.hilt.android.plugin")
    id("kotlin-kapt")
}

android {
    compileSdk = Versions.COMPILE_SDK_VERSION

    defaultConfig {
        minSdk = Versions.MIN_SDK_VERSION
        targetSdk = Versions.TARGET_SDK_VERSION

        buildConfigField(
            "String",
            "PRIVATE_KEY",
            gradleLocalProperties(rootDir).getProperty("PRIVATE_KEY")
        )
        buildConfigField(
            "String",
            "PUBLIC_KEY",
            gradleLocalProperties(rootDir).getProperty("PUBLIC_KEY")
        )

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
    buildFeatures {
        buildConfig = true
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
    testImplementation(project(":core:testing"))

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
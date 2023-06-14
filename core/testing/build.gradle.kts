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
    namespace = "com.project.marvelapp.core.testing"
}

dependencies {
    implementation(Kotlin.COROUTINES_CORE)
    implementation(Kotlin.KOTLIN_STDLIB)
    implementation(Libraries.RETROFIT_CONVERTER_GSON)
    implementation("androidx.annotation:annotation-jvm:+")

    testImplementation(UnitTest.JUNIT)
    testImplementation(UnitTest.MOCKK)
    testImplementation(UnitTest.KOTLIN_TEST)
    testImplementation(UnitTest.KOTLIN_TEST_JUNIT)
    testImplementation(UnitTest.CORE_TESTING)
    testImplementation(UnitTest.COROUTINE_TEST)
    androidTestImplementation(UnitTest.ANDROID_JUNIT_EXT)

    implementation(project(":core:model"))
    implementation(project(":core:data"))
    implementation(project(":core:domain"))
}
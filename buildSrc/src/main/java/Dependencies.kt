object Versions {
    //kotlin
    const val KOTLIN_VERSION = "1.8.0"
    const val KOTLINX_COROUTINES = "1.5.2"

    //build
    const val BUILD_GRADLE = "4.2.1"
    const val COMPILE_SDK_VERSION = 33
    const val MIN_SDK_VERSION = 23
    const val TARGET_SDK_VERSION = 33
    const val VERSION_CODE = 1
    const val VERSION_NAME = "1.0.0"

    //androidx
    const val CORE_KTX = "1.8.0"
    const val APP_COMPAT = "1.4.0"
    const val LEGACY_V4_SUPPORT = "1.0.0"
    const val ACTIVITY_KTX = "1.4.0"
    const val FRAGMENT_KTX = "1.4.0"
    const val LIFECYCLE_KTX = "2.4.0"
    const val ROOM = "2.3.0"
    const val VIEWPAGER2 = "1.0.0"
    const val CONSTRAINTLAYOUT = "2.1.2"
    const val PREFERENCE = "1.1.1"
    const val RECYCLERVIEW = "1.2.1"
    const val RECYCLERVIEW_SELECTION = "1.2.0-alpha01"
    const val PAGING = "3.0.0"
    const val HILT_VIEWMODEL = "2.28-alpha"
    const val KAPT_HILT = "1.0.0-alpha02"

    //google
    const val HILT = "2.45"
    const val MATERIAL = "1.6.1"
    const val DATASTORE = "1.0.0"

    //libraries
    const val RETROFIT = "2.9.0"
    const val OKHTTP = "4.9.0"
    const val OKHTTP_LOGGING_INTERCEPTOR = "4.7.2"
    const val GLIDE = "4.11.0"
    const val RXJAVA = "2.2.8"
    const val RXANDROID = "2.1.1"
    const val STETHO = "1.6.0"
    const val SQLITE_JDBC = "3.34.0"
    const val COIL = "1.4.0"
    const val COIL_COMPOSE = "2.3.0"

    //test
    const val JUNIT = "4.13.2"
    const val ANDROID_JUNIT = "1.1.2"
    const val ESPRESSO_CORE = "3.3.0"
}

object Kotlin {
    const val KOTLIN_STDLIB      = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.KOTLIN_VERSION}"
    const val COROUTINES_CORE    = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.KOTLINX_COROUTINES}"
    const val COROUTINES_ANDROID = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.KOTLINX_COROUTINES}"
}

object AndroidX {
    const val CORE_KTX                = "androidx.core:core-ktx:${Versions.CORE_KTX}"
    const val APP_COMPAT              = "androidx.appcompat:appcompat:${Versions.APP_COMPAT}"

    const val LEGACY_SUPPORT_V4 = "androidx.legacy:legacy-support-v4:${Versions.LEGACY_V4_SUPPORT}"


    const val ACTIVITY_KTX            = "androidx.activity:activity-ktx:${Versions.ACTIVITY_KTX}"
    const val FRAGMENT_KTX            = "androidx.fragment:fragment-ktx:${Versions.FRAGMENT_KTX}"

    const val LIFECYCLE_VIEWMODEL_KTX = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.LIFECYCLE_KTX}"
    const val LIFECYCLE_LIVEDATA_KTX  = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.LIFECYCLE_KTX}"
    const val LIFECYCLE_RUNTIME  = "androidx.lifecycle:lifecycle-runtime:${Versions.LIFECYCLE_KTX}"
    const val LIFECYCLE_RUNTIME_KTX  = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.LIFECYCLE_KTX}"
    const val LIFECYCLE_COMMON  = "androidx.lifecycle:lifecycle-common:${Versions.LIFECYCLE_KTX}"
    const val LIFECYCLE_EXT =  "androidx.lifecycle:lifecycle-extensions:${Versions.LIFECYCLE_KTX}"

    const val ROOM_RUNTIME            = "androidx.room:room-runtime:${Versions.ROOM}"
    const val ROOM_KTX                = "androidx.room:room-ktx:${Versions.ROOM}"
    const val ROOM_COMPILER           = "androidx.room:room-compiler:${Versions.ROOM}"
    const val ROOM_RXJAVA2           = "androidx.room:room-rxjava2:${Versions.ROOM}"
    const val ROOM_TESTING           = "androidx.room:room-testing:${Versions.ROOM}"

    const val VIEWPAGER2           = "androidx.viewpager2:viewpager2:${Versions.VIEWPAGER2}"

    const val CONSTRAINTLAYOUT = "androidx.constraintlayout:constraintlayout:${Versions.CONSTRAINTLAYOUT}"

    const val PREFERENCE = "androidx.preference:preference-ktx:${Versions.PREFERENCE}"

    const val RECYCLERVIEW_SELECTION = "androidx.recyclerview:recyclerview-selection:${Versions.RECYCLERVIEW_SELECTION}"
    const val RECYCLERVIEW = "androidx.recyclerview:recyclerview:${Versions.RECYCLERVIEW}"

    const val PAGING = "androidx.paging:paging-runtime:${Versions.PAGING}"
    const val PAGING_TEST = "androidx.paging:paging-common:${Versions.PAGING}"
    const val PAGING_COMPOSE = "androidx.paging:paging-compose:1.0.0-alpha18"

    //For Hilt support for ViewModel
    const val HILT_VIEWMODEL = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.HILT_VIEWMODEL}"
    const val KAPT_HILT = "androidx.hilt:hilt-compiler:${Versions.KAPT_HILT}"
}

object Google {
    const val HILT_ANDROID          = "com.google.dagger:hilt-android:${Versions.HILT}"
    const val HILT_ANDROID_COMPILER = "com.google.dagger:hilt-android-compiler:${Versions.HILT}"

    const val HILT_COMPOSE = "androidx.hilt:hilt-navigation-compose:1.0.0"
    const val NAVIGATION_COMPOSE = "androidx.navigation:navigation-compose:2.5.3"

    const val MATERIAL = "com.google.android.material:material:${Versions.MATERIAL}"

    const val DATASTORE_CORE = "androidx.datastore:datastore-core:${Versions.DATASTORE}"
    const val DATASTORE_PREFERENCE = "androidx.datastore:datastore-preferences:${Versions.DATASTORE}"
}

object Libraries {
    const val RETROFIT                   = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT}"
    const val RETROFIT_CONVERTER_GSON    = "com.squareup.retrofit2:converter-gson:${Versions.RETROFIT}"

    const val OKHTTP                     = "com.squareup.okhttp3:okhttp:${Versions.OKHTTP}"
    const val OKHTTP_LOGGING_INTERCEPTOR = "com.squareup.okhttp3:logging-interceptor:${Versions.OKHTTP_LOGGING_INTERCEPTOR}"
    const val OKHTTP_URLCONNECTION = "com.squareup.okhttp3:okhttp-urlconnection:${Versions.OKHTTP}"

    const val GLIDE = "com.github.bumptech.glide:glide:${Versions.GLIDE}"
    const val GLIDE_OKHTTP_INTERCEPTOR = "com.github.bumptech.glide:okhttp3-integration:${Versions.GLIDE}"
    const val KAPT_GLIDE_COMPILER = "com.github.bumptech.glide:compiler:${Versions.GLIDE}"

    const val COIL = "io.coil-kt:coil:${Versions.COIL}"
    const val COIL_COMPOSE = "io.coil-kt:coil-compose:${Versions.COIL_COMPOSE}"
    const val COIL_SVG = "io.coil-kt:coil-svg:${Versions.COIL}"

    const val RXJAVA2_RXJAVA = "io.reactivex.rxjava2:rxandroid:${Versions.RXANDROID}"
    const val RXJAVA2_RXANDROID = "io.reactivex.rxjava2:rxjava:${Versions.RXJAVA}"

    const val STETHO = "com.facebook.stetho:stetho:${Versions.STETHO}"
    const val STETHO_OKHTTP = "com.facebook.stetho:stetho-okhttp3:${Versions.STETHO}"

    const val SQLITE_JDBC = "org.xerial:sqlite-jdbc:${Versions.SQLITE_JDBC}"

    const val SPLASH_SCREEN = "androidx.core:core-splashscreen:1.0.0"
}

object UnitTest {
    const val JUNIT         = "junit:junit:${Versions.JUNIT}"
}

object AndroidTest {
    const val ANDROID_JUNIT = "androidx.test.ext:junit:${Versions.ANDROID_JUNIT}"
    const val ESPRESSO_CORE = "androidx.test.espresso:espresso-core:${Versions.ESPRESSO_CORE}"
}

object Compose {
    const val COMPOSE_UI = "androidx.compose.ui:ui"
    const val COMPOSE_ANIMATION = "androidx.compose.animation:animation"
    const val COMPOSE_MATERIAL = "androidx.compose.material:material"
    const val COMPOSE_MATERIAL_3 = "androidx.compose.material3:material3"
    const val COMPOSE_MATERIAL_ICON_EXTENDED = "androidx.compose.material:material-icons-extended"
    const val COMPOSE_UI_TOOLING_PREVIEW = "androidx.compose.ui:ui-tooling-preview"
    const val COMPOSE_UI_TOOLING_DEBUG = "androidx.compose.ui:ui-tooling"

    const val COMPOSE_LIFECYCLE_RUNTIME = "androidx.lifecycle:lifecycle-runtime-compose:2.6.0-alpha01"
    const val COMPOSE_ACTIVITY = "androidx.activity:activity-compose:1.5.1"
}

object Accompanist {
    const val FLOW_LAYOUT = "com.google.accompanist:accompanist-flowlayout:0.31.1-alpha"
    const val NAVIGATION_ANIMATION = "com.google.accompanist:accompanist-navigation-animation:0.31.1-alpha"
}
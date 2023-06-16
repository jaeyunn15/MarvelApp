buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.0")
    }
}

plugins {
    id("com.android.application") version "7.4.1" apply false
    id("com.android.library") version "7.4.1" apply false
    id("org.jetbrains.kotlin.android") version "1.5.31" apply false
    id("com.google.dagger.hilt.android") version "2.45" apply false
    id("org.jetbrains.kotlin.jvm") version "1.5.31" apply false
}

apply {
    from("gradle/projectDependencyGraph.gradle")
}
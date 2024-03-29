plugins {
    id("org.jetbrains.compose")
    id("com.android.application")
    kotlin("android")
}

group = "ru.mishenko.maksim"
version = "1.0-SNAPSHOT"

repositories {
    jcenter()
}

dependencies {
    api(project(":common"))
    implementation("androidx.activity:activity-compose:1.5.0")

    //decompose
    implementation("com.arkivanov.decompose:decompose:2.0.2")
    implementation("com.arkivanov.decompose:extensions-android:2.0.2")
}

android {
    compileSdkVersion(33)
    defaultConfig {
        applicationId = "ru.mishenko.maksim.android"
        minSdkVersion(24)
        targetSdkVersion(33)
        versionCode = 1
        versionName = "1.0-SNAPSHOT"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}
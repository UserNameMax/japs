plugins {
    kotlin("kapt")
    kotlin("multiplatform")
    id("org.jetbrains.compose")
    id("com.android.library")
    id("kotlin-parcelize")
    //id("io.ktor.plugin")
}

group = "ru.mishenko.maksim"
version = "1.0-SNAPSHOT"

kotlin {
    android()
    jvm("desktop") {
        jvmToolchain(11)
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(compose.runtime)
                api(compose.foundation)
                api(compose.material)

                //ktor client
                api("io.ktor:ktor-client-core:2.3.4")
                api("io.ktor:ktor-client-cio:2.3.4")
                implementation("io.ktor:ktor-client-websockets:2.3.4")

                //ktor server
                api("io.ktor:ktor-server-core:2.3.4")
                api("io.ktor:ktor-server-cio:2.3.4")
                api("io.ktor:ktor-server-websockets:2.3.4")

                //decompose
                implementation("com.arkivanov.decompose:decompose:2.0.2")
                implementation("com.arkivanov.decompose:extensions-compose-jetbrains:2.0.2")

                //mviKotlin
                implementation( "com.arkivanov.mvikotlin:mvikotlin:3.2.1")
                implementation( "com.arkivanov.mvikotlin:mvikotlin-main:3.2.1")
                implementation( "com.arkivanov.mvikotlin:mvikotlin-extensions-coroutines:3.2.1")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))

                //testImplementation("io.ktor:ktor-server-tests-jvm:$ktor_version")
            }
        }
        val androidMain by getting {
            dependencies {
                api("androidx.appcompat:appcompat:1.5.1")
                api("androidx.core:core-ktx:1.9.0")
            }
        }
        val androidTest by getting {
            dependencies {
                implementation("junit:junit:4.13.2")
            }
        }
        val desktopMain by getting {
            dependencies {
                api(compose.preview)
            }
        }
        val desktopTest by getting
    }
}

android {
    compileSdkVersion(33)
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdkVersion(24)
        targetSdkVersion(33)
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

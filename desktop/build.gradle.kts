import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

group = "ru.mishenko.maksim"
version = "1.0-SNAPSHOT"


kotlin {
    jvm {
        jvmToolchain(11)
        withJava()
    }
    sourceSets {
        val jvmMain by getting {
            dependencies {
                api(project(":common"))
                implementation(compose.desktop.currentOs)

                //decompose
                implementation("com.arkivanov.decompose:decompose:2.0.2")
                implementation("com.arkivanov.decompose:extensions-compose-jetbrains:2.0.2")
            }
        }
        val jvmTest by getting
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "japs"
            packageVersion = "1.0.0"
        }
    }
}

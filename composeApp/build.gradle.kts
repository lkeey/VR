import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.jetbrains.kotlin.serialization)
    alias(libs.plugins.sqldelight)
}

kotlin {

    jvm("desktop") {
        compilations.all {
            kotlinOptions.jvmTarget = "17"
        }
    }

    jvmToolchain(17)
    
    sourceSets {
        val desktopMain by getting

        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtime.compose)

            implementation(libs.kotlinx.serialization.json)

            // db
            implementation("app.cash.sqldelight:runtime:2.0.2")
            implementation("app.cash.sqldelight:coroutines-extensions:2.0.2")

        }
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutines.swing)
            implementation("app.cash.sqldelight:sqlite-driver:2.0.2")
            implementation("org.xerial:sqlite-jdbc:3.47.0.0")

            // movie
//            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-swing:1.8.3")
            implementation("uk.co.caprica:vlcj:4.8.2")
        }
    }
}


compose.desktop {
    application {
        mainClass = "dev.vr.com.MainKt"

        nativeDistributions {
            targetFormats(
                TargetFormat.Dmg,
                TargetFormat.Msi,
                TargetFormat.Deb
            )
            packageName = "dev.vr.com"
            packageVersion = "2.1.0"


            buildTypes.release.proguard {
                isEnabled.set(false)
                configurationFiles.from(file("proguard-rules.pro"))
                optimize.set(false)
                obfuscate.set(false)
            }

            // Include required modules for vlcj (AWT/Swing, instrumentation, and unsupported)
            modules("java.sql", "java.naming", "java.instrument", "jdk.unsupported")
        }
    }
}

sqldelight {
    databases {
        create("VRDatabase") {
            packageName.set("dev.vr.com.db")
        }
    }
}

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

            // movie
//            implementation("uk.co.caprica:vlcj:4.7.0")

            // db
            implementation("app.cash.sqldelight:runtime:2.0.2")
            implementation("app.cash.sqldelight:coroutines-extensions:2.0.2")

        }
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutines.swing)
            implementation("app.cash.sqldelight:sqlite-driver:2.0.2")
            implementation("org.xerial:sqlite-jdbc:3.45.3.0")
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
            packageVersion = "2.0.2"

            buildTypes {
                release { }
            }

            modules("java.sql", "java.naming")
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

import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework
import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFrameworkTask

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
}

composeCompiler {
    version = libs.versions.compose.compiler.get()
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_1_8)
        }
    }

    val frameworkName = "shared"
    val xcf = XCFramework(frameworkName)
    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            binaryOption("bundleId", "io.github.ryunen344.droidkaigies.shared")
            baseName = frameworkName
            isStatic = false
            xcf.add(this)
        }
    }

    val copyToIosDir: XCFrameworkTask.() -> Unit = {
        val iosXcfDir = rootProject.layout.projectDirectory.dir("iosApp/Framework/$frameworkName.xcframework").asFile
        val spec: CopySpec.() -> Unit = {
            from(outputs.files.first())
            into(iosXcfDir)
        }
        doFirst {
            fileTree(iosXcfDir) { exclude(".gitkeep") }.forEach(File::delete)
        }
        val copyWork = copy(spec)
        doLast {
            if (!copyWork.didWork) {
                copy(spec)
            }
        }
    }

    tasks.getByName<XCFrameworkTask>("assembleSharedDebugXCFramework", copyToIosDir)
    tasks.getByName<XCFrameworkTask>("assembleSharedReleaseXCFramework", copyToIosDir)

    sourceSets {
        all {
            languageSettings {
                optIn("androidx.compose.material3.ExperimentalMaterial3Api")
                optIn("org.jetbrains.compose.resources.ExperimentalResourceApi")
            }
        }

        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.runtimeSaveable)
            implementation(compose.animation)
            implementation(compose.animationGraphics)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.lifecycle.viewmodel.compose)
            implementation(libs.navigation.compose)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }

        androidMain.dependencies {
            implementation(libs.compose.ui.tooling.preview)
        }
    }
}

android {
    namespace = "io.github.ryunen344.droidkaigies.shared"
    compileSdk = 34
    defaultConfig {
        minSdk = 23
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

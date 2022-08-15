plugins {
    id(Config.PluginIds.androidLibrary)
    id(Config.PluginIds.kotlinAndroid)
    id(Config.PluginIds.kapt)
    id(Config.PluginIds.hilt)
}

android {
    compileSdk = Config.Versions.androidCompileSdk
    namespace = Config.namespace(".home")

    defaultConfig {
        minSdk = Config.Versions.androidMinSdk
        targetSdk = Config.Versions.androidTargetSdk

        testInstrumentationRunner = Config.testRunner
        consumerProguardFiles(Config.ProGuard.consumerRules)
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile(Config.ProGuard.androidOptimize), Config.ProGuard.rules)
        }
    }

    viewBinding {
        isEnabled = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
}

dependencies {
    implementation(project(Modules.domain))
    implementation(project(Modules.Commons.android))
    implementation(project(Modules.Commons.kotlin))

    // UI
    implementation(Dependencies.AndroidX.lifecycleComponents)
    implementation(Dependencies.AndroidX.lifecycleDefaultComponents)
    implementation(Dependencies.AndroidX.constraintLayout)
    implementation(Dependencies.Ui.materialComponents)
    implementation(Dependencies.Ui.groupie)
    implementation(Dependencies.Ui.groupieViewBinding)

    implementation(Dependencies.Ktx.fragment)
    implementation(Dependencies.Ktx.core)
    implementation(Dependencies.Ktx.activity)

    // Hilt
    implementation(Dependencies.DI.dagger)
    kapt(Dependencies.DI.androidCompiler)
    implementation(Dependencies.DI.lifecycleViewModel)
    kapt(Dependencies.DI.compiler)

    // Other
    implementation(Dependencies.Other.coroutines)
    implementation(Dependencies.Other.insetter)
}
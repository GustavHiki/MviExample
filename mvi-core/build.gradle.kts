plugins {
    id(Config.PluginIds.androidLibrary)
    id(Config.PluginIds.kotlinAndroid)
    id(Config.PluginIds.kapt)
}

android {
    compileSdk = Config.Versions.androidCompileSdk
    namespace = Config.namespace(".mvi_core")

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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
//    implementation(project(Modules.Commons.android))
    implementation(project(Modules.Commons.kotlin))
    implementation(project(Modules.domain))

    // AndroidX
    implementation(Dependencies.AndroidX.annotation)
    implementation(Dependencies.AndroidX.lifecycleComponents)
    implementation(Dependencies.AndroidX.lifecycleDefaultComponents)

    implementation(Dependencies.Ui.materialComponents)

    implementation(Dependencies.Ktx.fragment)
    implementation(Dependencies.Ktx.core)

    // Hilt
    implementation(Dependencies.DI.dagger)
    kapt(Dependencies.DI.androidCompiler)
    implementation(Dependencies.DI.lifecycleViewModel)
    kapt(Dependencies.DI.compiler)

    // Other
    implementation(Dependencies.Other.coroutines)
    implementation(Dependencies.Debugging.timber)
    implementation(Dependencies.Other.insetter)

    // Glide
    implementation(Dependencies.Ui.glide)
    kapt(Dependencies.Ui.glideAnnotationProcessor)

    implementation(Dependencies.Debugging.timber)
    // Groupie
    implementation(Dependencies.Ui.groupie)
    implementation(Dependencies.Ui.groupieViewBinding)
}
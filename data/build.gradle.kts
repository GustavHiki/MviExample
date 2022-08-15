plugins {
    id(Config.PluginIds.androidLibrary)
    id(Config.PluginIds.kotlinAndroid)
    id(Config.PluginIds.kapt)
}

android {
    compileSdk = Config.Versions.androidCompileSdk
    namespace = Config.namespace(".data")

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
}

dependencies {
    implementation(project(Modules.domain))
    implementation(project(Modules.Commons.kotlin))

    // Other
    implementation(Dependencies.DI.inject)
    implementation(Dependencies.DI.dagger)
    implementation(Dependencies.Other.coroutines)
}
plugins {
    id(Config.PluginIds.android)
    id(Config.PluginIds.kotlinAndroid)
    id(Config.PluginIds.kapt)
    id(Config.PluginIds.hilt)
}

android {
    compileSdk = Config.Versions.androidCompileSdk
    namespace = Config.namespace()

    defaultConfig {
        applicationId = Config.applicationId

        vectorDrawables.useSupportLibrary = true

        minSdk = Config.Versions.androidMinSdk
        targetSdk = Config.Versions.androidTargetSdk

        versionCode = Config.Versions.androidVersionCode
        versionName = Config.Versions.androidVersionName

        resourceConfigurations.addAll(mutableSetOf("en", "ru"))

        testInstrumentationRunner = Config.testRunner
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }

    buildTypes {
        getByName("debug") {
            applicationIdSuffix = ".dev"
            isMinifyEnabled = false
        }
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile(Config.ProGuard.androidOptimize), Config.ProGuard.rules)
        }
    }

    applicationVariants.all {
        outputs.all {
            val project = "mvi_example"
            val buildType = buildType.name
            (this as com.android.build.gradle.internal.api.BaseVariantOutputImpl).outputFileName = "${project}_${buildType}.apk"
        }
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(Modules.data))
    implementation(project(Modules.domain))
    implementation(project(Modules.Commons.android))
    implementation(project(Modules.Commons.kotlin))
    implementation(project(Modules.Presentation.home))

    // UI
    implementation(Dependencies.AndroidX.lifecycleComponents)
    implementation(Dependencies.AndroidX.lifecycleDefaultComponents)
    implementation(Dependencies.AndroidX.constraintLayout)
    implementation(Dependencies.Ui.materialComponents)

    implementation(Dependencies.Ktx.core)
    implementation(Dependencies.Ktx.activity)
    implementation(Dependencies.Ktx.fragment)

    // Retrofit
    implementation(Dependencies.Retrofit.retrofit)
//    implementation(Dependencies.Retrofit.converterGson)

    // Debugging
    implementation(Dependencies.Debugging.timber)
    implementation(Dependencies.Debugging.okhttp)

    // Hilt
    implementation(Dependencies.DI.dagger)
    kapt(Dependencies.DI.androidCompiler)
    implementation(Dependencies.DI.lifecycleViewModel)
    kapt(Dependencies.DI.compiler)

    // Debugging
    implementation(Dependencies.Debugging.timber)
    debugImplementation(Dependencies.Debugging.leakcanary)

    // Other
    implementation(Dependencies.Other.coroutines)
    implementation(Dependencies.Other.cicerone)
    implementation(Dependencies.Other.insetter)

    // Test
    testImplementation(Dependencies.Test.junit)
    androidTestImplementation(Dependencies.Test.androidJunit)
}
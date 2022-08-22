object Config {
    const val testRunner = "androidx.test.runner.AndroidJUnitRunner"
    const val applicationId = "ru.geckolab.mviexample"

    fun namespace(pcg: String = ""): String = applicationId + pcg

    object Versions {
        const val androidMinSdk = 23
        const val androidTargetSdk = 31
        const val androidCompileSdk = 31
        const val androidVersionCode = 100000152
        const val androidVersionName = "2.1.0"

        const val hiltVersion = "2.40.5"
    }

    object Dependencies {
        private const val gradle = "7.2.1"
        private const val kotlinVersion = "1.6.10"

        const val gradleAndroid = "com.android.tools.build:gradle:$gradle"
        const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"

        const val hilt = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hiltVersion}"
    }

    object PluginIds {
        const val android = "com.android.application"
        const val androidLibrary = "com.android.library"
        const val javaLibrary = "java-library"
        const val kapt = "kotlin-kapt"
        const val hilt = "dagger.hilt.android.plugin"
        const val kotlinAndroid = "org.jetbrains.kotlin.android"
        const val kotlinJvm = "org.jetbrains.kotlin.jvm"
    }

    object ProGuard {
        const val androidOptimize = "proguard-android-optimize.txt"
        const val rules = "proguard-rules.pro"
        const val consumerRules = "consumer-rules.pro"
    }
}
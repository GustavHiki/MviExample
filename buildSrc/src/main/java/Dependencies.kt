object Dependencies {

    object Ui {
        private const val materialComponentsVersion = "1.5.0"
        private const val glideVersion = "4.12.0"
        private const val groupieVersion = "2.10.0"

        const val groupie = "com.github.lisawray.groupie:groupie:$groupieVersion"
        const val groupieViewBinding = "com.github.lisawray.groupie:groupie-viewbinding:$groupieVersion"

        const val glide = "com.github.bumptech.glide:glide:$glideVersion"
        const val glideAnnotationProcessor = "com.github.bumptech.glide:compiler:$glideVersion"

        const val materialComponents = "com.google.android.material:material:$materialComponentsVersion"
    }

    object AndroidX {
        private const val lifecycleComponentsVersion = "2.2.0"
        private const val constraintLayoutVersion = "2.1.3"
        private const val swipeRefreshLayoutVersion = "1.1.0"
        private const val annotationVersion = "1.3.0"

        const val lifecycleComponents = "androidx.lifecycle:lifecycle-extensions:$lifecycleComponentsVersion"
        const val lifecycleDefaultComponents = "androidx.lifecycle:lifecycle-common-java8:$lifecycleComponentsVersion"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:$constraintLayoutVersion"
        const val swipeRefreshLayout = "androidx.swiperefreshlayout:swiperefreshlayout:$swipeRefreshLayoutVersion"
        const val annotation = "androidx.annotation:annotation:$annotationVersion"
    }

    object Room {
        private const val roomVersion = "2.4.1"

        const val runtime = "androidx.room:room-runtime:$roomVersion"
        const val compiler = "androidx.room:room-compiler:$roomVersion"
        const val ktx = "androidx.room:room-ktx:$roomVersion"
    }

    object Ktx {
        private const val ktxVersion = "1.7.0"
        private const val ktxActivityVersion = "1.4.0"
        private const val ktxFragmentVersion = "1.4.1"

        const val activity = "androidx.activity:activity-ktx:$ktxActivityVersion"
        const val core = "androidx.core:core-ktx:$ktxVersion"
        const val fragment = "androidx.fragment:fragment-ktx:$ktxFragmentVersion"
    }

    object Retrofit {
        private const val retrofitVersion = "2.9.0"

        const val retrofit = "com.squareup.retrofit2:retrofit:$retrofitVersion"
        const val converterMoshi = "com.squareup.retrofit2:converter-moshi:$retrofitVersion"
    }

    object DI {
        private const val hiltVersion = Config.Versions.hiltVersion
        private const val lifecycleViewModelVersion = "1.0.0-alpha03"
        private const val compilerVersion = "1.0.0"
        private const val injectVersion = "1"
        private const val hiltWorkManagerVersion = "1.0.0"

        const val dagger = "com.google.dagger:hilt-android:$hiltVersion"
        const val androidCompiler = "com.google.dagger:hilt-android-compiler:$hiltVersion"
        const val lifecycleViewModel = "androidx.hilt:hilt-lifecycle-viewmodel:$lifecycleViewModelVersion"
        const val compiler = "androidx.hilt:hilt-compiler:$compilerVersion"
        const val inject = "javax.inject:javax.inject:$injectVersion"
        const val hiltWorkManager = "androidx.hilt:hilt-work:$hiltWorkManagerVersion"
    }

    object Test {
        private const val jUnitVersion = "4.13.2"
        private const val androidJUnitVersion = "1.1.3"

        const val junit = "junit:junit:$jUnitVersion"
        const val androidJunit = "androidx.test.ext:junit:$androidJUnitVersion"
    }

    object Other {
        private const val ciceroneVersion = "7.1"
        private const val insetterVersion = "0.6.1"
        private const val localDateVersion = "1.3.1"
        private const val coroutinesVersion = "1.6.0"

        const val cicerone = "com.github.terrakok:cicerone:$ciceroneVersion"
        const val insetter = "dev.chrisbanes.insetter:insetter:$insetterVersion"
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion"
    }

    object Debugging {
        private const val okhttpVersion = "4.9.1"
        private const val timberVersion = "5.0.1"
        private const val remoteDebuggerVersion = "1.1.2"
        private const val leakcanaryVersion = "2.8.1"

        const val okhttp = "com.squareup.okhttp3:logging-interceptor:$okhttpVersion"
        const val timber = "com.jakewharton.timber:timber:$timberVersion"
        const val remoteDebugger = "com.github.zerobranch.android-remote-debugger:debugger:$remoteDebuggerVersion"
        const val leakcanary = "com.squareup.leakcanary:leakcanary-android:$leakcanaryVersion"
    }
}
plugins {
    id(Config.PluginIds.javaLibrary)
    id(Config.PluginIds.kotlinJvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

dependencies {
    implementation(project(Modules.Commons.kotlin))

    implementation(Dependencies.DI.inject)
    implementation(Dependencies.Other.coroutines)
}
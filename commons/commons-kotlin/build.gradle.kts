plugins {
    id(Config.PluginIds.javaLibrary)
    id(Config.PluginIds.kotlinJvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

dependencies {
    implementation(Dependencies.Other.coroutines)
}
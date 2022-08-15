buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Config.Dependencies.gradleAndroid)
        classpath(Config.Dependencies.kotlin)
        classpath(Config.Dependencies.hilt)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")
    }
}

task<Delete>("clean") {
    delete(rootProject.buildDir)
}
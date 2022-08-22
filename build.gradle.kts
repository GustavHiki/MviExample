buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Config.Dependencies.gradleAndroid)
        classpath(Config.Dependencies.kotlin)
        classpath(Config.Dependencies.hilt)
    }
}

task<Delete>("clean") {
    delete(rootProject.buildDir)
}
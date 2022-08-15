@file:Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven(url = java.net.URI.create("https://jitpack.io"))
    }
}

rootProject.buildFileName = "build.gradle.kts"
rootProject.name = "MviExample"
include(":app")
include(":data")
include(":domain")
include(":commons:commons-android")
include(":commons:commons-kotlin")

include(":presentation:home")
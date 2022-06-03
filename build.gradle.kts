buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Libs.gradle)
        classpath(Libs.Kotlin.gradlePlugin)
        classpath(Libs.Kotlin.Serialization.common)
        classpath(Libs.SqlDelight.gradlePlugin)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

plugins {
    id(Plugins.ANDROID_APPLICATION)
    kotlin(Plugins.KOTLIN_ANDROID)
}

android {
    defaultConfig {
        applicationId = App.id
        versionCode = App.Version.code
        versionName = App.Version.name

        minSdk = Sdk.Version.min
        targetSdk = Sdk.Version.target

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    compileSdk = Sdk.Version.compile

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Libs.Android.Compose.version
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation(project(":shared"))
    implementation(Libs.Android.core)
    implementation(Libs.Android.lifecycle)
    implementation(Libs.Android.Compose.activity)
    implementation(Libs.Android.Compose.runtime)
    implementation(Libs.Android.Compose.compiler)
    implementation(Libs.Android.Compose.ui)
    implementation(Libs.Android.Compose.material)
    implementation(Libs.Android.Compose.constrainLayout)
    implementation(Libs.Android.Compose.paging)
    implementation(Libs.Android.Compose.navigation)
    implementation(Libs.Android.Compose.materialIcons)
}
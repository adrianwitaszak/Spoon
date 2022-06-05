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
    with(Libs.Android) {
        implementation(core)
        implementation(lifecycle)
    }
    with(Libs.Android.Compose) {
        implementation(activity)
        implementation(runtime)
        implementation(compiler)
        implementation(ui)
        implementation(material)
        implementation(constrainLayout)
        implementation(paging)
        implementation(navigation)
        implementation(materialIcons)
    }
    with(Libs.Koin) {
        implementation(android)
        implementation(compose)
    }
}
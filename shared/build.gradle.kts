plugins {
    kotlin(Plugins.KOTLIN_MULTIPLATFORM)
    kotlin(Plugins.COCAPODS)
    kotlin(Plugins.SERIALIZATION)
    id(Plugins.ANDROID_LIBRARY)
    id(Plugins.SQL_DELIGHT)
}

version = App.Version.name

kotlin {
    android()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(Libs.Kermit.common)
                implementation(Libs.Kotlin.coroutines)
                implementation(Libs.Kotlin.Serialization.core)
                with(Libs.Ktor) {
                    implementation(core)
                    implementation(cio)
                    implementation(logging)
                    implementation(contentNegotiation)
                    implementation(serializationJson)
                }
                implementation(Libs.Koin.core)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(Libs.Ktor.ktorOkhttp)
                implementation(Libs.SqlDelight.Driver.android)
            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
                implementation(Libs.Ktor.ktorClientIOS)
                implementation(Libs.SqlDelight.Driver.native)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
                with(Libs.KotlinTest) {
                    implementation(mockk)
                    implementation(koinTest)
                    implementation(ktorMock)
                }
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

android {
    compileSdk = Sdk.Version.compile
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = Sdk.Version.min
        targetSdk = Sdk.Version.target
    }
}

sqldelight {
    database("spoon_database±") {
        packageName = App.id
    }
}

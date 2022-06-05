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
                api(Libs.Koin.core)
                with(Libs.Kotlin) {
                    implementation(coroutines)
                    implementation(serializationCore)
                }
                with(Libs.SqlDelight) {
                    implementation(runtime)
                    implementation(driverSqlite)
                    implementation(coroutines)
                }
                with(Libs.Ktor) {
                    implementation(core)
                    implementation(cio)
                    implementation(logging)
                    implementation(contentNegotiation)
                    implementation(serializationJson)
                }
            }
        }
        val androidMain by getting {
            dependsOn(commonMain)
            dependencies {
                api(Libs.SqlDelight.driverAndroid)
                implementation(Libs.Ktor.ktorOkhttp)
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
                api(Libs.SqlDelight.driverNative)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
                with(Libs.KotlinTest) {
                    api(mockk)
                    api(koinTest)
                    implementation(ktorMock)
                }
            }
        }
        val androidTest by getting {
            dependsOn(commonTest)
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
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
    database("SpoonDatabase") {
        packageName = App.id
    }
}

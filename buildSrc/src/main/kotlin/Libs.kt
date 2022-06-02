object Libs {

    const val gradle = "com.android.tools.build:gradle:7.4.0-alpha02"

    object Kotlin {
        private const val version = "1.6.21"
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2"
        const val dateTime = "org.jetbrains.kotlinx:kotlinx-datetime:0.3.1"

        object Serialization {
            const val common = "org.jetbrains.kotlin:kotlin-serialization:$version"
            const val json = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2"
        }
    }

    object Koin {
        const val version = "3.2"
        const val core = "io.insert-koin:koin-core:$version"
        const val android = "io.insert-koin:koin-android:$version"
    }

    object Ktor {
        private const val version = "2.0.0"
        const val ktorClient = "io.ktor:ktor-client-core:$version"
        const val ktorClientIOS = "io.ktor:ktor-client-ios:$version"
        const val ktorOkhttp = "io.ktor:ktor-client-okhttp:$version"

    }

    object SqlDelight {
        private const val version = "1.5.3"
        const val gradlePlugin = "com.squareup.sqldelight:gradle-plugin:$version"
        const val coroutines = "com.squareup.sqldelight:coroutines-extensions:$version"

        object Driver {
            const val android = "com.squareup.sqldelight:android-driver:$version"
            const val sqlite = "com.squareup.sqldelight:sqlite-driver:$version"
            const val native = "com.squareup.sqldelight:native-driver:$version"
        }
    }

    object Kermit {
        private const val version = "1.0.0"
        const val common = "co.touchlab:kermit:$version"
    }

    object GradleVersions {
        const val version = "0.39.0"
        const val plugin = "com.github.ben-manes.versions"
    }

    object Settings {
        private const val version = "0.8.1"
        const val common = "com.russhwolf:multiplatform-settings:$version"
        const val coroutines = "com.russhwolf:multiplatform-settings-coroutines:$version"
    }

    object JetbrainsCompose {
        const val version = "1.1.0-alpha1-dev550"
    }

    object Android {
        const val core = "androidx.core:core-ktx:1.8.0"
        const val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:2.4.1"

        object Compose {
            const val version = "1.2.0-beta03"
            const val activity = "androidx.activity:activity-compose:1.3.1"
            const val compiler = "androidx.compose.compiler:compiler:$version"
            const val runtime = "androidx.compose.runtime:runtime:$version"
            const val ui = "androidx.compose.ui:ui:$version"
            const val constrainLayout =
                "androidx.constraintlayout:constraintlayout-compose:1.0.0-beta02"
            const val material = "androidx.compose.material:material:$version"
            const val materialIcons =
                "androidx.compose.material:material-icons-extended:$version"
            const val navigation =
                "androidx.navigation:navigation-compose:2.4.2"
            const val paging = "androidx.paging:paging-compose:1.0.0-alpha15"
        }
    }

    object KotlinTest {
        const val koinTest = "io.insert-koin:koin-test:${Koin.version}"
        const val koinJunit = "io.insert-koin:koin-test-junit4:${Koin.version}"
        const val truthish = "com.varabyte.truthish:truthish:0.6.3"
    }
}
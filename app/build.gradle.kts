plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.rome.tech.horoscapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.rome.tech.horoscapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "0.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            release {
                isMinifyEnabled = false
                isDebuggable = false
                resValue("string","varsion_app_name", "HoroscApp")
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
                )
            }
            //no olvidar ultimo slash
            buildConfigField("String", "BASE_URL", "\"https://newastro.vercel.app/\"")
        }
        //no olvidar ultimo slash
        getByName("debug") {
            isDebuggable = true
            resValue("string","varsion_app_name", "HoroscApp - debug")
            buildConfigField("String", "BASE_URL", "\"https://newastro.vercel.app/\"")
        }


    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_18
        targetCompatibility = JavaVersion.VERSION_18
    }
    kotlinOptions {
        jvmTarget = "18"
    }

    // View binding
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }

    viewBinding { enable = true }

    kotlin {
        jvmToolchain(18)
    }
}

//kapt {
//    correctErrorTypes = true
//}

dependencies {
    val navVersion = "2.7.3"
    val dagger = "2.48"

    // NavComponent
    implementation("androidx.navigation:navigation-fragment-ktx:$navVersion")
    implementation("androidx.navigation:navigation-ui-ktx:$navVersion")

    // Dagger - hilt -> inyeccion de dependencias
    // https://mvnrepository.com/artifact/com.google.dagger/hilt-android
    implementation("com.google.dagger:hilt-android:$dagger")
    kapt("com.google.dagger:hilt-compiler:$dagger")

    // Retrofit -> API Rest
    // https://mvnrepository.com/artifact/com.squareup.retrofit2/retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    // interceptor
    implementation("com.squareup.okhttp3:logging-interceptor:4.3.1")

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}

kapt {
    correctErrorTypes = true
}
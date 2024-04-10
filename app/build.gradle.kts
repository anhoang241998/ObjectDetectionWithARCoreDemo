plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.androidDaggerHilt)
    alias(libs.plugins.kspDevTools)
}

android {
    namespace = "com.s3corp.objectdetectionwitharcoredemo"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.s3corp.objectdetectionwitharcoredemo"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.11"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    // Kotlin
    implementation(libs.kt.coroutine.core)

    // Android
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.viewmodel.saveState)
    implementation(libs.android.coroutine)
    implementation(libs.androidx.datastore.preferences)

    // Room
    implementation(libs.androidx.room)
    ksp(libs.androidx.room.compiler)

    // jetpack compose
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.foundation.layout)
    implementation(libs.androidx.compose.ripple)
    implementation(libs.androidx.compose.window.size)
    implementation(libs.androidx.compose.saveable)
    implementation(libs.androidx.compose.icon.core)
    implementation(libs.androidx.compose.icon.extended)
    implementation(libs.androidx.compose.util)
    implementation(libs.androidx.compose.viewmodel.lifecycle)
    implementation(libs.androidx.compose.lifecycle.runtime)
    implementation(libs.androidx.compose.animation)
    implementation(libs.androidx.compose.animation.core)
    implementation(libs.androidx.compose.animation.graphics)

    // Navigation
    implementation(libs.androidx.compose.navigation)

    // Dagger hilt
    implementation(libs.dagger.hilt.android)
    implementation(libs.dagger.hilt.android.gradlePlugin)
    ksp(libs.dagger.compiler)
    ksp(libs.dagger.hilt.compiler)
    ksp(libs.dagger.hilt.android.compiler)

    // Test
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
import org.jetbrains.kotlin.kapt3.base.Kapt.kapt
import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.muhammedhassaan.yaganews"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.muhammedhassaan.yaganews"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        val properties = Properties()
        properties.load(project.rootProject.file("local.properties").inputStream())

        buildConfigField("String","NEWS_API_KEY", properties.getProperty("NEWS_API_KEY"))
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_18
        targetCompatibility = JavaVersion.VERSION_18
    }

    kotlinOptions {
        jvmTarget = "18"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.compose.bom))



    implementation(libs.bundles.compose)



    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.junit4)
    debugImplementation(libs.compose.ui.tooling)
    debugImplementation(libs.compose.test.manifest)

    implementation(project(":data"))
    implementation(project(":domain"))

    //ViewModel
    implementation(libs.androidx.lifecycle.viewmodel.compose)

    //Coil
    implementation(libs.coil.compose)

    //Navigation
    implementation(libs.androidx.navigation.compose)

    //Coroutines
    implementation(libs.kotlinx.coroutines.android)

    //Retrofit
    implementation (libs.bundles.retrofit)

    //Moshi
    implementation(libs.converter.moshi)
    implementation(libs.moshi)
    implementation (libs.moshi.kotlin)


    // Dependency Injection
    implementation(libs.bundles.hilt)
    kapt(libs.hilt.android.compiler)


    //Room Database
    implementation (libs.bundles.room)
    annotationProcessor (libs.androidx.room.compiler)
    kapt (libs.androidx.room.room.compiler)

}
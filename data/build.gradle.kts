import java.util.Properties


plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    kotlin("kapt")
}

android {
    namespace = "com.muhammedhassaan.data"
    compileSdk = 33

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        val properties = Properties()
        properties.load(project.rootProject.file("local.properties").inputStream())

        buildConfigField("String","NEWS_API_KEY", properties.getProperty("NEWS_API_KEY"))
    }

    buildFeatures{
        buildConfig = true
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
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(project(":domain"))

    //Retrofit
    implementation (libs.bundles.retrofit)

    //Moshi
    implementation(libs.converter.moshi)
    implementation(libs.moshi)
    implementation (libs.moshi.kotlin)


    //Coroutines
    implementation(libs.kotlinx.coroutines.android)

    //Room Database
    implementation (libs.bundles.room)
    annotationProcessor (libs.androidx.room.compiler)
    kapt (libs.androidx.room.room.compiler)

    // Dependency Injection
    implementation(libs.bundles.hilt)
    kapt(libs.hilt.android.compiler)

}
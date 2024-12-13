
plugins {
    id("com.android.application")
    id("kotlin-android")
}

android {
    namespace = "dev.trindadedev.lixhub.re"
    compileSdk = 35
    
    defaultConfig {
        applicationId = "dev.trindadedev.lixhub.re"
        minSdk = 21
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
        
        vectorDrawables { 
            useSupportLibrary = true
        }
    }
    
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    buildFeatures {
        viewBinding = true
        
    }
    
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions.jvmTarget = "17"
}

dependencies {
  implementation("androidx.constraintlayout:constraintlayout:2.1.4")
  implementation("com.google.android.material:material:1.12.0")
  implementation("androidx.appcompat:appcompat:1.6.1")
    
  implementation("com.github.bumptech.glide:glide:4.16.0")
	implementation("androidx.credentials:credentials:1.5.0-beta01")
	implementation("androidx.credentials:credentials-play-services-auth:1.5.0-alpha05")
}

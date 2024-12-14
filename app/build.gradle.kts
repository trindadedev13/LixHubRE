import org.jetbrains.kotlin.gradle.dsl.JvmTarget
plugins {
  alias(libs.plugins.android.app)
  alias(libs.plugins.kotlin)
}

android {
  namespace = "dev.trindadedev.lixhub"
  compileSdk = libs.versions.android.compileSdk.get().toInt()
  
  defaultConfig {
    minSdk = libs.versions.android.minSdk.get().toInt()
    targetSdk = libs.versions.android.targetSdk.get().toInt()
    applicationId = "dev.trindadedev.lixhub"
    versionCode = 1
    versionName = "1.0"
    
    vectorDrawables.useSupportLibrary = true
  }
    
  compileOptions {
    sourceCompatibility = JavaVersion.toVersion(libs.versions.android.jvm.get().toInt())
    targetCompatibility = JavaVersion.toVersion(libs.versions.android.jvm.get().toInt())
  }

  signingConfigs {
    create("release") {
      // temporary keystore
      storeFile = file(layout.buildDirectory.dir("../release_key.jks"))
      storePassword = "release_temp"
      keyAlias = "release_temp"
      keyPassword = "release_temp"
    }
    getByName("debug") {
      storeFile = file(layout.buildDirectory.dir("../testkey.keystore"))
      storePassword = "testkey"
      keyAlias = "testkey"
      keyPassword = "testkey"
    }
  }
    
  buildTypes {
    release {
      isMinifyEnabled = true
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
      signingConfig = signingConfigs.getByName("release")
    }
  }

  buildFeatures {
    viewBinding = true
  }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
  compilerOptions {
    jvmTarget = JvmTarget.JVM_17
  }
}

dependencies {
  implementation(libs.google.material)
  implementation(libs.androidx.appcompat)
  implementation(libs.bumptech.glide)
	implementation(libs.androidx.credentials)
	implementation(libs.androidx.credentials.play.services.auth)
}

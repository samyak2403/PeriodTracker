plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.arrowwould.periodtracker"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.arrowwould.periodtracker"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        resourceConfigurations += setOf("en")
        multiDexEnabled = true

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    implementation ("com.github.bumptech.glide:glide:5.0.5")
    implementation ("com.google.code.gson:gson:2.13.2")
    implementation ("com.kizitonwose.calendar:view:2.6.0")
    implementation ("org.threeten:threetenbp:1.7.2")
    implementation ("com.airbnb.android:lottie:5.2.0")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

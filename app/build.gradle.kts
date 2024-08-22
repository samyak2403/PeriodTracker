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
        resConfigs("en")
        multiDexEnabled = true

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    bundle {
        density {
            enableSplit = true
        }
        abi {
            enableSplit = true
        }
        language {
            enableSplit = false
        }
    }

    buildTypes {
        release {
            // Disable debugging
            isDebuggable = false

            // Enable code minification (ProGuard or R8)
            isMinifyEnabled = true

            // Enable resource shrinking
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        debug {
            // Enable debugging
            isDebuggable = true

            // Enable code minification (ProGuard or R8) for the debug build
            isMinifyEnabled = true

            // Enable resource shrinking
            isShrinkResources = true

            // ProGuard/R8 rules files
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        buildFeatures {
            dataBinding = true
        }
    }

}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(libs.appcompat)

    implementation(libs.material)

//    implementation(libs.activity)

    implementation(libs.constraintlayout)


    implementation ("com.github.bumptech.glide:glide:4.9.0")

    implementation ("com.google.code.gson:gson:2.8.5")



    implementation ("com.haibin:calendarview:3.6.8")

    implementation ("org.threeten:threetenbp:0.7.2")
    implementation ("com.airbnb.android:lottie:5.2.0")

//    implementation ("com.google.android.gms:play-services-ads:21.3.0")
}
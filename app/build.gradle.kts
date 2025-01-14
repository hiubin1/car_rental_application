import com.android.build.api.dsl.ViewBinding

plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.pjthuexeapp"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.pjthuexeapp"
        minSdk = 27
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

    buildFeatures{
       viewBinding { true }
    }


    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

}



dependencies {


    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.appcompat:appcompat:1.6.1")
    testImplementation("junit:junit:4.13.2")
    implementation ("de.hdodenhof:circleimageview:3.1.0")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation("androidx.cardview:cardview:1.0.0")
//    implementation("gun0912.ted:tedbottompicker:3.3.0")
//
//    implementation("io.github.ParkSangGwon:tedpermission:3.3.0")
//    implementation("io.github.ParkSangGwon:tedpermission-coroutine:3.3.0")
//    implementation("io.github.ParkSangGwon:tedpermission-rx2:3.3.0")
//    implementation("io.github.ParkSangGwon:tedpermission-rx2:3.3.0")

}
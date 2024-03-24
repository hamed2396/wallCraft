plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    //kapt
    kotlin("kapt")
    //nav component
    id("androidx.navigation.safeargs.kotlin")
    //hilt
    id("com.google.dagger.hilt.android")
    //parcelable
    id("kotlin-parcelize")
}

android {
    viewBinding.enable = true
    buildFeatures.buildConfig = true
    namespace = "com.example.wallcraft"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.wallcraft"
        minSdk = 21
        targetSdk = 34
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    //extensions
    val utilsVersion = "2.2.10"
    implementation("com.github.FunkyMuse.KAHelpers:recyclerview:$utilsVersion")
    implementation("com.github.FunkyMuse.KAHelpers:kotlinextensions:$utilsVersion")

    //retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("com.squareup.okhttp3:okhttp:4.11.0")

    //OkHTTP client
    implementation("com.squareup.okhttp3:okhttp:4.11.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")

    //coil
    implementation("io.coil-kt:coil:2.3.0")
    val room_version = "2.5.2"
    implementation("androidx.room:room-runtime:$room_version")
    annotationProcessor("androidx.room:room-compiler:$room_version")
    implementation("androidx.room:room-ktx:$room_version")
    kapt("androidx.room:room-compiler:$room_version")

    //navigation component
    val navVersion = "2.6.0"
    implementation("androidx.navigation:navigation-fragment-ktx:$navVersion")
    implementation("androidx.navigation:navigation-ui-ktx:$navVersion")

    //hilt
    implementation("com.google.dagger:hilt-android:2.44")
    kapt("androidx.hilt:hilt-compiler:1.0.0")
    kapt("com.google.dagger:hilt-android-compiler:2.44")
    val paging_version = "3.1.1"
    implementation("androidx.paging:paging-runtime:$paging_version")

    //dataStore
    implementation("androidx.datastore:datastore-preferences:1.0.0")
    val lifecycle_version = "2.6.1"
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation("com.github.MrNouri:DynamicSizes:1.0")
    implementation("com.github.skydoves:androidveil:1.1.3")
   implementation ("com.github.skydoves:colorpickerview:2.3.0")
    implementation("com.flaviofaria:kenburnsview:1.0.7")
    implementation ("com.robertlevonyan.components:PermissionsFlow:1.2.6")
    implementation ("com.github.MrNouri:RotateView:1.0.0")
    implementation("com.facebook.shimmer:shimmer:0.5.0")
    implementation("com.todkars:shimmer-recyclerview:0.4.1")
    implementation ("com.github.ybq:Android-SpinKit:1.4.0")
   implementation ("com.github.Dhaval2404:ColorPicker:2.3")
}
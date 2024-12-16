plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("com.google.gms.google-services")
    id("signing")
    id("com.vanniktech.maven.publish") version "0.30.0"
}

android {
    namespace = "com.roshan.themebuilder"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
        vectorDrawables {
            useSupportLibrary = true
        }
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
        kotlinCompilerExtensionVersion = "1.5.1"
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
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.ui.text.google.fonts)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation(platform("com.google.firebase:firebase-bom:33.7.0"))
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.firebase:firebase-firestore")
    implementation ("com.google.firebase:firebase-database")
    implementation (libs.play.services.base)

    // Testing
    androidTestImplementation (libs.androidx.navigation.testing)



    implementation(libs.coil.compose)

    implementation (libs.gson)
}

mavenPublishing {
    coordinates("io.github.roshansharma824", "themebuilder", "1.0.1")

    pom {
        name.set("Theme Builder")
        description.set("A description of what my library does.")
        inceptionYear.set("2024")
        url.set("https://github.com/roshansharma824/News-App-Theme/")
        licenses {
            license {
                name.set("The Apache License, Version 2.0")
                url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                distribution.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
            }
        }
        developers {
            developer {
                id.set("roshansharma824")
                name.set("Roshan Sharma")
                url.set("https://github.com/roshansharma824/")
            }
        }
        scm {
            url.set("https://github.com/roshansharma824/News-App-Theme/")
            connection.set("scm:git:git://github.com/roshansharma824/News-App-Theme.git")
            developerConnection.set("scm:git:ssh://git@github.com/roshansharma824/News-App-Theme.git")
        }
    }

    println((findProperty("signing.secretKeyRingFile") as String))

    signing {

        useInMemoryPgpKeys(
            findProperty("signing.keyId") as String?,
            findProperty("signing.secretKeyRingFile") as String?,
            findProperty("signing.password") as String?)
        useGpgCmd()
        sign(publishing.publications)
    }
}

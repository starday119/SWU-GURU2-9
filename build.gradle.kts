
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.3" apply false
    id ("com.android.library") version "8.1.3" apply false
    id ("org.jetbrains.kotlin.android") version "1.8.20" apply false
    id ("com.google.android.libraries.mapsplatform.secrets-gradle-plugin") version "2.0.1" apply false
    id("com.google.gms.google-services") version "4.3.15" apply false
}
buildscript {
    repositories {
        google()  // Google의 Maven 저장소 추가
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.3.1")
        classpath("com.google.gms:google-services:4.4.0")
        // 기타 종속성
    }
}

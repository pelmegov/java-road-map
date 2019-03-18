import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.21"
}

group = "ru.pelmegov"
version = "0.0.1.SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))


    implementation("org.springframework.boot:spring-boot-starter:2.1.3.RELEASE")
    implementation("org.springframework.boot:spring-boot-starter-web:2.1.3.RELEASE")


    implementation("com.spotify:docker-client:8.15.1")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}
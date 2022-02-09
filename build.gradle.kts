import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val springBootVersion = "2.5.9"
val springfoxVersion = "3.0.0"

plugins {
	id("org.springframework.boot") version "2.5.9"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.5.32"
	kotlin("plugin.spring") version "1.5.32"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web:$springBootVersion")
	implementation("org.springframework.boot:spring-boot-starter-cache:$springBootVersion")
	implementation("org.springframework.boot:spring-boot-starter-validation:$springBootVersion")
	implementation("org.springframework.boot:spring-boot-starter-data-redis:$springBootVersion")
	implementation("org.springframework.boot:spring-boot-starter-data-mongodb:$springBootVersion")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("io.github.microutils:kotlin-logging:2.1.21")
	implementation("org.modelmapper:modelmapper:3.0.0")
	implementation("io.springfox:springfox-swagger2:${springfoxVersion}")
	implementation("io.springfox:springfox-swagger-ui:${springfoxVersion}")
	implementation("io.springfox:springfox-boot-starter:${springfoxVersion}")
	testImplementation("org.springframework.boot:spring-boot-starter-test:$springBootVersion")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

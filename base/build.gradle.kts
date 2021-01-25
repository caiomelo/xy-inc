import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.5.0-M1"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("org.asciidoctor.convert") version "1.5.8"
    id("jacoco")

    kotlin("jvm") version "1.4.21-2"
    kotlin("plugin.spring") version "1.4.21-2"
}

group = "com.xyinc"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
    maven { url = uri("https://repo.spring.io/milestone") }
}

val snippetsDir by extra { file("build/generated-snippets") }

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("de.flapdoodle.embed:de.flapdoodle.embed.mongo")
    testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
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

tasks.test {
    outputs.dir(snippetsDir)
    finalizedBy(tasks.jacocoTestReport)

    configure<JacocoTaskExtension> {
        excludes = listOf("**/Application.kt")
    }
}

tasks.asciidoctor {
    inputs.dir(snippetsDir)
    dependsOn(tasks.test)
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
}

tasks.withType<JacocoReport> {
    afterEvaluate {
        classDirectories.setFrom(files(classDirectories.files.map {
            fileTree(it).apply {
                exclude("**/Application*")
            }
        }))
    }
}
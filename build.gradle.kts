plugins {
    id("org.springframework.boot") version "2.7.1"
    id("io.spring.dependency-management") version "1.0.15.RELEASE"
    kotlin("jvm") version "1.6.21"
    kotlin("plugin.spring") version "1.6.21"
}


group = "br.com.coradini.loans"
version = "0.0.1"
java.sourceCompatibility = JavaVersion.VERSION_17


java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

repositories {
    mavenCentral()
}

dependencies {

    // Spring
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-validation") // Para validações, se necessário
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")


    // Flyway
    implementation("org.flywaydb:flyway-core:8.5.11")

    //Postgres
    implementation("org.postgresql:postgresql")
    implementation("io.r2dbc:r2dbc-postgresql:0.8.6.RELEASE") // Check for the latest version
    implementation("org.springframework.boot:spring-boot-starter-data-r2dbc") // Para uso com R2DBC


    // Jackson for Kotlin
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    // Reactor Kotlin Extensions
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")

    // Kotlin dependencies
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")

    // Testing dependencies
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.projectreactor:reactor-test")

    // Swagger dependencies
    implementation("io.springfox:springfox-boot-starter:3.0.0")
    implementation("io.springfox:springfox-swagger-ui:3.0.0")

    //implementation("org.springframework.boot:spring-boot-starter-actuator")
    //implementation("org.springframework.boot:spring-boot-starter-data-redis")

    //implementation("org.springframework.boot:spring-boot-starter-web")


    //Docker
    //developmentOnly("org.springframework.boot:spring-boot-docker-compose:3.1.1")





    //implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    //implementation("io.springfox:springfox-boot-starter:3.0.0")
    //implementation("io.springfox:springfox-swagger-ui:3.0.0")
    //implementation("org.springdoc:springdoc-openapi-webflux-ui:1.6.5")
    //implementation("org.springdoc:springdoc-openapi-ui:1.6.15")
    //implementation("org.springdoc:springdoc-openapi-webflux-ui:1.6.15")
    //implementation("org.springdoc:springdoc-openapi-data-rest:1.6.15")
    //implementation("org.springdoc:springdoc-openapi-kotlin:1.6.15")

    runtimeOnly("io.micrometer:micrometer-registry-prometheus")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.projectreactor:reactor-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

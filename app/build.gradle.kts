plugins {
    id("org.jetbrains.kotlin.jvm") version "1.9.23"
    id("org.jetbrains.kotlin.plugin.spring") version "1.9.23"
    id("org.springframework.boot") version "3.2.5"
    id("io.spring.dependency-management") version "1.1.5"
    id("org.graalvm.buildtools.native") version "0.10.2"
}

group = "dev.umbum"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("io.github.classgraph:classgraph:4.8.174")
    implementation(project(":external"))
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}

graalvmNative {
    binaries.all {
        resources.autodetect()
//        val reflectConfigPath1 = file("src/main/resources/native-config/reflect-config-snake.json").absolutePath
//        val reflectConfigPath2 = file("src/main/resources/native-config/reflect-config-dto.json").absolutePath
//        val reflectConfigPath3 = file("src/main/resources/native-config/reflect-config-caffeine.json").absolutePath
//        buildArgs.add("-H:ReflectionConfigurationFiles=$reflectConfigPath1")
//        buildArgs.add("-H:ReflectionConfigurationFiles=$reflectConfigPath2")
//        buildArgs.add("-H:ReflectionConfigurationFiles=$reflectConfigPath3")
    }
}

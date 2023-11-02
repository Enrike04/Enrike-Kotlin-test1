import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.9.20"
    id("io.gitlab.arturbosch.detekt") version "1.23.3"
    id("org.cqfn.diktat.diktat-gradle-plugin") version "1.2.3"
}

group = "org.jetbrains.edu"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.junit.jupiter:junit-jupiter-api:5.9.0")
    runtimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.0")
    implementation("org.junit.jupiter:junit-jupiter-params:5.9.0")
    runtimeOnly("org.junit.platform:junit-platform-console:1.9.0")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

detekt {
    buildUponDefaultConfig = true
    allRules = false
    config.setFrom("$projectDir/config/detekt.yml")
}

diktat {
    inputs {
        include("src/**/*.kt")
        exclude("src/test/**")
    }
    diktatConfigFile = file("$projectDir/config/diktat.yml")
}

tasks.register("diktat") {
    group = "verification"
    dependsOn(tasks.getByName("diktatCheck"))
}

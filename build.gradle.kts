group = "org.jetbrains.edu"
version = "1.0"

plugins {
    kotlin("jvm") version "1.9.20"
    id("io.gitlab.arturbosch.detekt") version "1.23.3"
    id("org.cqfn.diktat.diktat-gradle-plugin") version "1.2.3"
}

kotlin {
    jvmToolchain(8)
}

allprojects {
    apply {
        plugin("java")
        plugin("kotlin")
        plugin("io.gitlab.arturbosch.detekt")
    }


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
}

diktat {
    inputs {
        include("oop/src/**/*.kt")
        include("basic/src/**/*.kt")
        exclude("oop/src/test/**")
        exclude("basic/src/test/**")
    }
    diktatConfigFile = file("$projectDir/config/diktat.yml")
}

tasks.register("diktat") {
    group = "verification"
    dependsOn(tasks.getByName("diktatCheck"))
}

tasks.getByName("detekt") {
    dependsOn(":basic:detekt")
    dependsOn(":oop:detekt")
}

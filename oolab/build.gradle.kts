plugins {
    id("application")
    id("java")
    id("org.openjfx.javafxplugin") version "0.0.13"
}

application {
    getMainClass().set("agh.ics.oop.World")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

javafx {
    version = "17"
    modules("javafx.base", "javafx.controls", "javafx.fxml", "javafx.graphics", "javafx.media", "javafx.swing", "javafx.web")
}

dependencies {
    implementation("junit:junit:4.13.1")
    implementation("junit:junit:4.13.1")
    implementation("junit:junit:4.13.1")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}
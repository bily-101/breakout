import java.util.Properties


buildscript {
    repositories {
        mavenCentral()
        jcenter()
    }
}

plugins {
    kotlin("jvm") version "1.4.0"
}

val localProperties = Properties()
val localPropertiesFile: File = rootProject.file("artifactory.properties")
if (localPropertiesFile.exists()) {
    localPropertiesFile.inputStream().use { localProperties.load(it) }
}


repositories {
    val artifactory_contextUrl: String by localProperties
    val artifactory_username: String by localProperties
    val artifactory_password: String by localProperties

    jcenter()

    maven(url="https://dl.bintray.com/anysolo/edu")
    maven(url="https://jitpack.io")


    maven {
        url = uri("${artifactory_contextUrl}/gradle-dev")
        credentials {
            username = artifactory_username
            password = artifactory_password
        }
    }

}

dependencies {
    compile(gradleApi())
    compile(kotlin("stdlib"))
    compile(kotlin("stdlib-jdk8"))
    compile(kotlin("reflect"))
    compile("com.anysolo:toyGraphics:dev-v2-0.1.2")
    implementation("org.junit.jupiter:junit-jupiter-api:5.4.2")
    //compile("com.anysolo:prg-b1-course-solutions:0.2.3")
    compile("com.github.JensPiegsa:jfugue:5.0.9")


    testImplementation("org.junit.jupiter:junit-jupiter-api:5.4.2")
    testRuntime("org.junit.jupiter:junit-jupiter-engine:5.4.2")
}
tasks.named<Test>("test") {
    useJUnitPlatform()
}

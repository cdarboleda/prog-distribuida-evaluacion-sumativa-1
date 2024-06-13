plugins {
    id("java")
    id("application")
}

group = "com.distribuida"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.apache.openwebbeans:openwebbeans-impl:4.0.2")
    implementation("org.eclipse.persistence:eclipselink:4.0.3")

    implementation("io.helidon.webserver:helidon-webserver:4.0.9")
    implementation("org.xerial:sqlite-jdbc:3.46.0.0")
    implementation ("com.fasterxml.jackson.core:jackson-databind:2.14.0")

}

tasks.test {
    useJUnitPlatform()
}


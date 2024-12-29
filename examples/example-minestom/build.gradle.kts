
plugins {
    id("conventions.example")
    application
}

indra {
    javaVersions {
        target(21)
    }
}

dependencies {
    /* Cloud */
    implementation(project(":cloud-minestom"))
    implementation(libs.cloud.annotations)
    implementation(project(":cloud-minecraft-extras"))
    /* Minestom */
    implementation(libs.minestom)
    implementation(libs.slf4j.simple)
    /* Annotation processing */
    annotationProcessor(libs.cloud.annotations)
}

application {
    mainClass.set("org.incendo.cloud.examples.minestom.MinestomServer")
}

plugins {
    id("conventions.base")
    id("conventions.publishing")
}

java {
    disableAutoTargetJvm()
}

dependencies {
    api(libs.cloud.core)
    compileOnly(libs.minestom)
    javadocLinks(libs.minestom) {
        isTransitive = false
    }
}

configurations.javadocLinksSources {
    exclude("net.minestom")
}

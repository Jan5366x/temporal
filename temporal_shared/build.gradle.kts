
dependencies {
    testImplementation("org.junit.platform", "junit-platform-launcher", project.extra.get("jUnitPlatformVersion").toString())
    testImplementation("org.junit.jupiter", "junit-jupiter-api", project.extra.get("jUnitVersion").toString())
    testImplementation("org.junit.jupiter", "junit-jupiter-engine", project.extra.get("jUnitVersion").toString())
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks {
    /*
    // Developer Note:
    // this task require a project wide "classes" task first and don't work with parallel execution
    // the current work around is to run separate gradle commands like:
    // gradle classes
    // gradle fatJar
    val fatJar = task("fatJar", type = Jar::class) {
        archiveName = "temporal_shared.jar"
        from(configurations.compileClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
        with(jar as CopySpec)
    }

    val jar by getting(Jar::class) {
        archiveName = "temporal_shared.jar"
    }

    */
}


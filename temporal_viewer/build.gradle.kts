import java.text.SimpleDateFormat

dependencies {
    implementation(project(":temporal_shared"))

    implementation("net.sf.trove4j", "trove4j", project.extra.get("troveVersion").toString())

    implementation("org.openjfx:javafx-base:" +  project.extra.get("openJfxSpecificVersion").toString())
    implementation("org.openjfx:javafx-graphics:" + project.extra.get("openJfxSpecificVersion").toString())
    implementation("org.openjfx:javafx-controls:" + project.extra.get("openJfxSpecificVersion").toString())
    implementation("org.openjfx:javafx-fxml:" + project.extra.get("openJfxSpecificVersion").toString())

    testImplementation("org.reactfx", "reactfx", project.extra.get("reactfxVersion").toString())

    testImplementation("org.junit.platform", "junit-platform-launcher", project.extra.get("jUnitPlatformVersion").toString())
    testImplementation("org.junit.jupiter", "junit-jupiter-api", project.extra.get("jUnitVersion").toString())
    testImplementation("org.junit.jupiter", "junit-jupiter-engine", project.extra.get("jUnitVersion").toString())
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    options.compilerArgs.addAll(arrayOf(
            "--module-path", project.extra.get("openJfxPath").toString(),
            "--add-modules", "javafx.base",
            "--add-modules", "javafx.controls",
            "--add-modules", "javafx.fxml",
            "--add-modules", "javafx.web",
            "--add-modules", "javafx.graphics",
            "--add-modules", "javafx.media"))
}

tasks.withType<Javadoc> {
    val opts = options as StandardJavadocDocletOptions
    opts.addStringOption("-module-path", project.extra.get("openJfxPath").toString())
    opts.addStringOption("-add-modules", "javafx.base,javafx.controls,javafx.fxml,javafx.web,javafx.graphics,javafx.media")
}

tasks {
    /*
    // Developer Note:
    // this task require a project wide "classes" task first and don't work with parallel execution
    // the current work around is to run separate gradle commands like:
    // gradle classes
    // gradle fatJar
    val fatJar = task("fatJar", type = Jar::class) {

        archiveName = "temporal_viewer.jar"

        manifest {
            attributes["Main-Class"] = "de.novacyb.temporal.viewer.MainApp"
        }

        from(configurations.compileClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
        with(jar as CopySpec)
    }

    val jar by getting(Jar::class) {
        archiveName = "temporal_viewer.jar"

        manifest {
            attributes["Main-Class"] = "de.novacyb.temporal.viewer.MainApp"
        }
    }
    */
}



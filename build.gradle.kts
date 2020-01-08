plugins {
    java
    id("java-library")
    maven
    idea
    id("org.openjfx.javafxplugin") version "0.0.7"
}

allprojects {
    group = "de.novacyb.temporal"
    version = "0.1.3.0-SNAPSHOT"


    // version config
    val openJfxVersion = "13.0.1"
    val reactfxVersion = "2.0-M5"
    val troveVersion = "3.0.3"
    val jUnitVersion = "5.4.2"
    val jUnitPlatformVersion = "1.4.2"


    // os selector
    val currentOS = org.gradle.internal.os.OperatingSystem.current()
    var platform = ""
    if (currentOS.isWindows()) {
        platform = "win"
    } else if (currentOS.isLinux()) {
        platform = "linux"
    } else if (currentOS.isMacOsX()) {
        platform = "mac"
    }


    // version configuration
    extra.apply{
        set("openJfxPath", System.getenv("PATH_TO_FX"))
        set("openJfxVersion", openJfxVersion)
        set("openJfxSpecificVersion", "$openJfxVersion:$platform")
        set("reactfxVersion",reactfxVersion)
        set("troveVersion", troveVersion)
        set("jUnitVersion", jUnitVersion)
        set("jUnitPlatformVersion", jUnitPlatformVersion)
    }

    repositories {
        mavenCentral()
    }
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "maven")
    apply(plugin = "idea")
    apply(plugin = "org.openjfx.javafxplugin")
    apply(plugin = "java-library")

    idea {
        module {
            isDownloadJavadoc = true
            isDownloadSources = true
        }
    }

    javafx {
        modules("javafx.controls", "javafx.fxml", "javafx.graphics")
    }

    tasks {
        "test"(Test::class) {
            // enable JUnit Platform (a.k.a. JUnit 5) support
            useJUnitPlatform()

            // show standard out and standard error of the test JVM(s) on the console
            testLogging.showStandardStreams = true

            // set heap size for the test JVM(s)
            minHeapSize = "128m"
            maxHeapSize = "512m"

            // Fail the 'test' task on the first test failure
            failFast = false
        }
    }
}
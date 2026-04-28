plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "2.1.0"
    id("org.jetbrains.intellij.platform") version "2.7.1"
}

group = "com.mb.training"
version = "0.2.0"

repositories {
    mavenCentral()
    intellijPlatform {
        defaultRepositories()
    }
}

dependencies {
    testImplementation(kotlin("test-junit5"))
    testImplementation("junit:junit:4.13.2")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    intellijPlatform {
        create("IC", "2025.1.4.1")
        bundledPlugin("com.intellij.java")
        bundledPlugin("org.jetbrains.idea.maven")
        bundledPlugin("org.jetbrains.plugins.terminal")
    }
}

intellijPlatform {
    pluginConfiguration {
        changeNotes = """
            <ul>
              <li>Rework command interface.</li>
            </ul>
        """.trimIndent()
        ideaVersion {
            sinceBuild = "251"
        }
    }
}

intellijPlatformTesting {
    runIde.register("runIdeWithKaratePack") {
        plugins {
            val karatePackDependency =
                project.dependencies.project(mapOf("path" to ":karate-pack")) as org.gradle.api.artifacts.ProjectDependency
            localPlugin(karatePackDependency)
        }
        task {
            group = "intellij platform"
            description = "Run IDE sandbox with MBTraining Engine Framework + MBTraining Karate Pack."
        }
    }
}

tasks {
    withType<JavaCompile> {
        sourceCompatibility = "21"
        targetCompatibility = "21"
    }

    named<org.jetbrains.intellij.platform.gradle.tasks.RunIdeTask>("runIde") {
        group = "intellij platform"
        description = "Run IDE sandbox with MBTraining Engine Framework only."
    }

    named("buildSearchableOptions") {
        enabled = false
    }

    test {
        useJUnitPlatform()
    }

    register("unitTest") {
        group = "verification"
        description = "Run core MBTraining Engine Framework unit tests."
        dependsOn("test")
    }

    register("openTestIde") {
        group = "intellij platform"
        description = "Open IntelliJ IDEA test sandbox with MB Training plugin installed."
        dependsOn("runIde")
    }
}

kotlin {
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_21)
    }
}

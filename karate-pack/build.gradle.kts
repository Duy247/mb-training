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
    compileOnly(project(":"))

    intellijPlatform {
        create("IC", "2025.1.4.1")
        bundledPlugin("com.intellij.java")
        bundledPlugin("org.jetbrains.idea.maven")
    }
}

intellijPlatform {
    pluginConfiguration {
        changeNotes = """
            <ul>
              <li>Adapt to new command interface.</li>
            </ul>
        """.trimIndent()
        ideaVersion {
            sinceBuild = "251"
        }
    }
}

tasks {
    withType<JavaCompile> {
        sourceCompatibility = "21"
        targetCompatibility = "21"
    }

    named<org.jetbrains.intellij.platform.gradle.tasks.RunIdeTask>("runIde") {
        enabled = false
        description = "Disabled. Use root task `runIdeWithKaratePack` to run Engine + Karate Pack together."
    }

    named("buildSearchableOptions") {
        enabled = false
    }
}

kotlin {
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_21)
    }
}

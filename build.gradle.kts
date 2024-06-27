import java.net.URI

plugins {
    `maven-publish`
    `version-catalog`
}

group = "com.lapzupi.dev"
version = "0.0.1-SNAPSHOT"

catalog {
    versionCatalog {
        library("paper-api", "io.papermc.paper:paper-api:1.20.4-R0.1-SNAPSHOT")
        library("vault-api", "com.github.MilkBowl:VaultAPI:1.7.1")
        library("placeholder-api", "me.clip:placeholderapi:2.11.6")
        library("commands-paper","co.aikar:acf-paper:0.5.1-SNAPSHOT")
        library("nbt-api", "de.tr7zw:item-nbt-api:2.13.1")
        library("itemsadder-api", "com.github.LoneDev6:api-itemsadder:3.6.1")
        library("triumph-gui", "dev.triumphteam:triumph-gui:3.1.10")
        library("caffeine", "com.github.ben-manes.caffeine:caffeine:3.1.8")

        bundle("lapzupi-utils", listOf("lapzupi-config", "lapzupi-files", "lapzupi-connection"))
        library("lapzupi-config","com.github.Lapzupi:LapzupiConfig:1.1.2")
        library("lapzupi-files", "com.github.Lapzupi:LapzupiFiles:1.0.4")
        library("lapzupi-connection", "com.github.Lapzupi:LapzupiFiles:1.0.4")

        library("lapzupi-currency", "com.github.Lapzupi:LapzupiCurrency:1.3.2.0")

        library("more-paper-lib", "space.arim.morepaperlib:morepaperlib:0.4.3")

        version("flyway", "10.15.0")
        library("flyway-core", "org.flywaydb","flyway-core").versionRef("flyway")
        library("flyway-mysql", "org.flywaydb","flyway-mysql").versionRef("flyway")
        bundle("flyway", listOf("flyway-core", "flyway-mysql"))

        version("adventure", "4.17.0")
        library("adventure-api", "net.kyori","adventure-api").versionRef("adventure")
        library("adventure-minimessage", "net.kyori", "adventure-text-minimessage").versionRef("adventure")
        library("adventure-bukkit", "net.kyori:adventure-platform-bukkit:4.3.3")
        bundle("adventure", listOf("adventure-api", "adventure-minimessage", "adventure-bukkit"))

        library("configurate-yaml", "org.spongepowered:configurate-yaml:4.1.2")
        library("configurate-hocon", "org.spongepowered:configurate-hocon:4.1.2")
        library("luckperms", "net.luckperms:api:5.4")

        library("bstats", "org.bstats:bstats-bukkit:3.0.2")

        plugin("shadow", "io.github.goooler.shadow").version("8.1.7")
        plugin("plugin-yml-bukkit", "net.minecrell.plugin-yml.bukkit").version("0.6.0")
    }
}


publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["versionCatalog"])
        }
    }

    repositories {
        maven {
            val releaseUri = URI.create("https://repo.codemc.io/repository/maven-releases/")
            val snapshotUri = URI.create("https://repo.codemc.io/repository/maven-snapshots/")
            val version: String = project.version as String

            url = if (version.endsWith("SNAPSHOT")) snapshotUri else releaseUri

            val mavenUsername =  System.getenv("GRADLE_PROJECT_MAVEN_USERNAME") ?: null
            val mavenPassword =  System.getenv("GRADLE_PROJECT_MAVEN_PASSWORD") ?: null

            if(mavenUsername != null && mavenPassword != null) {
                credentials {
                    username = mavenUsername
                    password = mavenPassword
                }
            }
        }
    }
}
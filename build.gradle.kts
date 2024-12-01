import java.net.URI

plugins {
    `maven-publish`
    `version-catalog`
}

group = "com.lapzupi.dev"
version = "0.0.6"

catalog {
    versionCatalog {
        library("paper-api", "io.papermc.paper:paper-api:1.21.1-R0.1-SNAPSHOT")
        library("vault-api", "com.github.MilkBowl:VaultAPI:1.7.1")
        library("placeholder-api", "me.clip:placeholderapi:2.11.6")
        library("commands-paper","co.aikar:acf-paper:0.5.1-SNAPSHOT") //deprecated, lets use CommandsAPI instead
        library("nbt-api", "de.tr7zw:item-nbt-api:2.14.0")
        library("itemsadder-api", "com.github.LoneDev6:api-itemsadder:3.6.3-beta-14")
        library("triumph-gui", "dev.triumphteam:triumph-gui:3.1.10")

        library("caffeine", "com.github.ben-manes.caffeine:caffeine:3.1.8")

        bundle("lapzupi-utils", listOf("lapzupi-config", "lapzupi-connection", "lapzupi-files"))
        library("lapzupi-config", "com.github.Lapzupi:LapzupiConfig:1.2.1")
        library("lapzupi-connection", "com.github.Lapzupi:LapzupiConnection:1.1.1")
        library("lapzupi-files", "com.github.Lapzupi:LapzupiFiles:1.1.0")

        library("lapzupi-currency", "com.github.Lapzupi:LapzupiCurrency:1.3.2.2")

        library("more-paper-lib", "space.arim.morepaperlib:morepaperlib:0.4.4")

        version("flyway", "11.0.0")
        library("flyway-core", "org.flywaydb","flyway-core").versionRef("flyway")
        library("flyway-mysql", "org.flywaydb","flyway-mysql").versionRef("flyway")
        bundle("flyway", listOf("flyway-core", "flyway-mysql"))

        version("adventure", "4.17.0")
        library("adventure-api", "net.kyori","adventure-api").versionRef("adventure")
        library("adventure-minimessage", "net.kyori", "adventure-text-minimessage").versionRef("adventure")
        library("adventure-bukkit", "net.kyori:adventure-platform-bukkit:4.3.4")
        bundle("adventure", listOf("adventure-api", "adventure-minimessage", "adventure-bukkit"))

        //included with lapzupi-config. But until we get the libraries loader working, we still need to download these
        version("configurate", "4.2.0-SNAPSHOT")
        library("configurate-hocon", "org.spongepowered","configurate-hocon").versionRef("configurate")
        library("configurate-gson", "org.spongepowered","configurate-gson").versionRef("configurate")
        library("configurate-yaml", "org.spongepowered","configurate-yaml").versionRef("configurate")

        library("luckperms", "net.luckperms:api:5.4")

        library("bstats", "org.bstats:bstats-bukkit:3.1.0")

        plugin("shadow", "com.gradleup.shadow").version("8.3.5")
        plugin("bukkit-yml", "net.minecrell.plugin-yml.bukkit").version("0.6.0")

        library("mockbukkit", "com.github.seeseemelk:MockBukkit-v1.21:3.133.2")

        version("junit", "5.11.3")
        library("junit-jupiter-api", "org.junit.jupiter", "junit-jupiter-api").versionRef("junit")
        library("junit-jupiter-engine", "org.junit.jupiter", "junit-jupiter-engine").versionRef("junit")
        bundle("junit", listOf("junit-jupiter-api", "junit-jupiter-engine"))
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
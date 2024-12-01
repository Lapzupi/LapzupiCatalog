# LapzupiCatalog

Usage:

```kotlin
repositories {
    maven("https://repo.codemc.io/repository/maven-public/")
}
```

```kotlin
versionCatalogs {
    create("libs") {
        from("com.lapzupi.dev:lapzupi-catalog:0.0.5")
    }
}
```
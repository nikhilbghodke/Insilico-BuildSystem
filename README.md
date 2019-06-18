
# Insilico Build Gradle Plugin  [![Build Status](https://travis-ci.org/niikhilghodke/Insilico-BuildSystem.svg?branch=master)](https://travis-ci.org/niikhilghodke/Insilico-BuildSystem)

This a Gradle Plugin which aims to let user create Osgi Bundes, Eclipse features, Eclipse Plugins and configure Osgi Application. The aims to ease out the process of creating and launching Osgi application. The project is still in development phase.

## User Documentation

### Installation

#### Using Leagcy Plugin Application

```groovy
buildscript {
  repositories {
    maven {
      url "https://plugins.gradle.org/m2/"
    }
  }
  dependencies {
    classpath "gradle.plugin.com.github.niikhilghodke:InsilicoBuildSystem:1.0.1"
  }
}

apply plugin: "com.github.niikhilghodke"
```
#### Using Plugins DSL

```groovy
plugins {
  id "com.github.niikhilghodke" version "1.0.1"
}
```
## Developer Documentation

### How to clone this Repository

Open Git Bash and type the following command

```bash
$ git clone https://github.com/niikhilghodke/Insilico-BuildSystem
```
### How to build the project
Gradle plugin is nothing but a code Written in JVM Language, so the process is similar to building any JVM project using Gradle.

1) Clone the Project.
2) Open Command Prompt inside the directory where the project has benn cloned.
3) Type the following Command

```bash
./gradlew build
```
### Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
[MIT](https://choosealicense.com/licenses/mit/)

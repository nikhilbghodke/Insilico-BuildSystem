
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
### How to create Osgi bundle

A Custom Task  named `OsgiBundle` can be used to build OSGi bundles. This Custom Task extends Bundle Task defined in Bnd Gradle Plugin, so users are free to use all the properties used inside Bundle Custom Task Defined by Bnd Gradle Plugin. Documentation of which can be found [here](https://github.com/bndtools/bnd/tree/master/biz.aQute.bnd.gradle#create-a-task-of-the-bundle-type). The Osgi Bundle Task defines few extra properties inside it to declare Manifest headers. The Advantage of declaring this property is that user will no longer have to remember OSGi constants for wrting Manifest Headers and Gradle will give you immediate feedback when user mis spells any header, which was not the case with Bundle Task. Here is an example of how to configure the Custom Task

```groovy
task bundle(type:OsgiBundle){
    from sourceSets.main.output

    bundleName ="somevalue"
    symbolicName= ""
    developer= "Roman"
    license= "MIT"
    vendor= "draegerLab"
    version = "1.1.0"
    contactAddress= "21b Bakers Street"
    activator= "com.Insilico.Activator"
}

```
bundleName property of Task is used to set `Bundle-Name` Header of Manifest.
symbolicName property of Task is used to set `Bundle-SymbolicName` Header of Manifest.
and so on each other property in above above example

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

If this doesn't work, you may need to change the permissions for the gradle file before you can run it: `chmod +x gradlew`.

### Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
[MIT](https://choosealicense.com/licenses/mit/)

# Activator-Bundle Example

This is an example project about how to build an OSGi bundle using this plugin. This project builds a Bundle which upon starting in any OSGi framework, will display `Hello Everyone - Bundle is started!!!` message and upon stoping the bundle will display `Goodbye Everyone - Bundle is stoped!!!` message.

## How to build the project

Inorder to build this example project follow these steps
1) Open this this example project on your computer.
2) Open command prompt and type `gradle jar`
These two simple steps will build your OSGi bundle. You can find the built OSGi bundle in `build/libs` directory.

## Explaining the example project in detail

1) Unlike conventional projects where the source code lies in `src/main/java` (followed by gradle convention), this project has its source code inside `src/any` directory. If your project does not follow Gradle onvention then you will have to set Sourcets for your project, which is done for this project in `build.gradle` file on line number 14.

```groovy
sourceSets {
    main {
        java {
            srcDir 'src/any'
        }
    }
}
```
if your source code lies according to Gradle convention you do not need to write the above line.

2) the following part of `build.gradle` file is important for building OSGi bundle.

```groovy
jar{
            from sourceSets.main.output
            bundle{
                bundleName = "SimpleBundle"
                symbolicName = "org.insilico.examples.simpleExample"
                version = 1.2
                vendor = "Roman Schulte"
                license = 'MIT License'
                developer = 'Nikhil Ghodke'
                contactAddress = "221b Baker St, London"
                activator="com.nikhil.Activator"
            }
}
```
1) `bundleName` :- It is used to set `Bundle-Name` header inside Manifest file of OSGi bundle.
2) `SymbolicName` :- It is used to set `Symbolic-Name` header inside Manifest file of OSGi bundle.
3) `version` :- It is used to set `Bundle-Version` header inside Manifest file of OSGi bundle.
4) `vendor` :- It is used to set `Bundle-Vendor` header inside Manifest file of OSGi bundle.
5) `license` :- It is used to set `Bundle-License` header inside Manifest file of OSGi bundle.
6) `developer` :- It is used to set `Bundle-Developer` header inside Manifest file of OSGi bundle.
7) `conatactAddress` :- It is used to set `Bundle-ContactAddress` header inside Manifest file of OSGi bundle.
8) `activator` :- It is used to set `Bundle-Activator` header inside Manifest file of OSGi bundle.

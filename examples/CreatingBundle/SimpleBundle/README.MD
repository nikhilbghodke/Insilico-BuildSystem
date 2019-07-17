# Activator-Bundle Example

This is an example project about how to build an OSGi bundle using the developed plugin.
The bundle built from this project does do anything upon starting or stoping it, this project just serves an example, so that the user get familiar to the plugin.


## How to build the project

Inorder to build this example project follow these steps
1) Open this this example project on your computer.
2) Open command prompt and type `gradle jar`
These two simple steps will build your OSGi bundle. You can find the built OSGi bundle in `build/libs` directory.

## Explaining the example project in detail

1) the following part of `build.gradle` file is important for building OSGi bundle.

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

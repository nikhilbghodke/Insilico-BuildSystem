# Application Example
This is an example for building an OSGi Application using the the `com.github.niikhilghodke.applicationBuilder`. When you build the application and run the application then its by default starts the a bundle with name `ActivatorBundle` and prints "Hello Everyone - Bundle is started!!!" within the framework.

## How to build the project

Inorder to build this example project follow these steps
1) Open this example project on your computer.
2) Open command prompt and type `gradle buildApplication`
These two simple steps will build your OSGi Application. You can find the built OSGi bundle in `build/apps` directory.

## How to run the built Project
1) Open the the `build/app` within this project.
2) Open Command Prompt within the `build/app` directory.
3) Now type `java -jar org.eclipse.osgi-3.13.200.jar -console`
And within three steps you have a Osgi Application running.

## Explaining the example project in detail

The Plugin creates three tasks by Default Tasks named `copyBundles`,`setConfiguration` and `buildApplication`. `buildApplication` task just `dependsOn` `copyBundles` and `setConfiguration` and does nothing different, so that user has to just run one task to build the application.

# Application Example
This is an example for building an OSGi Application using the the `com.github.niikhilghodke.applicationBuilder`.
When you build the application and run the application then its by default starts the a bundle with name `ActivatorBundle` and 
prints "Hello Everyone - Bundle is started!!!" within the framework.

## How to build the project

Inorder to build this example project follow these steps
1) Open this example project on your computer.
2) Open command prompt and type `./gradlew buildApplication`
These two simple steps will build your OSGi Application. You can find the built OSGi bundle in `build/apps` directory.

## How to run the built Project
1) Open the the `build/app` within this project.
2) Open Command Prompt within the `build/app` directory.
3) Now type `java -jar org.eclipse.osgi-3.13.200.jar -console`
And within three steps you have an Osgi Application running.

## Explaining the example project in detail

The Plugin creates three tasks by Default Tasks named `copyBundles`,`setConfiguration` and `buildApplication`. `buildApplication` task `dependsOn` `copyBundles` and `setConfiguration` and does nothing different, so that user has to just run one task to build the application.

1) `copyBundle` task is a task of type `CopyBundle` which helps in copying copying dependencies of a particular type into a particular directory. By default it copies all the dependencies of of configuration `osgiInstall` into `build/app` directory. The user is always free to select any another directory and configuration to build the application 

2) Another small point worth noticing is that in order to build a osgi application, you need to perform following steps<br>
  a) Copy bundles into one directory<br>
  b) Write configuration file  `configuration/config.ini` as per the requirements ( like which bundles needs to be included in application and which bundles should be started when the application starts).<br>
The first task of copying bundles is accomplished by `copyBundle` task and second task is completed by `setConfiguration` task. The Plugin as previously explained creates three tasks<br>
a)`copyBundles`<br>
b)`setConfiguration`<br>
c)`build`<br>
The`build` tasks just depends on `copyBundle` task and `setConfiguration` task, so that does not have to run two tasks to build the application instead he could just run one command `gradle build` and his application is ready. This also means that you can build your application by running two commands `gradle copyBundles` and then run `gradle setConfiguration` and still get the same application.

3) `copyBundles` is of Custom task type `CopyBundles`and `setConfiguration` is of Custom task type `WriteEquinoxConfgiration`.
So users are free to create as many tasks of these Custom task types for building the application.



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
And within three steps you have a Osgi Application running.

## Explaining the example project in detail

The Plugin creates three tasks by Default Tasks named `copyBundles`,`setConfiguration` and `buildApplication`. `buildApplication` task just `dependsOn` `copyBundles` and `setConfiguration` and does nothing different, so that user has to just run one task to build the application.

1) `copyBundle` task is a task of type `CopyBundle` which helps in copying copying dependencies of a particular type into a paricular directory. By default it copies all the dependencies of of configuration `osgiInstall` into `build/app` directory. The user is always free to select any another directory and configuration to build the application 
```groovy
plugins {
    id "com.github.niikhilghodke.applicationBuilder" version "1.0.29"
}

project.configurations.create("osgiInstall")

dependencies {

    Install group: 'org.apache.felix', name: 'org.apache.felix.gogo.runtime', version: '1.1.2'
    Install group: 'org.apache.felix', name: 'org.apache.felix.gogo.shell', version: '1.1.2'
    Install group: 'org.apache.felix', name: 'org.apache.felix.gogo.command', version: '1.0.0'
    Install group: 'org.eclipse.platform', name: 'org.eclipse.equinox.console', version: '1.3.100'
    Install group: 'org.eclipse.platform', name: 'org.eclipse.osgi', version: '3.13.200'
}
copyBundle{
  configuration= project.configurations.Install
  into ("app")
}
setConfiguration{
  buildDir="app"
}
```

The above `build.gradle` file will create a application from dependencies under the `Install` configuration intead of `osgiInstall`(this deafult) into `app` directory instead of `build/app` (default directory).
<b> It should be noted that if you want to your application to be built in some other directory than `build/app` , then you have to change `into` property of `copyBundle` task and `buildDir` property of `setConfiguration` task to a <i>same path </i> or else the bundles might get copied some where else and configuration file will be written in other directoty.</b>
<br>
Another thing worth noticing in above example is that if you want to build application usinf dependencies from different configuration configuration and not `osgiInstall`(deafult one), you also need to create that configuration using the following code in `build.gradle`
file.
<br>
`project.configurations.create("Install")`<br>
The above code creates a new configuration named `Install` which can be used to register dependencies and latter to build applications.

2) Another small point worth noticing is that in order to build a osgi application, you need to perform following steps
  a) Copy bundles into one directory, lets say `application` as an example
  b) Write configuration file  `application/configuration/config.ini`, where `application` is file where all the bundles are              already copied.
the first task of copying bundles is accomplished by `copyBundle` task and second task is completed by `setConfiguration` task. The Plugin as previously explained creates three tasks<br>
a)`copyBundles`<br>
b)`setConfiguration`<br>
c)`buildApplication`<br>
`buildApplication` tasks just dependes on `copyBundle` task and `setConfiguration` task, so that does not have to run two tasks to build the application instead he could just run one command `gradle buildApplication` and his application is ready. This also means that you can build your application by running two commands `gradle copyBundles` and then run `gradle setConfiguration` and still get the same application.
<br>
3) `copyBundles` is of Custom task type `CopyBundles`and `setConfiguration` is of Custom task type `WriteEquinoxConfgiration`.
So users are free to create as many task of these Custom task types for building the application.

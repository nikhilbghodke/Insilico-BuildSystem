# Application Example
This is an example for building an OSGi Application using the `com.github.niikhilghodke.applicationBuilder`. When you build the application and run the application (Instruction of which are given below), then a simple osgi application is started and then you are free to hack around things to create a mucher more productive application.

## How to build the project

Inorder to build this example project follow these steps
1) Open this example project on your computer.
2) Open command prompt and type `./gradlew build`
These two simple steps will build your OSGi Application. You can find the built OSGi bundle in `build/apps` directory.

## How to run the built Project
1) Open the the `build/app` within this project.
2) Open Command Prompt within the `build/app` directory.
3) Now type `java -jar org.eclipse.osgi-3.13.200.jar -console`
And within three steps you have an Osgi Application running.

## Explaining the example project in detail

The Plugin creates three tasks by Default Tasks named `copyBundles`,`setConfiguration` and `build`. `build` task `dependsOn` `copyBundles` and `setConfiguration` and does nothing different, so that user has to just run one task to build the application.

1)Also, `copyBundles` is of Custom task type `CopyBundles` and `setConfiguration` is of Custom task type `WriteEquinoxConfgiration`.
So users are free to create as many tasks of these Custom task types for building the application.

2)Another small point worth noticing is that in order to build an osgi application, you need to perform following steps<br>
  a) Copy bundles into one directory<br>
  b) Write configuration file  `configuration/config.ini` as per the requirements ( like which bundles needs to be included in application and which bundles should be started when the application starts).<br>
The first task of copying bundles is accomplished by `copyBundle` task and the second task is completed by `setConfiguration` task. The Plugin as previously explained creates three tasks<br>
a)`copyBundles`<br>
b)`setConfiguration`<br>
c)`build`<br>
The`build` tasks just depends on `copyBundle` task and `setConfiguration` task, so that does not have to run two tasks to build the application instead he could just run one command `gradle build` and his application is ready. This also means that you can build your application by running two commands `gradle copyBundles` and then run `gradle setConfiguration` and still get the same application.



## Gogo Shell commands
If you are new to Gogo shell, here are a few commands that can help u understand the framework a bit.

1) ss (Ex: osgi> ss)
This command can be used to list all the existing bundles in the OSGi environment. Bundle ID, State and Bundle symbolic name of all the bundles are shown.

2) start <bundle-id> (Ex: osgi> start 16)
This command can be used to start a particular bundle. If it can’t be started, reasons are printed on the console.

3) stop <bundle-id> (Ex: osgi> stop 16)
This command can be used to stop a particular bundle. If it can’t be stopped, reasons are printed on the console.

4) b <bundle-id> (Ex: osgi> b 16)
This can be used to print all the metadata related to this bundle. That includes imported packages, exported packages, host bundle, required bundles etc.

5) diag <bundle-id> (Ex: osgi> diag 16)
This can be used to diagnose a particular bundle. It will show you the list of missing imported packages.

6) headers <bundle-id> (Ex: osgi> headers 16)
This can be used to list the headers for a particular bundle.

7) packages <package-name> (Ex: osgi> packages org.wso2.foo)
This can be used to list all the bundles which use the given package.

8) install file:<file-path> (Ex: osgi> install file:/home/temp/bundle1.jar)
This can be used to install a bundle into running OSGi environment. After installing, use ‘start’ command to activate the bundle.

9) uninstall <bundle-id> (Ex: osgi> uninstall 16)
This can be used to remove a bundle from the OSGi environment.

10) active (Ex: osgi> active)
This can be used to list all active bundles in the current instance.

11) refresh (Ex: osgi> refresh)
This can be used to refresh the system. All the package resolutions are refreshed when this is executed. Always refresh the system after uninstalling a bundle as a best practice.

12) exit 
This command is used to exit the framework


### If you are still curious about the commands please visit this [website](https://felix.apache.org/documentation/subprojects/apache-felix-gogo.html)



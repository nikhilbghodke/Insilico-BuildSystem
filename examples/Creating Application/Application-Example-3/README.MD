# Application Example
This is an example for building an OSGi Application using the the `com.github.niikhilghodke.applicationBuilder`.
The main aim of this project is show you can convert a non-osgi bundle (`jdom.jar`) to a osgi bundle and also let users set any osgi headers for the bundle. This project converts `jdom.jar` to a osgi bundle.

## How to build the project

Inorder to build this example project follow these steps
1) Open this example project on your computer.
2) Open command prompt and type `./gradlew buildApplication`
These two simple steps will build your OSGi Application. You can find the built OSGi bundle in `build/apps` directory. 

## How to run the built Project
1) Open the the `build/app` within this project.
2) Open Command Prompt within the `build/app` directory.
3) Now type `java -jar org.eclipse.osgi-3.13.200.jar -console`
And within three steps you have a Osgi Application running. Now you can see which bundles are present in your application with their states (`Installed`,`Active`, `Resolved`) by running `ss` command in your application. You might see that the `jdom.jar` is in `Installed` state in your application and if you try to start `jdom.jar` bundle then it will throw a error saying unresolved dependencies  which is true as all the dependencies of `jdom.jar` are not installed in your application, but the main aim is to convert a normal jar to osgi bundle which is accomplished successfully.

## Explaining the example project in detail

1) <b> I would suggest you to have a look at `Application-Example-3` project which explains the basics of creating a simple application.</b>
2) Inorder to convert a jar to osgi bundle `copyBundles` task is used (yess, you are right, its the same task which copies bundles in earlier examples.) `copyBundles` does tow task <br>
  a) Copy bundles to particular directory (default is `build/app`)<br>
  b) Wraps jar to osgi bundles and allows users to add custom manifest headers.<br>
  ```groovy
  copyBundles{
    wrapper{
        bundle("jdom"){
            bundleName = "SimpleBundle"
            symbolicName = "org.insilico.examples.simpleBundle"
            version = 1.0
            vendor = "Roman Schulte"
            license = 'MIT License'
            developer = 'Nikhil Ghodke'
            contactAddress = "221b Baker St, London"
            bnd('Manifest-Version':'2.3')
            bnd('Bundle-Description': 'Give some description of your bundle here')
        }
    }
}
 ```
 This is the part where acctually the user decides which dependencies he wants to wrap and what manifest headers he wants to set.
2) `bundle` is a method which takes parameter like `id` of the dependency which you want to wrap and other parameter is a closure which allows users to set manifest headers.<b>There must be a dependency with the id which is passed in the `bundle` function or else the task is unable to wrap the dependency, Lets say if the user passes `jdom` as a parameter for id, then the task checks for a dependency with name `jdom.jar` within the configuration `osgiInstall`(if the configuration property was not changed for the task)</b>. Another parameter which is the closure allows users to set following manifest headers 
  1) `bundleName` :- It is used to set `Bundle-Name` header inside Manifest file of OSGi bundle.
  2) `SymbolicName` :- It is used to set `Symbolic-Name` header inside Manifest file of OSGi bundle.
  3) `version` :- It is used to set `Bundle-Version` header inside Manifest file of OSGi bundle.
  4) `vendor` :- It is used to set `Bundle-Vendor` header inside Manifest file of OSGi bundle.
  5) `license` :- It is used to set `Bundle-License` header inside Manifest file of OSGi bundle.
  6) `developer` :- It is used to set `Bundle-Developer` header inside Manifest file of OSGi bundle.
  7) `conatactAddress` :- It is used to set `Bundle-ContactAddress` header inside Manifest file of OSGi bundle.
If you want to set any another header appart from above headers like `Manifest-Version`, you have to use `bnd()` function. `bnd()` takes 
input of a Map<String header,String value>.
3) Another important thing that we should not forget is, you are always free to change the default configuration as explained in previous examples and you change the output directory using the `into()` method, in short everything explained in `Application-Example-1` ans `Application-Example-2` stil remains valid.

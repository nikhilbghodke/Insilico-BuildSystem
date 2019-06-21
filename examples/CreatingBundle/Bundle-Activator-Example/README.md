# An Example of creating Osgi Bundle using this Plugin"

This Example Creates a Bundle which prints "Hello" Message when the bundle is started and Prints "Good Bye" Message when bundle is stoped within an application.
For creating Bundle the example uses pre defined jar Task and properties provided by the Plugin.

This Example does not follow the Gradle Java Convention of keeping the code in 'src/main/java' directory, instead it sets different of SourceSets through the build. gradle file. So this is a good example of how to change the SourceSets, if they do not Match the Deafult One.

## How to build the Bundle

Type the below given command to build the example project and you can find the build jar with `build\libs` directory.

```groovy
 gradle jar
```

package org.insilico.build
import org.gradle.api.tasks.WriteProperties

class WriteConfiguration extends WriteProperties {
    private String noShutdown;
    private String ignoreApp;
    public String buildDir;
    public WriteConfiguration() {
        this.buildDir = "build/app"
        this.outputFile(buildDir + "/configuration/config.ini")
        this.noShutdown = "true"
        this.ignoreApp = "true"
    }

    String getBuildDir() {
        return buildDir
    }

    void setBuildDir(String buildDir) {
        this.buildDir = buildDir
        outputFile(buildDir+"/configuration/config.ini")
    }

    void noShutdown(String a){
        this.noShutdown=a
    }

    void ignoreApp(String a)
    {
        this.ignoreApp=a
    }

    @Override
    public void writeProperties() {

        String bundles= ""
        // getting Array of resolved artifacts from osgiRuntime configuration
        def set = project.configurations.osgiRuntime.resolvedConfiguration.resolvedArtifacts.toArray()

        println set.toString()

        // getting one string with names of all the bundles
        for(def file : set)
            bundles=bundles+file.name+"@start,"

        //writting the properties in config.iniproperty("eclipse.ignoreApp","true")
        outputFile(buildDir+"/configuration/config.ini")
        property("osgi.noShutdown",this.noShutdown)
        property("eclipse.ignoreApp",this.ignoreApp)
        property("osgi.bundles",bundles)
        super.writeProperties()
    }
}

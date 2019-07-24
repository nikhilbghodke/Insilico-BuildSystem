package org.insilico.build

import org.gradle.api.artifacts.Configuration
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.WriteProperties

/**
 * WriteOsgiConfiguration Class extends {@link WriteProperties} and is used to set properties related to Osgi application inside
 * Configuration files of Application.
 *
 * @author NikhilGhodke
 * @since 2019-07-29
 */

class WriteOsgiConfiguration extends WriteProperties {
    private boolean noShutdown;
    private Configuration configuration;
    public String buildDir;
    private static final String OSGI_NO_SHUTDOWN = "osgi.noShutdown";
    private static final String OSGI_BUNDLES = "osgi.bundles";
    private  HashSet<String> toBeStarted;
    public WriteOsgiConfiguration(){
        super()
        this.buildDir = "build/app"
        this.outputFile(buildDir + "/configuration/config.ini")
        this.noShutdown = true
        this.configuration=project.configurations.osgiInstall;
        this.toBeStarted=new HashSet<>();

    }
    /**
     * Used to Get noShutdown property of task.
     * @return
     */
    @Input
    boolean getNoShutdown() {
        return noShutdown
    }

    /**
     * Used to set osgi.noShutdown Property of Configuration file of Osgi Application, its Default value is true.
     * @param noShutdown Default value is true.
     */
    void setNoShutdown(boolean noShutdown) {
        this.noShutdown = noShutdown
    }

    /**
     * Used to get configuration property of task
     * @return configuration property
     */
    @Input
    Configuration getConfiguration() {
        return configuration
    }

    /**
     * Used to set configuration property, all the dependencies under the set Configuration willbe used to write configuration file
     * for application. The default value of this property is project.configurations.osgiRuntime
     * @param configuration
     */
    void setConfiguration(Configuration configuration) {
        this.configuration = configuration
    }

    /**
     * Used to get buildDir property of the Class
     * @return buildDir  property
     */
    @Input
    String getBuildDir() {
        return buildDir
    }

    /**
     * Used to set buildDir property of task. buildDir property is used to decide where to create the configuration file.
     * If the user specifies a directory named 'application', then the the task creates a directory named 'configuration'
     * inside 'application' directory. And under 'configuration' directory creates a file file'config.ini' where it writes
     * the properties. The Default value of this property is 'build/app'.
     * @param buildDir
     */
    void setBuildDir(String buildDir) {
        this.buildDir = buildDir
    }

    @Input
    void startWith(String id){
        this.toBeStarted.add(id+".jar");
    }
    @Input
    void startWith(String id,String version){
        this.toBeStarted.add(id+"-"+version+".jar");
    }

    /**
     * Actually writes the properties into the configuration, rest all the function of this class just setting the properties.
     * But this function takes those values and write it to configuration file as specified by buildDir property.
     */
    @Override
    public void writeProperties() {

        String bundles= ""
        // getting Array of resolved artifacts from osgiRuntime configuration
        def set = this.configuration.resolvedConfiguration.getFiles().toArray()
        
        for(def file : set)
        {
            println( file.name)
            bundles=bundles+file.name;
            if(this.toBeStarted.contains(file.name))
                bundles+="@start";
            bundles+=","
        }


        //writting the properties in config.iniproperty("eclipse.ignoreApp","true")
        outputFile(buildDir+"/configuration/config.ini")
        property(OSGI_NO_SHUTDOWN,this.noShutdown)
        property(OSGI_BUNDLES,bundles)
        super.writeProperties()
    }
}

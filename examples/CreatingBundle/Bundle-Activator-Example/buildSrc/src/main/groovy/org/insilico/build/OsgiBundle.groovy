package org.insilico.build

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.SourceSet
import org.gradle.api.tasks.TaskAction
import org.osgi.framework.Constants

class OsgiBundle extends DefaultTask{

    // the Booleans are used to determine whether the user has specified
    // any value for the property

    private Object bndfile
    private boolean isBndfileSet=false

    private SourceSet sourceSet
    private boolean isSourcesetSet=false

    private Object from
    private boolean isFromSet=false

    private Object classpath
    private boolean isClasspathSet=false

    private String instructions=""
    private boolean isInstructionSet=false

    @Input
    public Object getFrom(){
        return this.from
    }

    public void from(Object a){
        this.from=a
        this.isFromSet=true
    }

    public void setFrom(Object a)
    {
        this.from=a
        this.isFromSet=true

    }

    @InputFile
    @Optional
    public Object getBndfile() {
        return bndfile
    }

    public void bndfile(Object fil) {
        this.bndfile=fil
        this.isBndfileSet=true
    }

    public void setBndfile(Object fil) {
        this.bndfile=fil
        this.isBndfileSet=true
    }

    public void sourceSet(SourceSet sourceSet) {
        this.sourceSet = sourceSet
        this.isSourcesetSet=true
    }

    /**
     * Get the sourceSet property.
     */
    @Input
    @Optional
    public SourceSet getSourceSet() {
        return sourceSet
    }
    /**
     * Set the sourceSet property.
     */
    public void setsourceSet(SourceSet sourceSet) {
        this.sourceSet = sourceSet
        this.isSourcesetSet=true
    }

    @Input
    @Optional
    public String getBnd() {
        return instructions
    }

    /**
     * Set the bnd property from a map.
     */
    public void setBnd(Map<String, ?> map) {
        bnd(map)
        this.isInstructionSet=true
    }

    /**
     * Add instuctions to the bnd property from a map.
     */
    public void bnd(Map<String, ?> map) {
        this.isInstructionSet=true

        HashMap<String,String> transform=new HashMap<String,String>();


        //Add all the Headers that you want to change here before wrting it into maifest file
        // here along with the constants they are related to
        transform.put("vendor", Constants.BUNDLE_VENDOR)
        transform.put("activator",Constants.BUNDLE_ACTIVATOR)
        transform.put("version",Constants.BUNDLE_VERSION)
        transform.put("name",Constants.BUNDLE_NAME)
        transform.put("license",Constants.BUNDLE_LICENSE)
        transform.put("contactAddress",Constants.BUNDLE_CONTACTADDRESS)
        transform.put("developer","Bundle-Developer")


        //appending key and values of the map into a string named instructions
        for (String name : map.keySet())
        {
            int flag=0
            //Checks if the Header is present in the transform map
            for (String key : transform.keySet())
            {   //If the headers is matched to a key in transform map then the value of key from transformed map is written
                // is appended to the instructions
                if(key.compareTo(name)==0)
                {
                    instructions=instructions.concat(transform.get(key)+"="+map.get(name)+"\n")
                    flag=1
                }
            }
            // the match is not found then the then the key and value as appended as it is without any change
            if(flag==0)
                instructions=instructions.concat(name+"="+map.get(name)+"\n")
        }

    }

    /**
     * Add files to the classpath.
     *
     * <p>
     * The arguments will be handled using
     * Project.files().
     */
    public void classpath(Object... paths) {
        this.isClasspathSet=true
        this.classpath=paths
    }

    /**
     * Get the classpath property.
     */
    @Input
    @Optional
    public Object getClasspath() {
        return classpath
    }
    /**
     * Set the classpath property.
     */
    public void setClasspath(Object path) {
        this.isClasspathSet=true
        this.classpath=path
    }

    @TaskAction
    public something(){


        //if the property is not set by user then properties are set to their default values
        // as specified in Bnd Gradle Plugin Documentation
        if(!this.isInstructionSet)
            this.instructions=""
        if(!this.isSourcesetSet)
            this.sourceSet=project.sourceSets.main
        if(!isClasspathSet)
            this.classpath=project.sourceSets.main.compileClasspath


        // creates and configures a task of type Bundle from bnd Gradle plugin
        project.task("anything",type:aQute.bnd.gradle.Bundle){
                from this.getFrom()
                bnd(this.getBnd())
                sourceSet = this.getSourceSet()
                classpath = this.getClasspath()
            }

        //runs the newly created task
        project.tasks.anything.copy()

        //remove the newly created task from project
        project.gradle.startParameter.excludedTaskNames.add('anything')
    }
}

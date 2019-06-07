package org.insilico.build

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.SourceSet
import org.gradle.api.tasks.TaskAction

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
        for (String name : map.keySet())
            instructions=instructions.concat(name+"="+map.get(name)+"\n")
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

        //Check if the property is set or not if it is set by the user only then
        //the new task will have have the property as specified by users and if the user does not specify any property
        //then it will not be set in the new task and will take default task as specified by Bnd Gradle plugin
        String configure=""
        if(this.isFromSet)
            configure=configure.concat("from "+this.getFrom()+"\n")
        if(this.isInstructionSet)
            configure=configure.concat("bnd("+this.getBnd()+")\n")
        if(this.isSourcesetSet)
            configure=configure.concat("sourceSet = "+this.getSourceSet()+"\n")
        if(this.isBndfileSet)
            configure=configure.concat("bndfile = "+this.getBndfile()+"\n")


        // creates and configures a task of type Bundle from bnd Gradle plugin
        project.task("anything",type:aQute.bnd.gradle.Bundle){
                configure
            }

        //runs the newly created task
        project.tasks.anything.copy()

        //remove the newly created task from project
        project.gradle.startParameter.excludedTaskNames.add('anything')
    }
}

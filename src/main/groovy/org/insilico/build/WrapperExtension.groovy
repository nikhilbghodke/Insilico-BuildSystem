package org.insilico.build

import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.tasks.Copy

/**
 * This class is used to define Extensions which are used for converting non osgi dependencies to osgi dependencies.
 * Inorder to convert a jar into OSGi bundle, it must be specified in dependencies of the project and `bundle` function
 * must be called for that jar.
 *
 * The mechanism that is employed to convert jars to bundles is simple and as follows
 *1) Unzip the jar in `build/unpacked` directory, this is accomplished by creating a task of type {@Link Copy} and then calling it.
 *2) Define a task of type {@Link Bundle} which will again create a jar from the unzipped jar in `build/unpacked` directory.
 *3) The task task of type {@Link Bundle} is configured as per user inputs for Manifest Headers and the task is made to run(
 * by calling its method which implement @TaskAction)
 *4) Repeat step 1 for all the jars specified using `bundle` method.
 */
public class WrapperExtension {

    /**This List is just used to store information of all the jars that needs to be wrapped. This information include id,
     * version of jar and all the manifest headers that the user wants to set.
     */
    public List<ManifestHeaders> toBeWrapped;

    /**
     * This task is just used to access properties of the task which adds the extension(Those properties can be Destionation
     * Directory etc.)
     */
    private Copy task;


    public  WrapperExtension(Copy task){
        toBeWrapped= new ArrayList<>();
        this.task=task;
        //this.qw= new bnd();

    }

/**
 * This method is called by the user through an extension when he wants to register a jar which needs to converted to bundle.
 * Before registering it first checks if the jar which the user wants to convert is registered as a dependency under proper
 * configuration.
 * @param id Id of the jar to get wrapped
 * @param version Version of jar
 * @param configurator Used to get information of all the manifest headers which the user wants to set.
 */
   public void  bundle(String id,@DelegatesTo(ManifestHeaders) Closure configurator) {
        ManifestHeaders a= new ManifestHeaders()
        a.id=id;
        Closure cfg = configurator.clone()
        cfg.delegate = a;
        cfg.resolveStrategy = Closure.DELEGATE_FIRST
        cfg.call()



       File[] set = task.configuration.resolvedConfiguration.getFiles().toArray()
       boolean  found =false;
       for(File file:set)
       {
           //println( file.name)
           if(file.name==a.id+".jar")
           {
               found=true;
               break;
           }
       }

       if(found==false)
       {
           println( a.id+".jar not found in dependencies");
       }

       else
       {

           toBeWrapped.add(a);
       }
   }

    /**
     * This method is called by the user through an extension when he wants to register a jar which needs to converted to bundle.
     * Before registering it first checks if the jar which the user wants to convert is registered as a dependency under proper
     * configuration.
     * @param id Id of the jar to get wrapped
     * @param version Version of jar
     * @param configurator Used to get information of all the manifest headers which the user wants to set.
     */
    public void  bundle(String id,String version,@DelegatesTo(ManifestHeaders) Closure configurator) {
        ManifestHeaders a= new ManifestHeaders()
        a.id=id;
        a.version=version;
        Closure cfg = configurator.clone()
        cfg.delegate = a;
        cfg.resolveStrategy = Closure.DELEGATE_FIRST
        cfg.call()

        File[] set = task.configuration.resolvedConfiguration.getFiles().toArray()
        boolean  found =false;
        for(File file:set)
        {
            //println( file.name)
            if(file.name==a.id+"-"+a.version+".jar")
            {
                found=true;
                break;
            }
        }

        if(found==false)
        {
            println( a.id+".jar not found in dependencies");
        }

        else
        {
            toBeWrapped.add(a);
        }
    }

    /**
     * This method converts jars to bundles. The mechanism that is employed to convert jars to bundles is simple and as follows
     *1) Unzip the jar in `build/unpacked` directory, this is accomplished by creating a task of type {@Link Copy} and then calling it.
     *2) Define a task of type {@Link Bundle} which will again create a jar from the unzipped jar in `build/unpacked` directory.
     *3) The task task of type {@Link Bundle} is configured as per user inputs for Manifest Headers and the task is made to run(
     * by calling its method which implement @TaskAction)
     *4) Repeat step 1 for all the jars specified using `bundle` method.
     */
    public  void wrap(){

        for(ManifestHeaders a: toBeWrapped){

            //calculate the name of the jar which needs to be wrapped from it's id and version provided by user
             String jarName= a.id;
            if(a.version!="")
                jarName=jarName+"-"+a.version;
            jarName+=".jar";

            File[] set = task.configuration.resolvedConfiguration.getFiles().toArray()
            File requiredDependency;
            for(File file:set){
                if(file.name==jarName)
                {
                    requiredDependency=file;
                    break;
                }
            }

            //creates the build directory if it was created
            File build= new File("build");
            if(!build.exists())
                build.mkdir();

            Project project= this.task.getProject()
            Task tempCopy= project.task('copy'+jarName.substring(0,jarName.length()-4),type: Copy)

            tempCopy.configure {
                def zipFile = this.task.project.file(requiredDependency.getAbsolutePath())
                def outputDir = this.task.project.file("build/unpacked/"+jarName.substring(0,jarName.length()-4))

                from this.task.project.zipTree(zipFile)
                into outputDir
            }

            tempCopy.copy()

            Task wrap= project.task('wrap'+jarName.substring(0,jarName.length()-4),type:aQute.bnd.gradle.Bundle)
            File file = new File(this.task.getDestinationDir().getAbsolutePath()+"/"+jarName);
            if(file.exists())
            {
                file.delete()
            }
            wrap.configure {
                from ("build/unpacked/"+jarName.substring(0,jarName.length()-4))
                destinationDir = this.task.project.file(this.task.getDestinationDir())
                archiveFileName =jarName
                bnd(a.map)
            }
            wrap.copy();
        }
    }
}
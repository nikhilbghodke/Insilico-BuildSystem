package org.insilico.build


import org.gradle.api.tasks.TaskAction
import org.gradle.jvm.tasks.Jar

/**
 * This is a Task used for building Eclipse Features. All the properties and methods of this task are acquired by adding
 * an extension of type {@Link FeatureExtension}. This Task extends Jar task from core Java plugin.
 * @see WriteEquinoxConfiguration
 * @author Nikhil Ghodke
 * @since 2019-07-17
 */

public class EquinoxFeature extends Jar {


/**
 * The constructor is the place where the extension is added to the task. The Class {@Link FeatureExtension} is used fpr creating
 * the extension and takes the newly created task as a parameter for its constructor.
 */
    public EquinoxFeature(){
        super()
        this.extensions.feature= new FeatureExtension(this)
    }

    /**
     * This Function just calls createFeatureArchive from the extension of type {@Link FeatureExtension}. The method createFeatureArchive
     * is used to just write the feature.xml file in `build/features` directory.
     * This Function also calls copy method from Jar class which is useful for generating the archive in total.
     */
    @TaskAction
    void Feature(){

        this.extensions.feature.createFeatureArchive()
        this.copy()

    }
}

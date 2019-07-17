package org.insilico.build

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.plugins.JavaPlugin

/**
 * This plugin is used for building feature archive. It will generate a feature.xml file and store it `build/features`
 * directory and creates a archive inside `build/libs` directory.
 * @see EquinoxFeature
 * @see FeatureExtension
 * @author Nikhil Ghodke
 * @since 2019-07-17
 */

class FeatureBuilderPlugin implements Plugin<Project>  {
    /**
     * This Function first checks if `java` plugin is applied by the user, if it is not applied by the user then this plugin
     * adds `java` plugin by itself. It then adds extension named `feature` to the `jar` task and calls few methods from
     * extension for building the archive.
     * @param project
     */
    void apply(Project project){

        boolean containsJavaPlugin =project.plugins.hasPlugin('java')
        if(containsJavaPlugin==false)
            project.plugins.apply('java')

           //adds extension named feature to jar task
            Task jar= project.getTasks().findByName('jar')
            jar.extensions.feature= new FeatureExtension(jar)
            jar.doLast {
                jar.extensions.feature.createFeatureArchive()
                jar.copy()
            }

    }
}


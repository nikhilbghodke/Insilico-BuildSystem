package org.insilico.build

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task

class FeatureBuilderPlugin implements Plugin<Project>  {
    void apply(Project project){
        Task jar= project.getTasks().findByName('jar')
        jar.extensions.feature= new FeatureExtension(jar)


        jar.doLast {
            jar.extensions.feature.writeFile()
        }
    }
}

package org.insilico.build

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.plugins.JavaPlugin

class FeatureBuilderPlugin implements Plugin<Project>  {
    void apply(Project project){
        project.plugins.withType(JavaPlugin) {
           //adds extension named feature to jar task
            Task jar= project.getTasks().findByName('jar')
            jar.extensions.feature= new FeatureExtension(jar)
            jar.doLast {
                jar.extensions.feature.createFeatureArchive()
                jar.copy()
            }
        }
    }
}


package org.insilico.build

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.tasks.Copy
import org.osgi.framework.Constants

class BuildPlugin implements Plugin<Project> {
    void apply (Project project)
    {

        //applies the base Plugin
        project.plugins.apply(org.insilico.build.BasePlugin)

        //adds extension named bundle to jar task
        Task jar= project.getTasks().findByName('jar')
        jar.extensions.bundle= new JarExtension(jar)
        jar.doLast {
            buildBundle()
        }



        project.task("Insilico"){
            dependsOn 'SetConfiguration'
            dependsOn 'CopyBundles'
        }
        project.task('CopyBundles',type: CopyBundles)
        project.task("SetConfiguration",type:WriteConfiguration)
    }
}

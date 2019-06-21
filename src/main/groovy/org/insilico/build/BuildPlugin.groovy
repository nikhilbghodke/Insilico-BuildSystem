package org.insilico.build

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.tasks.Copy
import org.osgi.framework.Constants

class BuildPlugin implements Plugin<Project> {
    void apply (Project project)
    {
        project.plugins.apply('biz.aQute.bnd.builder')

        Task jar= project.getTasks().findByName('jar')
        jar.extensions.bundle= new JarExtension(jar)
        jar.doLast {
            buildBundle()
        }

        def dest="build/app"
       // def exto= project.extensions.create('bundle',ApplicationExtension)
        project.task("Insilico"){
            dependsOn 'SetConfiguration'
        }
        project.task('CopyBundles',type: Copy)
                {
                    dependsOn 'SetDependencies'
                    from(project.configurations["archives"])
                    into("build/app")
                }
        project.task("SetDependencies",type:SetDependencies)
                {
                    finalizedBy('build')
                }
        project.task("SetConfiguration",type:WriteConfiguration)
                {
                    dependsOn 'CopyBundles'
                    ignoreApp "true"
                    noShutdown "true"
                }
    }
}

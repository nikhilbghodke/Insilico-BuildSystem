package org.insilico.build

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.Copy

class BuildPlugin implements Plugin<Project> {
    void apply (Project project)
    {
        def dest="build/app"
        def exto= project.extensions.create('bundle',Bundle)
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

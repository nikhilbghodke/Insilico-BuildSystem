package org.insilico.build

import org.gradle.api.Project
import org.gradle.api.tasks.TaskAction
import org.gradle.api.DefaultTask

class SetDependencies extends DefaultTask{
    @TaskAction
    public void setDependencies() {

        //Project project =Project.getProject()
        def ext = project.getExtensions().findByName("bundle")

        for (int i = 0; i < ext.toBeDownloaded.size(); i++) {
            project.dependencies {
                //println ext.toBeDownloaded[i].group
                archives group: ext.toBeDownloaded[i].group, name: ext.toBeDownloaded[i].name, version: ext.toBeDownloaded[i].version
            }
        }

        for (int i = 0; i < ext.toBeCopied.size(); i++) {
            project.dependencies {
                archives project.files(ext.toBeCopied[i])
            }
        }
    }
}
package org.insilico.build

import org.gradle.api.Plugin
import org.gradle.api.Project

class BasePlugin implements Plugin<Project> {

    @Override
    void apply (Project project){

        project.plugins.apply('biz.aQute.bnd.builder')

        //create a new configuration for dependencies
        project.configurations.create("osgiRuntime")
    }
}

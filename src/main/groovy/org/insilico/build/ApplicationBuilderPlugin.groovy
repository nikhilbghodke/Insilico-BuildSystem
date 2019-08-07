/**
 * This the Application Builder Plugin which adds two tasks of named copyBundles and setConfiguration.
 * CopyBundles is a task of type {@link CopyBundles} and setConfiguration is a task of type {@link WriteEquinoxConfiguration}
 * @see CopyBundles
 * @see WriteEquinoxConfiguration
 * @author Nikhil Ghodke
 * @since 2019-06-29
 */



package org.insilico.build

import org.gradle.api.Plugin
import org.gradle.api.Project


class ApplicationBuilderPlugin implements Plugin<Project>{

    /**
     * Adds to tasks named copyBundles and setConfiguration to project and then sets a Dependency of build task
     * on these two tasks.
     * @param project Project object passed by Gradle
     */
    void apply (Project project)
    {
        //create a new configuration for dependencies
        project.configurations.create("osgiInstall")

        project.plugins.apply(org.insilico.build.BasePlugin)
        project.task("buildApplication"){
            dependsOn 'setConfiguration'
            dependsOn 'copyBundles'
        }
        project.task('copyBundles',type: CopyBundles)
        project.task("setConfiguration",type:WriteEquinoxConfiguration)
    }
}

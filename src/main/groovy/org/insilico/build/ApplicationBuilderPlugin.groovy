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
import org.gradle.api.Task
import org.gradle.api.plugins.JavaBasePlugin
import org.gradle.api.plugins.JavaPlugin


class ApplicationBuilderPlugin implements Plugin<Project>{

    /**
     * Adds to tasks named copyBundles and setConfiguration to project and then sets a Dependency of build task
     * on these two tasks.
     * @param project Project object passed by Gradle
     */
    void apply (Project project) throws Exception
    {
        //create a new configuration for dependencie

        project.configurations.create("osgiInstall")
        Task b=project.task('buildApplication')
        b.configure{
            dependsOn 'setConfiguration'
            dependsOn 'copyBundles'
        }
        project.task('copyBundles',type: CopyBundles)
        project.task("setConfiguration",type:WriteEquinoxConfiguration)
    }
}
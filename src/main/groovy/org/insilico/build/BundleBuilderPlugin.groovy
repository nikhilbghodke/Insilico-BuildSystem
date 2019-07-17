package org.insilico.build

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.plugins.JavaPlugin

/**
 * This Plugin Checks if the {@link aQute.bnd.gradle.BndBuilderPlugin} is applied to the project and if it is applied then adds a new
 * extension named bundle to jar task. The extension is of type {@link JarExtension}
 * @see JarExtension
 * @author Nikhil Ghodke
 * @since 2019-07-29
 */

class BundleBuilderPlugin implements Plugin<Project> {

    @Override
    void apply (Project project){

        // after checking if the user has applied bnd Gradle Plugin we have two approaches

        //this approach checks if the user has applied the Bnd Gradle Plugin, if not then it applies the plugin
        // and adds extension to jar task
        boolean containsJavaPlugin =project.plugins.hasPlugin('java')
        boolean containsBndPlugin =project.plugins.hasPlugin('biz.aQute.bnd.builder')
        if(containsJavaPlugin==false)
            project.plugins.apply('java')

        if(containsBndPlugin==false)
            project.plugins.apply('biz.aQute.bnd.builder')
        Task jar = project.getTasks().findByName('jar')
            jar.extensions.bundle = new JarExtension(jar)
            jar.doLast {
                buildBundle()
            }

        //other approach is to check if Bnd Gradle Plugin and Java Plugin is applied by user, and if
        // he has applied the Plugin only then add extension to jar task
//        project.plugins.withType(JavaPlugin) {
//            project.plugins.withType(aQute.bnd.gradle.BndBuilderPlugin) {
//                //adds extension named bundle to jar task
//                Task jar = project.getTasks().findByName('jar')
//                jar.extensions.bundle = new JarExtension(jar)
//                jar.doLast {
//                    buildBundle()
//                }
//            }
        }
    }



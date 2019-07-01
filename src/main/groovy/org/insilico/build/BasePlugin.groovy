package org.insilico.build

import aQute.bnd.gradle.BndBuilderPlugin
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * This is the Base Plugin which does not adds any extension or task
 * to the project, but allows User to use all the Custom tasks and create their
 * own Gradle tasks thereby imposing no opinions on users.
 * @author Nikhil Ghodke
 * @since 2019-06-29
 */

class BasePlugin implements Plugin<Project> {


    /**
     * Add no new task to project
     * @param project
     */
    @Override
    void apply (Project project){


        
    }
}

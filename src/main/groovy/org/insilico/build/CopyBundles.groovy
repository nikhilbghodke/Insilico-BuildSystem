package org.insilico.build

import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.artifacts.Configuration
import org.gradle.api.tasks.Copy
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import org.osgi.framework.Bundle

/**
 * <p>
 * This Custom Task extends {@link Copy} task and adds some default value for {@link org.gradle.api.tasks.Copy#from(java.lang.Object, groovy.lang.Closure)} method and
 *  {@link org.gradle.api.tasks.Copy#into(java.lang.Object)} method of Copy Task. The Default value of {@link org.gradle.api.tasks.Copy#into(java.lang.Object)}
 *  method is set to "build/app" directory and the default value of {@link org.gradle.api.tasks.Copy#from(java.lang.Object, groovy.lang.Closure)}
 *  method is set to "project.configurations.osgiRuntime". <br>
 *  This Custom task can also be used to convert non OSGi bundles to OSGi bundle. All the wrapping part of the Jar is accomplished
 *  by a extension of type {@Link WrapperExtension}. The {@Link WrapperExtension} class is reponsible for actual conversion or wrapping of jars.
 *  In order to use the methods defined in {@Link WrapperExtension} a extension named `wrapper` is added to all tasks within the constructor,
 *  and latter methods defined within the extension are called inside the `wrap` function of this class
 * @see Copy
 * @see WrapperExtension
 * @author Nikhil Ghodke
 * @since 2019-07-29
 * </p>
 */

class CopyBundles extends Copy {
    private Configuration configuration;
    /**<p>
     * The constructor sets the default destination directory as `build/app`, this is the location where the application will get buiult
     * by default,(users can any time change the destination directory by using `into()` of {@Link Copy}).<br>
     * It also sets the default value of `configuration` property as `project.configurations.osgiInstall`, all the dependencies under
     * this specified configuration will be used for building the application (Users can any day set reset the `configurator` property
     * of the task.)<br>
     * Also the constructor adds an extension named `wrapper` of type {@Link WrapperExtension} to all the tasks, (This extension is usefull
     *  for converting non osgi bundles to osgi bundles as specified by the user).
     * </p>
     */
    public CopyBundles(){
        super()
        this.into("build/app")
        this.configuration=project.configurations.osgiInstall;
        this.from(project.configurations.osgiInstall);
        this.extensions.wrapper= new WrapperExtension(this);
    }

    Configuration getConfiguration() {
        return configuration
    }

    void setConfiguration(Configuration configuration) {
        this.configuration = configuration
        this.from(configuration)
    }

    /**
     * This method just calls `wrap` method from the extension of type {@Link WrapperExtension}, this method is reponsible for acctuall
     * conversion of non OSGi Bundles to OSGi Bundles.<br>
     * It also calls Task Action of its parent class ({@Link Copy}).
     */
    @TaskAction
    void convert(){
        super.copy();
        this.extensions.wrapper.wrap();
    }

}
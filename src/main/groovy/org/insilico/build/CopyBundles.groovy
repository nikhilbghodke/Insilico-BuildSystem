package org.insilico.build

import org.gradle.api.tasks.Copy
import org.gradle.api.tasks.Input

/**
 * <p>
 * This Custom Task extends {@link Copy} task and adds some default value for {@link org.gradle.api.tasks.Copy#from(java.lang.Object, groovy.lang.Closure)} method and
 *  {@link org.gradle.api.tasks.Copy#into(java.lang.Object)} method of Copy Task. The Default value of {@link org.gradle.api.tasks.Copy#into(java.lang.Object)}
 *  method is set to "build/app" directory and the default value of {@link org.gradle.api.tasks.Copy#from(java.lang.Object, groovy.lang.Closure)}
 *  method is set to "project.configurations.osgiRuntime".
 * @see Copy
 * @author Nikhil Ghodke
 * @since 2019-07-29
 * </p>
 */

class CopyBundles extends Copy {
    public CopyBundles(){
        super()
        this.into("build/app")
        this.from(project.configurations.osgiInstall)
    }

}
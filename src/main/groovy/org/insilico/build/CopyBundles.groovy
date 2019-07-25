package org.insilico.build

import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Task
import org.gradle.api.artifacts.Configuration
import org.gradle.api.tasks.Copy
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction

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
    private Configuration configuration;
    NamedDomainObjectContainer<WrapperExtension> productContainer;
    public CopyBundles(){
        super()
        this.into("build/app")
        this.configuration=project.configurations.osgiInstall;
        this.from(project.configurations.osgiInstall);
        this.productContainer=project.container(WrapperExtension);
        this.extensions.add('products', productContainer);
    }

    Configuration getConfiguration() {
        return configuration
    }

    void setConfiguration(Configuration configuration) {
        this.configuration = configuration
        this.from(configuration)
    }

    @TaskAction
    void convert(){
        super.copy();
        println( super.getDestinationDir().getAbsoluteFile())
        def products = this.extensions.getByName('products')
        products.each {
            println( it.name)
            Task tempTask=this.getProject().tasks.add(it.name+"_copy",Copy);
            tempTask{
                def zipFile = file(super.getDestinationDir().getAbsolutePath()+"/"+it.name)
                def outputDir = file("${buildDir}/unpacked/"+it.name)

                from zipTree(zipFile)
                into outputDir
            }
        }
    }
}
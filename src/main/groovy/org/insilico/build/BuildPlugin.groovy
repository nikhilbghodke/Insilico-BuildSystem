package org.insilico.build

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.tasks.Copy
import org.osgi.framework.Constants

class BuildPlugin implements Plugin<Project> {
    void apply (Project project)
    {
        project.plugins.apply('biz.aQute.bnd.builder')

        //Adding extensions to jar task
        Task jar= project.getTasks().findByName('jar')
        def ext = jar.extensions.create('bundle',JarExtension)
        jar.inputs.property("name",{ return ext.name}).optional(true)
        jar.inputs.property("symbolicName",{ return ext.symbolicName}).optional(true)
        jar.inputs.property("vendor",{ return ext.vendor}).optional(true)
        jar.inputs.property("developer",{ return ext.developer}).optional(true)
        jar.inputs.property("license",{ return ext.license}).optional(true)
        jar.inputs.property("version",{ return ext.version}).optional(true)
        jar.inputs.property("contactAddress",{ return ext.contactAddress}).optional(true)
        jar.inputs.property("activator",{ return ext.activator}).optional(true)
        jar.doLast {
            if(ext.symbolicName!=null)
                jar.convention.plugins.bundle.bnd(Constants.BUNDLE_SYMBOLICNAME+'='+ext.symbolicName)

            if(ext.name!=null)
                jar.convention.plugins.bundle.bnd(Constants.BUNDLE_NAME+"="+ext.name)

            if(ext.vendor!=null)
                jar.convention.plugins.bundle.bnd(Constants.BUNDLE_VENDOR+'='+ext.vendor)

            //i was getting compilation error if constants of Bundle Developer was used
            // i guess this is something to do with version of bundle(org.osgi.framework)
            if(ext.developer!=null)
                jar.convention.plugins.bundle.bnd("Bundle-Developers="+ext.developer)

            if(ext.license!=null)
                jar.convention.plugins.bundle.bnd(Constants.BUNDLE_LICENSE+"="+ ext.license)

            if(ext.version!=null)
                jar.convention.plugins.bundle.bnd(Constants.BUNDLE_VERSION+"="+ext.version)

            if(ext.contactAddress!=null)
                jar.convention.plugins.bundle.bnd(Constants.BUNDLE_CONTACTADDRESS+"="+ext.contactAddress)

            if(ext.activator!=null)
                jar.convention.plugins.bundle.bnd(Constants.BUNDLE_ACTIVATOR+"="+ext.activator)

            jar.convention.plugins.bundle.buildBundle()
        }
        def dest="build/app"
        def exto= project.extensions.create('bundle',ApplicationExtension)
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

package org.insilico.build

import org.gradle.api.Task
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Optional
import org.osgi.framework.Constants

class JarExtension {
    public String bundleName;
    public String symbolicName
    public String vendor
    public String developer
    public String license
    public String version
    public String contactAddress
    public String activator

    private  Task task;

    public JarExtension(Task task){
        this.task=task;
    }

    @Optional
    @Input
    String getActivator() {
        return activator
    }

    void setActivator(String activator) {
        this.activator = activator
        task.convention.plugins.bundle.bnd(Constants.BUNDLE_ACTIVATOR+"="+this.activator)
    }

    @Optional
    @Input
    String getContactAddress() {
        return contactAddress
    }

    void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress
        task.convention.plugins.bundle.bnd(Constants.BUNDLE_CONTACTADDRESS+"="+this.contactAddress)
    }

    @Input
    @Optional
    String getVersion() {
        return version
    }

    void setVersion(String version) {
        this.version = version
        task.convention.plugins.bundle.bnd(Constants.BUNDLE_VERSION+"="+this.version)
    }

    @Optional
    @Input
    String getLicense() {
        return license
    }

    void setLicense(String license) {
        this.license = license
        task.convention.plugins.bundle.bnd(Constants.BUNDLE_LICENSE+"="+ this.license)
    }

    @Optional
    @Input
    String getDeveloper() {
        return developer
    }

    //
    void setDeveloper(String developer) {
        this.developer = developer
        task.convention.plugins.bundle.bnd("Bundle-Developers="+this.developer)
    }

    @Optional
    @Input
    String getVendor() {
        return vendor
    }

    void setVendor(String vendor) {
        this.vendor = vendor
        task.convention.plugins.bundle.bnd(Constants.BUNDLE_VENDOR+'='+this.vendor)
    }

    @Optional
    @Input
    String getSymbolicName() {
        return symbolicName
    }

    void setSymbolicName(String name) {
        this.symbolicName = name
        task.convention.plugins.bundle.bnd(Constants.BUNDLE_SYMBOLICNAME+"="+this.symbolicName)
    }

    @Optional
    @Input
    String getBundleName() {
        return bundleName
    }

    void setBundleName(String name) {
        this.bundleName = name
        task.convention.plugins.bundle.bnd(Constants.BUNDLE_NAME+"="+this.bundleName)
    }
}

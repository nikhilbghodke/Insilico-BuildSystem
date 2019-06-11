package org.insilico.build

import aQute.bnd.gradle.Bundle
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Optional
import org.osgi.framework.Constants

class OsgiBundle extends Bundle{
    public String name;
    public String symbolicName;
    public String vendor
    public String developer
    public String license
    public String version
    public String contactAddress
    public String activator

    public OsgiBundle(){
        super()
    }


    @Input
    @Optional
    String getSymbolicName() {
        return symbolicName
    }

    void setSymbolicName(String symbolicName) {
        this.symbolicName = symbolicName
        super.convention.plugins.bundle.bnd(Constants.BUNDLE_SYMBOLICNAME+'='+this.symbolicName)
    }

    public void setName(String name){
        this.name=name
        super.convention.plugins.bundle.bnd(Constants.BUNDLE_NAME+"="+this.name)
    }

    @Input
    @Optional
    public String getName(String name){
        return this.name
    }

    @Input
    @Optional
    String getVendor() {
        return vendor
    }

    void setVendor(String vendor) {
        this.vendor = vendor
        super.convention.plugins.bundle.bnd(Constants.BUNDLE_VENDOR+'='+this.vendor)
    }

    @Input
    @Optional
    String getDeveloper() {
        return developer
    }

    //i was getting compilation error if constants of Bundle Developer was used
    // i guess this is something to do with version of bundle(org.osgi.framework)
    // i will try to fix this issue
    void setDeveloper(String developer) {
        this.developer = developer
        super.convention.plugins.bundle.bnd("Bundle-Developers="+this.developer)
    }

    @Input
    @Optional
    String getLicense() {
        return license
    }

    void setLicense(String license) {
        this.license = license
        super.convention.plugins.bundle.bnd(Constants.BUNDLE_LICENSE+"="+ this.license)
    }

    @Input
    @Optional
    String getVersion() {
        return version
    }

    void setVersion(String version) {
        this.version = version
        super.convention.plugins.bundle.bnd(Constants.BUNDLE_VERSION+"="+this.version)
    }

    @Input
    @Optional
    String getContactAddress() {
        return contactAddress
    }

    void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress
        super.convention.plugins.bundle.bnd(Constants.BUNDLE_CONTACTADDRESS+"="+this.contactAddress)
    }

    @Input
    @Optional
    String getActivator() {
        return activator
    }

    void setActivator(String activator) {
        this.activator = activator
        super.convention.plugins.bundle.bnd(Constants.BUNDLE_ACTIVATOR+"="+this.activator)
    }
}

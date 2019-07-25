package org.insilico.build

import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Optional
import org.osgi.framework.Constants

class WrapperExtension {
    /** Used to set Manifest Header Named Bundle-Name*/
    public String bundleName;
    /** Used to set Manifest Header Named Bundle-SymbolicName*/
    public String symbolicName
    /** Used to set Manifest Header Named Bundle-Vendor*/
    public String vendor
    /** Used to set Manifest Header Named Bundle-Developer*/
    public String developer
    /** Used to set Manifest Header Named Bundle-License*/
    public String license
    /** Used to set Manifest Header Named Bundle-Version*/
    public String version
    /** Used to set Manifest Header Named Bundle-ContactAddress*/
    public String contactAddress
    /** Used to set Manifest Header Named Bundle-Activator*/
    public String activator

    public String requireBundle

    public  String name;

    WrapperExtension(String name) {
        this.name = name
    }

    @Input
    @Optional
    String getRequireBundle() {
        return requireBundle
    }

    void setRequireBundle(String requireBundle) {
        this.requireBundle = requireBundle
        //task.convention.plugins.bundle.bnd(Constants.REQUIRE_BUNDLE+"="+this.requireBundle)
    }

    @Optional
    @Input
    String getActivator() {
        return activator
    }

    void setActivator(String activator) {
        this.activator = activator
        //task.convention.plugins.bundle.bnd(Constants.BUNDLE_ACTIVATOR+"="+this.activator)
    }

    @Optional
    @Input
    String getContactAddress() {
        return contactAddress
    }

    void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress
        //task.convention.plugins.bundle.bnd(Constants.BUNDLE_CONTACTADDRESS+"="+this.contactAddress)
    }

    @Input
    @Optional
    String getVersion() {
        return version
    }

    void setVersion(String version) {
        this.version = version
        //task.convention.plugins.bundle.bnd(Constants.BUNDLE_VERSION+"="+this.version)
    }

    @Optional
    @Input
    String getLicense() {
        return license
    }

    void setLicense(String license) {
        this.license = license
        //task.convention.plugins.bundle.bnd(Constants.BUNDLE_LICENSE+"="+ this.license)
    }

    @Optional
    @Input
    String getDeveloper() {
        return developer
    }

    //
    void setDeveloper(String developer) {
        this.developer = developer
        //task.convention.plugins.bundle.bnd("Bundle-Developers="+this.developer)
    }

    @Optional
    @Input
    String getVendor() {
        return vendor
    }

    void setVendor(String vendor) {
        this.vendor = vendor
        //task.convention.plugins.bundle.bnd(Constants.BUNDLE_VENDOR+'='+this.vendor)
    }

    @Optional
    @Input
    String getSymbolicName() {
        return symbolicName
    }

    void setSymbolicName(String name) {
        this.symbolicName = name
        //task.convention.plugins.bundle.bnd(Constants.BUNDLE_SYMBOLICNAME+"="+this.symbolicName)
    }

    @Optional
    @Input
    String getBundleName() {
        return bundleName
    }

    void setBundleName(String name) {
        this.bundleName = name
        //task.convention.plugins.bundle.bnd(Constants.BUNDLE_NAME+"="+this.bundleName)
    }
}

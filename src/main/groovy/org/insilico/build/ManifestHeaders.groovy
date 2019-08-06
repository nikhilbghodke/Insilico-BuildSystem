package org.insilico.build

import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Optional
import org.osgi.framework.Constants

class ManifestHeaders {
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

    public String id;
    public  String initialVersion;

    public Map<String,String> map;

    public ManifestHeaders() {
        this.bundleName="";
        this.symbolicName="";
        this.vendor="";
        this.developer="";
        this.license=""
        this.version=""
        this.contactAddress=""
        this.activator=""
        this.requireBundle=""
        this.id=""
        this.initialVersion=""
        map= new HashMap<>();
    }

    @Input
    @Optional
    String getRequireBundle() {
        return requireBundle
    }

    void setRequireBundle(String requireBundle) {
        this.requireBundle = requireBundle
        this.map.put(Constants.REQUIRE_BUNDLE,requireBundle)
    }

    @Optional
    @Input
    String getActivator() {
        return activator
    }

    void setActivator(String activator) {
        this.activator = activator
        this.map.put(Constants.BUNDLE_ACTIVATOR,activator)
    }

    @Optional
    @Input
    String getContactAddress() {
        return contactAddress
    }

    void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress
        this.map.put(Constants.BUNDLE_CONTACTADDRESS,contactAddress)
    }

    @Input
    @Optional
    String getVersion() {
        return version
    }

    void setVersion(String version) {
        this.version = version
        this.map.put(Constants.BUNDLE_VERSION,version)
    }

    @Optional
    @Input
    String getLicense() {
        return license
    }

    void setLicense(String license) {
        this.license = license
        this.map.put(Constants.BUNDLE_LICENSE,license)
    }

    @Optional
    @Input
    String getDeveloper() {
        return developer
    }

    //
    void setDeveloper(String developer) {
        this.developer = developer
        this.map.put("Bundle-Developers",developer)
    }

    @Optional
    @Input
    String getVendor() {
        return vendor
    }

    void setVendor(String vendor) {
        this.vendor = vendor
        this.map.put(Constants.BUNDLE_VENDOR,vendor)
    }

    @Optional
    @Input
    String getSymbolicName() {
        return symbolicName
    }

    void setSymbolicName(String name) {
        this.symbolicName = name
        this.map.put(Constants.BUNDLE_SYMBOLICNAME,name)
    }

    @Optional
    @Input
    String getBundleName() {
        return bundleName
    }

    void setBundleName(String name) {
        this.bundleName = name
        this.map.put(Constants.BUNDLE_NAME,name)
    }

    public void bnd(String name, String value)
    {
        this.map.put(name,value)
    }
}

package org.insilico.build

import aQute.bnd.gradle.Bundle
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Optional


class ExtendedBuilder extends Bundle{
    public String name;
    public String symbolicName;
    public String vendor
    public String developer
    public String license
    public String version
    public String contactAddress
    public String activator

    public ExtendedBuilder(){
        super()
    }


    @Input
    @Optional
    String getSymbolicName() {
        return symbolicName
    }

    void setSymbolicName(String symbolicName) {
        this.symbolicName = symbolicName
        super.convention.plugins.bundle.bnd('Bundle-SymbolicName': this.symbolicName)
    }

    public void setName(String name){
        this.name=name
        super.convention.plugins.bundle.bnd('Bundle-Name': name)
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
        super.convention.plugins.bundle.bnd('Bundle-Vendor': this.vendor)
    }

    @Input
    @Optional
    String getDeveloper() {
        return developer
    }

    void setDeveloper(String developer) {
        this.developer = developer
        super.convention.plugins.bundle.bnd('Bundle-Developer': this.developer)
    }

    @Input
    @Optional
    String getLicense() {
        return license
    }

    void setLicense(String license) {
        this.license = license
        super.convention.plugins.bundle.bnd('Bundle-License': this.license)
    }

    @Input
    @Optional
    String getVersion() {
        return version
    }

    void setVersion(String version) {
        this.version = version
        super.convention.plugins.bundle.bnd('Bundle-Version': this.version)
    }

    @Input
    @Optional
    String getContactAddress() {
        return contactAddress
    }

    void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress
        super.convention.plugins.bundle.bnd('Bundle-ContactAddress': this.contactAddress)
    }

    @Input
    @Optional
    String getActivator() {
        return activator
    }

    void setActivator(String activator) {
        this.activator = activator
        super.convention.plugins.bundle.bnd('Bundle-Activator': this.activator)
    }
}

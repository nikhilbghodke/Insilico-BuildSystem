
package org.insilico.build

import org.gradle.api.tasks.Input

/**
 *  WriteEquinoxConfiguration Task extends {@link WriteOsgiConfiguration} and adds some function which are used to set properties
 *  inside configuration file only related to Equinox Application.
 *
 *  @see WriteOsgiConfiguration
 */

class WriteEquinoxConfiguration extends WriteOsgiConfiguration {
    /** Used to write ignoreApp Property inside Configuration file. The default value of this
     * property is true.
     */
    public boolean ignoreApp;
    private static final String ECLIPSE_IGNORE_APP = "eclipse.ignoreApp";

    public WriteEquinoxConfiguration(){
        super()
        this.ignoreApp = true
    }

    @Input
    boolean getIgnoreApp() {
        return ignoreApp
    }

    void setIgnoreApp(boolean ignoreApp) {
        this.ignoreApp = ignoreApp
    }

    /**
     * Actually writes the set properties into the configuration file
     */
    @Override
    public void writeProperties() {
        property(ECLIPSE_IGNORE_APP,this.ignoreApp)
        super.writeProperties()
    }
}

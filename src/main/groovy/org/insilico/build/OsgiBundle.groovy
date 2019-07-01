package org.insilico.build

import aQute.bnd.gradle.Bundle

/**
 * Osgi Bundle Task extends {@link Bundle} Task from Bnd Gradle Plugin, used to create Osgi Bundles.
 * This task adds a extension named "bundle" to each task of type OsgiBundle. This extension  provides properties which can be used to
 * set Manifest headers in Osgi Bundles
 *
 * @see JarExtension
 * @author Nikhil Ghodke
 * @since 2019-07-29
 */

class OsgiBundle extends Bundle{
    public OsgiBundle()
    {
        super()
        this.extensions.bundle=new JarExtension(this)
    }
}

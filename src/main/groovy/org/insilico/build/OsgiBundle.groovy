package org.insilico.build

import aQute.bnd.gradle.Bundle

class OsgiBundle extends Bundle{
    public OsgiBundle()
    {
        super()
        this.extensions.bundle=new JarExtension(this)
    }
}

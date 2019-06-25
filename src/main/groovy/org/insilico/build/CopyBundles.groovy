package org.insilico.build

import org.gradle.api.tasks.Copy

class CopyBundles extends Copy {
    public String buildDir;

    public CopyBundles(){
        super()
        this.buildDir="build/app"
        this.into("build/app")
        this.from(project.configurations.osgiRuntime)
    }

    String getBuildDir() {
        return buildDir
    }

    void setBuildDir(String buildDir) {
        this.buildDir = buildDir
        into(buildDir)
    }
}
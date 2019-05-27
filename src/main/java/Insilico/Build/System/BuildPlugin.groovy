package Insilico.Build.System

import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * Created by jamesharmon on 5/3/17.
 */
class BuildPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        project.task('taska',type: LatestArtifactVersion)
    }
}


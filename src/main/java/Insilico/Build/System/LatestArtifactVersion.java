package Insilico.Build.System;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.TaskAction;

public class LatestArtifactVersion extends DefaultTask {
	private String coordinates;
    private String serverUrl;

    /*
    Incremental build note
    This annotation should be attached to the getter method in Java or the property in Groovy. Annotations on setters or
    just the field in Java are ignored.

    This will cause the task to be considered out-of-date when the property has changed. When used on a File object that refers
    to a file or directory, the up-to-date check is only dependent on the path and not the contents of the file or directory.
    To make it depend on the contents, use InputFile or InputDirectory respectively.
     */


    @Input
    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    @Input
    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    @TaskAction
    public void resolveLatestVersion() {
        System.out.println("Retrieving artifact " + coordinates + " from " + serverUrl);
        // issue HTTP call and parse response
    }
}

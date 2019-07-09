package org.insilico.build

import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Optional

class FeaturePropertyExtension {
    public String url="";
    public String context="";

    @Input
    @Optional
    String getUrl() {
        return url
    }

    void setUrl(String url) {
        this.url = url
    }

    @Input
    @Optional
    String getContext() {
        return context
    }

    void setContext(String context) {
        this.context = context
    }
}

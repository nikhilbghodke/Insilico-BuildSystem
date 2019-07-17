package org.insilico.build

import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Optional

/**
 * The main aim of this class is to be used to create object which are latter used to configure Closure passed into
 * functions of {@Link FeatureExtension}. Methods like copyright, description and license. This class are is not used
 * anywhere else within the plugin.
 *
 * @see FeatureExtension
 * @author Nikhil Ghodke
 * @since @since 2019-07-17
 */
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

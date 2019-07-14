package org.insilico.build

import org.gradle.api.Task
import org.w3c.dom.Attr
import org.w3c.dom.Document
import org.w3c.dom.Element

class FeatureRequirePropertyExtension {

    private Task task
    private Element rootElement
    private List<Plugin> plugin
    private List<Feature> feature
    private Document doc

    FeatureRequirePropertyExtension(Task task, Element rootElement, Document doc) {
        this.task = task
        this.rootElement = rootElement
        this.plugin = new ArrayList<>()
        this.feature = new ArrayList<>()
        this.doc = doc
    }


    void importPlugin(String id, Closure configurator) {
        Closure cfg = configurator.clone()
        Plugin a = new Plugin(id)
        cfg.delegate = a
        cfg.resolveStrategy = Closure.DELEGATE_FIRST
        cfg.call()

        this.plugin.add(a)
    }

    void importFeature(String id, Closure configurator) {
        Closure cfg = configurator.clone()
        Feature a = new Feature(id)
        cfg.delegate = a
        cfg.resolveStrategy = Closure.DELEGATE_FIRST
        cfg.call()

        this.feature.add(a)
    }

    void createRequireElement() throws java.lang.Exception {
        Element requires = this.doc.createElement("requires")
        this.rootElement.appendChild(requires)

        for (Plugin a : plugin) {

            a.check()
            Element plugin = this.doc.createElement("import")
            requires.appendChild(plugin)

            Attr pluginID = doc.createAttribute("plugin")
            pluginID.setValue(a.id)
            plugin.setAttributeNode(pluginID)

            if (a.version.length() != 0) {
                Attr pluginVersion = doc.createAttribute("version")
                pluginVersion.setValue(a.version)
                plugin.setAttributeNode(pluginVersion)
            }
            if (a.match.length() != 0) {
                Attr pluginMatch = doc.createAttribute("match")
                pluginMatch.setValue(a.match)
                plugin.setAttributeNode(pluginMatch)
            }


        }

        for (Feature a : feature) {

            a.check()
            Element feature = this.doc.createElement("import")
            requires.appendChild(feature)

            Attr featureID = doc.createAttribute("feature")
            featureID.setValue(a.id)
            feature.setAttributeNode(featureID)

            if (a.version.length() != 0) {
                Attr featureVersion = doc.createAttribute("version")
                featureVersion.setValue(a.version)
                feature.setAttributeNode(featureVersion)
            }
            if (a.match.length() != 0 || a.version.length() != 0) {
                Attr featureMatch = doc.createAttribute("match")
                featureMatch.setValue(a.match)
                feature.setAttributeNode(featureMatch)
            }
            if (a.patch != false) {
                Attr featurePatch = doc.createAttribute("patch")
                featurePatch.setValue(a.patch.toString())
                feature.setAttributeNode(featurePatch)
            }

        }

    }


    private class Plugin {
        public String id = ""
        public String version = ""
        public String match = ""

        Plugin(String id) {
            this.id = id
        }

        void check() {

            if (this.match.length() != 0) {
                String[] allowedValues = new String[4]
                allowedValues[0] = "perfect"
                allowedValues[1] = "equivalent"
                allowedValues[2] = "compatible"
                allowedValues[3] = "greaterOrEqual"
                boolean found = false
                for (String a : allowedValues) {
                    if (a == this.match) {
                        found = true
                        break
                    }
                }

                if (found == false) {
                    throw new Exception(this.match + " is not a permitted value.\nPermitted values are \n1) perfect\n2)equivalent \n3)compatible" +
                            "\n4)greaterOrEqual")
                }
            }

        }
    }

    private class Feature {
        public String id = ""
        public String version = ""
        public String match = ""
        public boolean patch = false

        Feature(String id) {

            this.id = id

        }

        void check() {

            if (this.match.length() != 0) {
                String[] allowedValues = new String[4]
                allowedValues[0] = "perfect"
                allowedValues[1] = "equivalent"
                allowedValues[2] = "compatible"
                allowedValues[3] = "greaterOrEqual"
                boolean found = false
                for (String a : allowedValues) {
                    if (a == this.match) {
                        found = true
                        break
                    }
                }

                if (found == false) {
                    throw new Exception(this.match + " is not a permitted value.\nPermitted values are \n1) perfect\n2)equivalent \n3)compatible" +
                            "\n4)greaterOrEqual")
                }
            }

            if (this.patch == true) {
                if (this.version.length() == 0)
                    throw new Exception("If the value of Patch for feature with id " + this.id + " is set to true, then version number is required to be set!")

                if (this.match != "perfect") {
                    this.match = "perfect"
                }
            }
        }
    }

}

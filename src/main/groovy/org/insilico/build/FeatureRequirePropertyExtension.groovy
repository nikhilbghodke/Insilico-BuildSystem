package org.insilico.build

import org.gradle.api.Project
import org.gradle.api.Task
import org.w3c.dom.Attr
import org.w3c.dom.Document
import org.w3c.dom.Element

/**
 * This class is used for create extension for tasks which apply extensions of type {@Link FeatureExtension}
 * and are used to store properties about dependencies of features and write manifest files
 */
class FeatureRequirePropertyExtension {

    /**Used to store task object for internal use*/
    private Task task
    /**Used to store root element of the Xml file*/
    private Element rootElement

    /**Used to store all plugin dependencies of the feature*/
    private List<Plugin> plugin

    /**Used to store all plugin dependencies of the feature*/
    private List<Feature> feature
    /**Used to Store the document object to which the XML file is written*/
    private Document doc

     enum ValidValues {
        PERFECT("perfect"),
        COMPATIBLE("compatible"),
        GREATOREQUAL("greaterOrEqual")
        ;
        private final String value;
        ValidValues(String value) {
            this.value = value;
        }
        public String value() {return value;}

        static public boolean isMember(String aName) {
            ValidValues[] aValues = ValidValues.values();
            for (ValidValues aValue : aValues)
                if (aValue.value.equals(aName))
                    return true;
            return false;
        }
    }
    FeatureRequirePropertyExtension(Task task, Element rootElement, Document doc) {
        this.task = task
        this.rootElement = rootElement
        this.plugin = new ArrayList<>()
        this.feature = new ArrayList<>()
        this.doc = doc
        this.task.inputs.property("plugins",{plugin.toString()})
        this.task.inputs.property("feature",{feature.toString()})
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

    void writeManifestFile(){

        Project project=this.task.getProject()

        this.task.manifest {
            attributes('Symbolic-Name': project.getName())
            if(project.getVersion()=="unspecified")
            attributes('Bundle-Version': project.getVersion())
        }

        String RequireBundle=""
        for (Feature a:feature){
            RequireBundle+=a.id+";bundle-version=\""+a.version+"\",\n "
        }
        for (Plugin a:plugin){
            RequireBundle+=a.id+";bundle-version=\""+a.version+"\",\n "
        }
        println("this what should be writen in Manifest file\n"+RequireBundle)
        this.task.manifest{
            attributes('Require-Bundle': RequireBundle)
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
//                String[] allowedValues = new String[4]
//                allowedValues[0] = "perfect"
//                allowedValues[1] = "equivalent"
//                allowedValues[2] = "compatible"
//                allowedValues[3] = "greaterOrEqual"

//                for (String a : allowedValues) {
//                    if (a == this.match) {
//                        found = true
//                        break
//                    }
//                }

                boolean found = false
                found=ValidValues.isMember(this.match)
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
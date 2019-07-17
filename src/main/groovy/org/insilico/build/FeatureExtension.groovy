package org.insilico.build


import org.gradle.api.Task
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Optional
import org.w3c.dom.Attr
import org.w3c.dom.Document
import org.w3c.dom.Element

import javax.xml.parsers.DocumentBuilder
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.transform.OutputKeys
import javax.xml.transform.Transformer
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult
/**
 * This is the most important class which is used to creating extension which are latter added to `jar` task and
 * each task of type {@Link EquinoxFeature}. This class defines properties and methods used for writing `feature.xml`
 * files.
 * @see EquinoxFeature
 * @author Nikhil Ghodke
 * @since @since 2019-07-17
 */

class FeatureExtension {
    /**Used to store task object to whic the extension is added*/
    private Task task

    /**This String is used while creating file to store the created feature XML file*/
    private String buildDir = "build/features/feature.xml"


    //ids of feature element
    /**Used to configure the version of feature archive*/
    public String version

   /** Used to configure the id of feature*/
    public String id

    /**Used to configure the providerName of feature*/
    public String providerName

    /**Used to write short descriptive label inside the XML file*/
    public String label

    /** Used to set optional property `os` for feature XML file */
    public String os

    /** Used to set optional property `arch` for feature XML file */
    public String arch

    /** Used to set optional property `ws` for feature XML file */
    public String ws

    /** Used to set optional property `nl` for feature XML file */
    public String nl

    /**The object is used by description method to configure the configure the closure and later extract information from
     * them regarding description
     * */
    public FeaturePropertyExtension description

    /**The object is used by copyright method to configure the configure the closure and later extract information from
     * them regarding copyright
     * */
    public FeaturePropertyExtension copyright

    /**The object is used by license method to configure the configure the closure and later extract information from
     * them regarding license
     * */
    public FeaturePropertyExtension license

    /**This List is used to store all the Features that the user wants to include*/
    public List<FeatureIncludesProperty> includes
    /**This List is used to store all the Pluginss that the user wants to include*/
    public List<FeaturePluginProperty> plugins

    /**The Below properties are used during the XML file creation*/
    public Document doc
    public Element featureRootElement


    /**
     * The constructor does few things
     * 1) Initializes all the String member used for property setting to ""
     * 2) Creates all the required instance of {@Link FeaturePropertyExtension}
     * 3) Initializes Both the lists user for storing information
     * 4) Creates the Document object and Root elemnt for the XML file to be created
     * 5) Add another extension named `require` to the task object
     * 6)Creates `build`, `build/libs` and `build/features` directory (creating directory was necessary or else it
     * was giving error in some case) and `build/features/feature.xml`.
     * @param task this object is stored into a private member for further usage
     */
    FeatureExtension(Task task) {

        //Initializes all the String member used for property setting to ""
        this.task = task
        this.providerName = ""
        this.label = ""
        this.os = ""
        this.arch = ""
        this.ws = ""
        this.nl = ""
        this.version = ""
        this.id = ""

       // Creates all the required instance of {@Link FeaturePropertyExtension}
        this.description = new FeaturePropertyExtension()
        this.license = new FeaturePropertyExtension()
        this.copyright = new FeaturePropertyExtension()

        //Initializes Both the lists user for storing information
        this.includes = new ArrayList<>()
        this.plugins = new ArrayList<>()

        //Creates the document object for xml file
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance()
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder()
        this.doc = docBuilder.newDocument()
        // Create Person root element
        this.featureRootElement = doc.createElement("feature")

        //Add another extension named `require` to the task object
        task.extensions.require = new FeatureRequirePropertyExtension(task, featureRootElement, doc)

        //Creates `build`, `build/libs` and `build/features` directory (creating directory was necessary or else it was giving error in some case)
        // and `build/features/feature.xml`.
        task.destinationDirectory = new File("build/libs")
        boolean buildMade=new File("build").mkdirs();
        boolean featuresMade=new File("build/features").mkdirs();
        task.from new File(this.buildDir)

    }

    @Input
    @Optional
    String getWs() {
        return ws
    }

    void setWs(String ws) {
        this.ws = ws
    }

    @Input
    @Optional
    String getOs() {
        return os
    }

    void setOs(String os) {
        this.os = os
    }

    @Input
    @Optional
    String getNl() {
        return nl
    }

    void setNl(String nl) {
        this.nl = nl
    }

    @Input
    @Optional
    String getArch() {
        return arch
    }

    void setArch(String arch) {
        this.arch = arch
    }

    @Input
    @Optional
    String getVersion() {
        return version
    }

    void setVersion(String version) {
        this.version = version
    }

    @Input
    @Optional
    String getProviderName() {
        return providerName
    }

    void setProviderName(String providerName) {
        this.providerName = providerName
    }

    @Input
    @Optional
    String getLabel() {
        return label
    }

    void setLabel(String label) {
        this.label = label
    }

    @Input
    @Optional
    String getId() {
        return id
    }

    void setId(String id) {
        this.id = id
    }


/**
 * This method is used to set all the description related information within the feature.
 * It uses the description object created within the constructor to extract the information from Closure passed.
 * @param configurator the closure is used to create a DSL and latter extract information from it within the function
 */
    void description(@DelegatesTo(FeaturePropertyExtension) Closure configurator) {
        Closure cfg = configurator.clone()
        cfg.delegate = description
        cfg.resolveStrategy = Closure.DELEGATE_FIRST
        cfg.call()

    }
/**
 * This method is used to set all the copyright related information within the feature.
 * It uses the copyright object created within the constructor to extract the information from Closure passed.
 * @param configurator the closure is used to create a DSL and latter extract information from it within the function
 */
    void copyright(@DelegatesTo(FeaturePropertyExtension) Closure configurator) {
        Closure cfg = configurator.clone()
        cfg.delegate = copyright
        cfg.resolveStrategy = Closure.DELEGATE_FIRST
        cfg.call()

    }

    /**
     * This method is used to set all the license related information within the feature.
     * It uses the license object created within the constructor to extract the information from Closure passed.
     * @param configurator the closure is used to create a DSL and latter extract information from it within the function
     */
    void license(@DelegatesTo(FeaturePropertyExtension) Closure configurator) {
        Closure cfg = configurator.clone()
        cfg.delegate = license
        cfg.resolveStrategy = Closure.DELEGATE_FIRST
        cfg.call()

    }


    /**
     *
     * This methods takes three parameter namely id,version and a Closure which is used to create a object of Class
     * FeatureIncludesProperty and the created object is then added to list named includes. IN order to resolve the
     * passed Closure an object of class FeatureIncludesProperty(it is a private inner class)
     * @param id id of the feature to be included
     * @param version version of the feature to be included
     * @param configurator used to pass all the optional information regarding the required feature
     */
    void includes(String id, String version, Closure configurator) {
        Closure cfg = configurator.clone()
        FeatureIncludesProperty newFeature = new FeatureIncludesProperty(id, version)
        cfg.delegate = newFeature
        cfg.resolveStrategy = Closure.DELEGATE_FIRST
        cfg.call()

        this.includes.add(newFeature)
    }

    /**
     *
     * This methods takes three parameter namely id,version and a Closure which is used to create a object of Class
     * FeaturePluginProperty and the created object is then added to list named plugins. In order to resolve the
     * passed Closure an object of class FeaturePluginProperty(it is a private inner class)
     * @param id id of the plugin to be included
     * @param version version of the plugin to be included
     * @param configurator used to pass all the optional information regarding the required plugin
     */
    void plugin(String id, String version, Closure configurator) {
        Closure cfg = configurator.clone()
        FeaturePluginProperty newPlugin = new FeaturePluginProperty(id, version)
        cfg.delegate = newPlugin
        cfg.resolveStrategy = Closure.DELEGATE_FIRST
        cfg.call()

        this.plugins.add(newPlugin)
    }

/**
 * This function simply writes creates a element , adds attributes to them and then adds the created element to
 * Root Element of the XML file. Before creating the element or attribute, it first checks if it s set by the user, thereby
 * does not create unncessary elements and attributes in XML file
 */
    void writeFile() {


        String personXMLStringValue = null

        //Attributes are added to feature element
        if (this.version.length() == 0) {
            this.version = task.getProject().version
        }
        if (this.id.length() == 0) {
            this.id = task.getProject().name
        }


        Attr featureVersion = doc.createAttribute("version")
        featureVersion.setValue(this.version)
        featureRootElement.setAttributeNode(featureVersion)

        Attr featureId = doc.createAttribute("id")
        featureId.setValue(this.id)
        featureRootElement.setAttributeNode(featureId)

        if (this.label.length() != 0) {
            Attr featureLabel = doc.createAttribute("label")
            featureLabel.setValue(this.label)
            featureRootElement.setAttributeNode(featureLabel)
        }

        if (this.providerName.length() != 0) {
            Attr featureProviderName = doc.createAttribute("provider-name")
            featureProviderName.setValue(this.providerName)
            featureRootElement.setAttributeNode(featureProviderName)
        }

        if (this.os.length() != 0) {
            Attr featureOs = doc.createAttribute("os")
            featureOs.setValue(this.os)
            featureRootElement.setAttributeNode(featureOs)
        }

        if (this.ws.length() != 0) {
            Attr featureWs = doc.createAttribute("ws")
            featureWs.setValue(this.providerName)
            featureRootElement.setAttributeNode(featureWs)
        }

        if (this.arch.length() != 0) {
            Attr featureArch = doc.createAttribute("arch")
            featureArch.setValue(this.providerName)
            featureRootElement.setAttributeNode(featureArch)
        }


        //feature element added to document
        doc.appendChild(featureRootElement)


        //if context is not set, it set to default value as per in eclipse IDE
        if (this.description.context.length() == 0)
            description.context = "[Enter Feature Description here.]"

        //if url is not set, it set to default value as per in eclipse IDE
        if (this.description.url.length() == 0)
            description.url = "http://www.example.com/description"


        // Create Description Element
        Element description = doc.createElement("description")
        description.appendChild(doc.createTextNode(this.description.context))
        featureRootElement.appendChild(description)

        Attr descriptionUrl = doc.createAttribute("url")
        descriptionUrl.setValue(this.description.url)
        description.setAttributeNode(descriptionUrl)


        //if context is not set, it set to default value as per in eclipse IDE
        if (this.copyright.context.length() == 0)
            copyright.context = "[Enter Copyright Description here.]"

        //if url is not set, it set to default value as per in eclipse IDE
        if (this.copyright.url.length() == 0)
            description.url = "http://www.example.com/copyright"

        Element copyright = doc.createElement("copyright")
        copyright.appendChild(doc.createTextNode(this.copyright.context))
        featureRootElement.appendChild(copyright)

        Attr copyrightUrl = doc.createAttribute("url")
        copyrightUrl.setValue(this.copyright.url)
        copyright.setAttributeNode(copyrightUrl)

        //if context is not set, it set to default value as per in eclipse IDE
        if (this.license.context.length() == 0)
            license.context = "[Enter License Description here.]"

        //if url is not set, it set to default value as per in eclipse IDE
        if (this.license.url.length() == 0)
            license.url = "http://www.example.com/copyright"


        Element license = doc.createElement("license")
        license.appendChild(doc.createTextNode(this.license.context))
        featureRootElement.appendChild(license)

        Attr licenseUrl = doc.createAttribute("url")
        licenseUrl.setValue(this.license.url)
        license.setAttributeNode(licenseUrl)


        //writes all the includes elements with all optional properties
        for (FeatureIncludesProperty a : this.includes) {
            Element includes = doc.createElement("includes")
            featureRootElement.appendChild(includes)

            Attr id = doc.createAttribute("id")
            id.setValue(a.id)
            includes.setAttributeNode(id)

            Attr version = doc.createAttribute("version")
            version.setValue(a.version)
            includes.setAttributeNode(version)

            Attr optional = doc.createAttribute("optional")
            optional.setValue(a.optional.toString())
            includes.setAttributeNode(optional)

            if (a.ws.length() != 0) {
                Attr ws = doc.createAttribute("ws")
                ws.setValue(a.ws)
                includes.setAttributeNode(ws)
            }

            if (a.os.length() != 0) {
                Attr os = doc.createAttribute("os")
                os.setValue(a.os)
                includes.setAttributeNode(os)
            }

            if (a.nl.length() != 0) {
                Attr nl = doc.createAttribute("nl")
                nl.setValue(a.nl)
                includes.setAttributeNode(nl)
            }

            if (a.arch.length() != 0) {
                Attr arch = doc.createAttribute("arch")
                arch.setValue(a.arch)
                includes.setAttributeNode(arch)
            }

            if (a.name.length() != 0) {
                Attr name = doc.createAttribute("name")
                name.setValue(a.name)
                includes.setAttributeNode(name)
            }
        }


        for (FeaturePluginProperty a : this.plugins) {
            Element plugin = doc.createElement("plugin")
            featureRootElement.appendChild(plugin)

            Attr id = doc.createAttribute("id")
            id.setValue(a.id)
            plugin.setAttributeNode(id)

            Attr version = doc.createAttribute("version")
            version.setValue(a.version)
            plugin.setAttributeNode(version)

            Attr fragment = doc.createAttribute("fragment")
            fragment.setValue(a.fragment.toString())
            plugin.setAttributeNode(fragment)

            Attr unpack = doc.createAttribute("unpack")
            unpack.setValue(a.unpack.toString())
            plugin.setAttributeNode(unpack)

            Attr downloadSize = doc.createAttribute("download-size")
            downloadSize.setValue(a.downloadSize)
            plugin.setAttributeNode(downloadSize)

            Attr installSize = doc.createAttribute("install-size")
            installSize.setValue(a.installSize)
            plugin.setAttributeNode(installSize)


            if (a.ws.length() != 0) {
                Attr ws = doc.createAttribute("ws")
                ws.setValue(a.ws)
                plugin.setAttributeNode(ws)
            }

            if (a.os.length() != 0) {
                Attr os = doc.createAttribute("os")
                os.setValue(a.os)
                plugin.setAttributeNode(os)
            }

            if (a.nl.length() != 0) {
                Attr nl = doc.createAttribute("nl")
                nl.setValue(a.nl)
                plugin.setAttributeNode(nl)
            }

            if (a.arch.length() != 0) {
                Attr arch = doc.createAttribute("arch")
                arch.setValue(a.arch)
                plugin.setAttributeNode(arch)
            }

        }

        //Writes all the dependencies in require element of feature.xml files
        this.task.extensions.require.createRequireElement()

        // Transform Document to XML String
        TransformerFactory tf = TransformerFactory.newInstance()
        Transformer transformer = tf.newTransformer()

        //just for identation
        transformer.setOutputProperty(OutputKeys.INDENT, "yes")
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2")


        StringWriter writer = new StringWriter()
        transformer.transform(new DOMSource(doc), new StreamResult(writer))
        // Get the String value of final xml document
        personXMLStringValue = writer.getBuffer().toString()

        //System.out.println(personXMLStringValue)
        //System.out.println("Done creating XML File")


        FileWriter a = new FileWriter(this.buildDir)
        a.write(personXMLStringValue)
        a.close()
    }


    /**
     * This function simply calls two functions
     * 1) writeFile() :- to write the `feature.xml` file itself
     * 2) writeManifestFile :- this method from {@Link FeatureRequirePropertyExtension} is used to write manifest file inside the
     * archives
     */

    void createFeatureArchive() {
        //writes the feature.xml file
        this.writeFile()



        //Writes the manifest file for feature
        this.task.extensions.require.writeManifestFile()
    }


/**
 * This inner Class is used just to create objects inside `includes` method inorder to resolve the passed closure and extract the
 * required information.This class is o=not used anywhere else.
 */
    class FeatureIncludesProperty {
        public String id = ""
        public String version = "0.0.0"
        public String os = ""
        public String arch = ""
        public String ws = ""
        public String nl = ""
        public String name = ""
        public boolean optional = true

        FeatureIncludesProperty(String id, String version) {
            this.id = id
            this.version = version
        }
    }
/**
 * This inner Class is used just to create objects inside `plugin` method inorder to resolve the passed closure and extract the
 * required information. This class is o=not used anywhere else.
 */
    class FeaturePluginProperty {
        public boolean fragment = false
        public boolean unpack = true
        public String downloadSize = "0"
        public String installSize = "0"

        public String id = ""
        public String version = "0.0.0"
        public String os = ""
        public String arch = ""
        public String ws = ""
        public String nl = ""

        FeaturePluginProperty(String id, String version) {
            this.version = version
            this.id = id
        }
    }
}
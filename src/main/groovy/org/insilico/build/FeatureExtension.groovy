package org.insilico.build


import org.gradle.api.Task
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Optional
import org.w3c.dom.Attr
import org.w3c.dom.Document
import org.w3c.dom.Element

import javax.xml.parsers.DocumentBuilder
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.transform.Transformer
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult

class FeatureExtension {
    private Task task;
    public File buildDir= new File("feature.xml");


    //ids of feature element
    public String version;
    public String id;
    public String providerName;
    public String label;
    public String os;
    public String arch;
    public String ws;
    public String nl;

    public  FeaturePropertyExtension description;
    public  FeaturePropertyExtension copyright;
    public  FeaturePropertyExtension license;

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

    public FeatureExtension(Task task){
        this.task=task;
        this.version=task.getProject().version;
        this.id=task.getProject().name;
        this.providerName="";
        this.label="";
        this.os=""
        this.arch=""
        this.ws=""
        this.nl=""

        this.description= new FeaturePropertyExtension()
        this.license= new FeaturePropertyExtension()
        this.copyright= new FeaturePropertyExtension()



       // task.inputs.property('description', { .extensions.description.getUrl() })

    }

    void description( @DelegatesTo(FeaturePropertyExtension) Closure configurator) {
        Closure cfg = configurator.clone()
        cfg.delegate = description
        cfg.resolveStrategy = Closure.DELEGATE_FIRST
        cfg.call()

    }

    void copyright( @DelegatesTo(FeaturePropertyExtension) Closure configurator) {
        Closure cfg = configurator.clone()
        cfg.delegate = copyright
        cfg.resolveStrategy = Closure.DELEGATE_FIRST
        cfg.call()

    }

    void license( @DelegatesTo(FeaturePropertyExtension) Closure configurator) {
        Closure cfg = configurator.clone()
        cfg.delegate = license
        cfg.resolveStrategy = Closure.DELEGATE_FIRST
        cfg.call()

    }

//    public void description( Action<? super Description> action){
//        action.execute(description);
//        //println this.description.n+"was passed";
//    }



//    void plugin( String plugin,@DelegatesTo(PlainObject) Closure configurator) {
//        Closure cfg = configurator.clone()
//        cfg.delegate = description
//        cfg.resolveStrategy = Closure.DELEGATE_FIRST
//        cfg.call()
//
//
//        println( plugin)
//    }


    public void  writeFile(){

        String personXMLStringValue = null;
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.newDocument();




        // Create Person root element
        Element featureRootElement = doc.createElement("feature");



        //Attributes are added to feature element
        Attr featureVersion = doc.createAttribute("version");
        featureVersion.setValue(this.version)
        featureRootElement.setAttributeNode(featureVersion);

        Attr featureId = doc.createAttribute("id");
        featureId.setValue(this.id)
        featureRootElement.setAttributeNode(featureId);

        if(this.label.length()!=0) {
            Attr featureLabel = doc.createAttribute("label");
            featureLabel.setValue(this.label)
            featureRootElement.setAttributeNode(featureLabel);
        }

        if(this.providerName.length()!=0) {
            Attr featureProviderName = doc.createAttribute("provider-name");
            featureProviderName.setValue(this.providerName)
            featureRootElement.setAttributeNode(featureProviderName);
        }

        if(this.os.length()!=0) {
            Attr featureOs = doc.createAttribute("os")
            featureOs.setValue(this.os)
            featureRootElement.setAttributeNode(featureOs)
        }

        if(this.ws.length()!=0) {
            Attr featureWs = doc.createAttribute("ws");
            featureWs.setValue(this.providerName)
            featureRootElement.setAttributeNode(featureWs)
        }

        if(this.arch.length()!=0) {
            Attr featureArch = doc.createAttribute("arch")
            featureArch.setValue(this.providerName)
            featureRootElement.setAttributeNode(featureArch);
        }


        //feature element added to document
        doc.appendChild(featureRootElement);






        //if context is not set, it set to default value as per in eclipse IDE
        if(this.description.context.length()==0)
            description.context="[Enter Feature Description here.]"

        //if url is not set, it set to default value as per in eclipse IDE
        if(this.description.url.length()==0)
            description.url="http://www.example.com/description"


        // Create Description Element
        Element description = doc.createElement("description");
        description.appendChild(doc.createTextNode(this.description.context));
        featureRootElement.appendChild(description);

        Attr descriptionUrl = doc.createAttribute("url")
        descriptionUrl.setValue(this.description.url)
        description.setAttributeNode(descriptionUrl);


        //if context is not set, it set to default value as per in eclipse IDE
        if(this.copyright.context.length()==0)
            copyright.context="[Enter Copyright Description here.]"

        //if url is not set, it set to default value as per in eclipse IDE
        if(this.copyright.url.length()==0)
            description.url="http://www.example.com/copyright"

        Element copyright = doc.createElement("copyright");
        copyright.appendChild(doc.createTextNode(this.copyright.context));
        featureRootElement.appendChild(copyright);

        Attr copyrightUrl = doc.createAttribute("url")
        copyrightUrl.setValue(this.copyright.url)
        copyright.setAttributeNode(copyrightUrl);

        //if context is not set, it set to default value as per in eclipse IDE
        if(this.license.context.length()==0)
            license.context="[Enter License Description here.]"

        //if url is not set, it set to default value as per in eclipse IDE
        if(this.license.url.length()==0)
            license.url="http://www.example.com/copyright"


        Element license = doc.createElement("license");
        license.appendChild(doc.createTextNode(this.license.context));
        featureRootElement.appendChild(license);

        Attr licenseUrl = doc.createAttribute("url")
        licenseUrl.setValue(this.license.url)
        license.setAttributeNode(licenseUrl);

        




        // Transform Document to XML String
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        StringWriter writer = new StringWriter();
        transformer.transform(new DOMSource(doc), new StreamResult(writer));
        // Get the String value of final xml document
        personXMLStringValue = writer.getBuffer().toString();

        System.out.println(personXMLStringValue);
        System.out.println("Done creating XML File");

        FileWriter a=new FileWriter(this.buildDir)
        a.write(personXMLStringValue)
        a.close()
    }
}


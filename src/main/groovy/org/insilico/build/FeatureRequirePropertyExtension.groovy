package org.insilico.build

import org.gradle.api.Task
import org.w3c.dom.Attr
import org.w3c.dom.Document
import org.w3c.dom.Element

class FeatureRequirePropertyExtension {

    private Task task;
    private Element rootElement;
    private List<String> plugin;
    private Map<String,String> feature;
    private Document doc;

    public  FeatureRequirePropertyExtension(Task task, Element rootElement, Document doc){
        this.task=task
        this.rootElement=rootElement
        this.plugin=new ArrayList<>()
        this.feature= new TreeMap<>()
        this.doc=doc
    }

    public void importPlugin(String id){
        this.plugin.add(id)
    }

    public  void importFeature(String id, String version){
        this.feature.put(id,version)
    }

    public void createRequireElement(){
        Element requires = this.doc.createElement("requires");
        this.rootElement.appendChild(requires);

        for(String a:plugin){
            Element plugin = this.doc.createElement("import");
            requires.appendChild(plugin);

            Attr pluginID = doc.createAttribute("plugin")
            pluginID.setValue(a)
            plugin.setAttributeNode(pluginID);
        }

        // Returns Set view
        Set< Map.Entry< String,Integer> > st = this.feature.entrySet();

        for (Map.Entry< String,Integer> me:st)
        {
            Element feature = this.doc.createElement("import");
            requires.appendChild(feature);

            Attr featureID = doc.createAttribute("feature")
            featureID.setValue(me.getKey())
            feature.setAttributeNode(featureID);

            Attr featureVersion = doc.createAttribute("version")
            featureVersion.setValue(me.getValue())
            feature.setAttributeNode(featureVersion);
        }
    }

}

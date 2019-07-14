package org.insilico.build;

import org.gradle.api.Action;
import org.gradle.api.DefaultTask;
import org.gradle.api.Task;
import org.gradle.api.tasks.TaskAction
import org.gradle.jvm.tasks.Jar
import org.w3c.dom.Attr
import org.w3c.dom.Document
import org.w3c.dom.Element

import javax.xml.parsers.DocumentBuilder
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.transform.Transformer
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult;

public class EquinoxFeature extends Jar {

    public EquinoxFeature(){
        super()
        this.extensions.feature= new FeatureExtension(this)
    }

    @TaskAction
    void Feature(){

        this.extensions.feature.createFeatureArchive()
        this.copy()

    }
}

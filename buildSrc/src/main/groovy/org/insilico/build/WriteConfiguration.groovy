package org.insilico.build

import org.gradle.api.tasks.WriteProperties

class WriteConfiguration extends WriteProperties {
    private String noShutdown;
    private String ignoreApp;
    public WriteConfiguration(){
        this.outputFile("build/app/configuration/config.ini")
        this.noShutdown="true"
        this.ignoreApp="true"
    }

    void noShutdown(String a){
        this.noShutdown=a
    }

    void ignoreApp(String a)
    {
        this.ignoreApp=a
    }
    @Override
    public void writeProperties() {

//        // made a directory named configuration if it does not exists
//        File a = new File("build/app/configuration")
//        def q=a.mkdir()
        String bundles= ""
        File app=new File("build/app")
        String[] names=app.list()

        //getting one string with names of all the bundles
        for(int i=0;i<names.length;i++)
            if(names[i].compareTo("configuration")!=0)
                bundles = bundles.concat(names[i]+"@start,")

        //writting the properties in config.iniproperty("eclipse.ignoreApp","true")
        property("osgi.noShutdown",this.noShutdown)
        property("eclipse.ignoreApp",this.ignoreApp)
        property("osgi.bundles",bundles)
        super.writeProperties()
    }
}

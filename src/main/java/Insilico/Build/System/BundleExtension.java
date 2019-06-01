package Insilico.Build.System;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class BundleExtension {
    List<File> toBeCopied = new ArrayList<>();
    List <download> toBeDownloaded= new ArrayList<>();

    class download{
        String group;
        String name;
        String version;
        download(String group,String name,String version){
            this.group=group;
            this.name=name;
            this.version=version;
        }
    }

    void addBundle(String group,String name, String version)
    {
        this.toBeDownloaded.add(new download(group,name,version));
    }

    void addBundle(String file)
    {
        this.toBeCopied.add(new File(file));
    }

}

package org.insilico.build

class ApplicationExtension {
    List<String> toBeCopied = new ArrayList<>();
    List<Download> toBeDownloaded = new ArrayList<>();

    class Download {
        String group;
        String name;
        String version;

        Download(String group, String name, String version) {
            this.group = group;
            this.name = name;
            this.version = version;

        }
    }

    void addBundle(String group, String name, String version) {
        this.toBeDownloaded.add(new Download(group, name, version));
    }

    void addBundle(String file) {
        this.toBeCopied.add(file);
    }
}

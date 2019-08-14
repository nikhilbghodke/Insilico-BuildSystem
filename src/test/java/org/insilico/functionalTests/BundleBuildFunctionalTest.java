package org.insilico.functionalTests;

import org.junit.Test;

import org.gradle.testkit.runner.BuildResult;
import org.gradle.testkit.runner.GradleRunner;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.osgi.framework.Constants;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import static org.gradle.testkit.runner.TaskOutcome.*;

import static org.junit.Assert.assertEquals;

public class BundleBuildFunctionalTest {

    @Rule public final TemporaryFolder testProjectDir = new TemporaryFolder();
    private File settingsFile;
    private File buildFile;
    BufferedReader build;

    @Before
    public void setup() throws IOException {
        settingsFile = testProjectDir.newFile("settings.gradle");
        buildFile = testProjectDir.newFile("build.gradle");
        this.build=new BufferedReader( new FileReader("src/test/resources/applicationBuild"));
    }

    @Test
    public void testBuildTask() throws IOException {
        writeFile(settingsFile, "rootProject.name = 'Inilico-app'");

        //properties listed here are used to check whether these values were properly written or not
        String vendor="Roman";
        String bundleName="SimpleBundle";
        String symbolicName="org.insilico.examples.simpleBundle";
        String version="1.0";
        String license="MIT License";
        String developer="Nikhil Ghodke";
        String contactAddress="221b Baker St, London";
        System.out.println();
        BufferedReader ab = new BufferedReader(new FileReader("src/test/resources/BundleFunctionalTestbuild.gradle"));
        String buildFileContent="";

        String strCurrentLine="";
        while ((strCurrentLine = ab.readLine()) != null) {
            buildFileContent=buildFileContent+"\n"+strCurrentLine;
        }
        writeFile(buildFile,buildFileContent);

        BuildResult result = GradleRunner.create()
                .withProjectDir(testProjectDir.getRoot())
                .withPluginClasspath()
                .withArguments("jar")
                .build();


        File testProjectfile = testProjectDir.getRoot();
        String pathOfJar=testProjectfile.getAbsolutePath();
        pathOfJar+="/build/libs/Inilico-app.jar";
        File jarFile= new File(pathOfJar);

        String destDir=testProjectDir.getRoot().getAbsolutePath()+"/build/libs";



        java.util.jar.JarFile jar = new java.util.jar.JarFile(jarFile);
        java.util.Enumeration enumEntries = jar.entries();
        while (enumEntries.hasMoreElements()) {
            java.util.jar.JarEntry file = (java.util.jar.JarEntry) enumEntries.nextElement();
            java.io.File f = new java.io.File(destDir + java.io.File.separator + file.getName());
            if (file.isDirectory()) { // if its a directory, create it
                f.mkdir();
                continue;
            }
            java.io.InputStream is = jar.getInputStream(file); // get the input stream
            java.io.FileOutputStream fos = new java.io.FileOutputStream(f);
            while (is.available() > 0) {  // write contents of 'is' to 'fos'
                fos.write(is.read());
            }
            fos.close();
            is.close();
        }
        jar.close();



        FileReader reader=new FileReader(destDir +"/META-INF/MANIFEST.MF");

        Properties p=new Properties();
        p.load(reader);


        assertEquals(vendor, p.getProperty(Constants.BUNDLE_VENDOR));
        assertEquals(version, p.getProperty(Constants.BUNDLE_VERSION));
        assertEquals(bundleName, p.getProperty(Constants.BUNDLE_NAME));
        assertEquals(symbolicName, p.getProperty(Constants.BUNDLE_SYMBOLICNAME));
        assertEquals(developer, p.getProperty("Bundle-Developers"));
        assertEquals(contactAddress, p.getProperty(Constants.BUNDLE_CONTACTADDRESS));
        assertEquals(license, p.getProperty(Constants.BUNDLE_LICENSE));
        assertEquals(SUCCESS, result.task(":jar").getOutcome());

    }

    private void writeFile(File destination, String content) throws IOException {
        BufferedWriter output = null;
        try {
            output = new BufferedWriter(new FileWriter(destination));
            output.write(content);
        } finally {
            if (output != null) {
                output.close();
            }
        }
    }
}
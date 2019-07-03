package org.insilico.functionalTests;

import org.junit.Test;

import org.gradle.testkit.runner.BuildResult;
import org.gradle.testkit.runner.GradleRunner;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import static org.gradle.testkit.runner.TaskOutcome.*;

import static org.junit.Assert.assertEquals;

public class BundleBuildFunctionalTest {

    @Rule public final TemporaryFolder testProjectDir = new TemporaryFolder();
    private File settingsFile;
    private File buildFile;

    @Before
    public void setup() throws IOException {
        settingsFile = testProjectDir.newFile("settings.gradle");
        buildFile = testProjectDir.newFile("build.gradle");
    }

    @Test
    public void testBuildTask() throws IOException {
        writeFile(settingsFile, "rootProject.name = 'Inilico-app'");
        String buildFileContent = "plugins {\n" +
                "  id \"com.github.niikhilghodke.bundleBuilder\" version \"1.0.6\"\n" +
                " id 'java'"+
                "}\n"+
                "apply plugin: 'biz.aQute.bnd.builder'\n";
        buildFileContent+="jar{\n" +
                "           from sourceSets.main.output\n" +
                "            bundle{\n" +
                "                bundleName = \"SimpleBundle\"\n" +
                "                symbolicName = \"org.insilico.examples.simpleExample\"\n" +
                "                version = 1.0\n" +
                "                vendor = \"Roman Schulte\"\n" +
                "                license = 'MIT License'\n" +
                "                developer = 'Nikhil Ghodke'\n" +
                "                contactAddress = \"221b Baker St, London\"\n" +
                "             }\n" +
                "         }";
        writeFile(buildFile, buildFileContent);

        BuildResult result = GradleRunner.create()
                .withProjectDir(testProjectDir.getRoot())
                .withPluginClasspath()
                .withArguments("jar")
                .build();

//        File testProjectfile = testProjectDir.getRoot();
//        File buildFile = testProjectfile.listFiles(new FilenameFilter() {
//            public boolean accept(File dir, String name) {
//                return name.equals("build")&& dir.isDirectory();
//            }
//        })[0];
//
//        File libsFile=buildFile.listFiles(new FilenameFilter() {
//            public boolean accept(File dir, String name) {
//                return name.equals("libs")&& dir.isDirectory();
//            }
//        })[0];



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
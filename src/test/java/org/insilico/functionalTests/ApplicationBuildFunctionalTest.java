package org.insilico.functionalTests;

import org.junit.Test;

import org.gradle.testkit.runner.BuildResult;
import org.gradle.testkit.runner.GradleRunner;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.nio.BufferUnderflowException;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import static org.gradle.testkit.runner.TaskOutcome.*;

import static org.junit.Assert.assertEquals;

public class ApplicationBuildFunctionalTest {

    @Rule
    public final TemporaryFolder testProjectDir = new TemporaryFolder();
    private File settingsFile;
    private File buildFile;


    @Before
    public void setup() throws IOException {
        settingsFile = testProjectDir.newFile("settings.gradle");
        buildFile = testProjectDir.newFile("build.gradle");
    }

    @Test
    public void testBuildTask() throws Exception {
        writeFile(settingsFile, "rootProject.name = 'Inilico-app'");
        BufferedReader a = new BufferedReader(new FileReader("README.md"));
        System.out.println(new File("README.md").getAbsoluteFile());
        BufferedReader ab = new BufferedReader(new FileReader("src/test/resources/ApplicationFunctionalTestbuild.gradle"));
        String buildFileContent="";

        String strCurrentLine="";
        while ((strCurrentLine = ab.readLine()) != null) {
            buildFileContent=buildFileContent+"\n"+strCurrentLine;
        }
        //throw new Exception(ans);
        writeFile(buildFile,buildFileContent);
        BuildResult result = GradleRunner.create()
                .withProjectDir(testProjectDir.getRoot())
                .withPluginClasspath()
                .withArguments("buildApplication")
                .build();

        assertEquals(SUCCESS, result.task(":buildApplication").getOutcome());
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
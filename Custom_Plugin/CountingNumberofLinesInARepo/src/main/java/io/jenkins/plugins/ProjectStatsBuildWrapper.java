package io.jenkins.plugins;

import hudson.Extension;
import hudson.Launcher;
import hudson.model.AbstractBuild;
import hudson.model.AbstractProject;
import hudson.model.BuildListener;
import hudson.tasks.BuildWrapper;
import hudson.tasks.BuildWrapperDescriptor;
import java.io.*;
import java.nio.charset.StandardCharsets;
import javax.annotation.Nonnull;
import org.kohsuke.stapler.DataBoundConstructor;

public class ProjectStatsBuildWrapper extends BuildWrapper {
    // This class extends BuildWrapper, which is part of the Jenkins extension
    // points for build process customization.

    public static final String REPORT_TEMPLATE_PATH = "/stats.html";
    public static final String PROJECT_NAME_VAR = "$PROJECT_NAME$";

    // These are constants defining paths and placeholders used in the code.

    @DataBoundConstructor
    public ProjectStatsBuildWrapper() {}
    // This is the constructor for the class. It is annotated with @DataBoundConstructor,
    // indicating that it should be used for data binding during the configuration of the Jenkins job.

    @Override
    public Environment setUp(AbstractBuild build, final Launcher launcher, BuildListener listener) {
        // This method is part of the BuildWrapper extension point. It sets up the environment for the build.
        // Inside the setUp method, an anonymous subclass of Environment is created with a customized tearDown method.
        // This tearDown method is responsible for generating project statistics, creating an HTML report,
        // and saving it to the artifacts directory.
        // This method is overridden from the BuildWrapper class and is called at the beginning of the build process.
        // It returns an instance of an anonymous subclass of the Environment class.
        return new Environment() {
            @Override
            public boolean tearDown(AbstractBuild build, BuildListener listener)
                    throws IOException, InterruptedException {
                // This method is called at the end of the build process.
                // It is responsible for generating project statistics, creating an HTML report,
                // and saving it to the artifacts directory.
                ProjectStats stats = ArhamUtilClass.buildStats(String.valueOf(build.getWorkspace()));
                String report = ArhamUtilClass.generateReport(build.getProject().getDisplayName(), stats);
                File artifactsDir = build.getArtifactsDir();
                if (!artifactsDir.isDirectory()) {
                    boolean success = artifactsDir.mkdirs();
                    if (!success) {
                        listener.getLogger()
                                .println("Can't create artifacts directory at " + artifactsDir.getAbsolutePath());
                    }
                }
                String path = artifactsDir.getCanonicalPath() + REPORT_TEMPLATE_PATH;
                try (BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(new FileOutputStream(path), StandardCharsets.UTF_8))) {
                    writer.write(report);
                }
                return super.tearDown(build, listener);
            }
        };
    }

    @Extension
    public static final class DescriptorImpl extends BuildWrapperDescriptor {
        // This is an extension point descriptor for the build wrapper.
        // It provides information about the build wrapper, such as its display name and
        // whether it is applicable to a specific project.
        // isApplicable Method: Determines if the build wrapper is applicable to a given project.
        // getDisplayName Method: Returns the display name of the build wrapper.

        @Override
        public boolean isApplicable(AbstractProject<?, ?> item) {
            return true;
        }

        @Nonnull
        @Override
        public String getDisplayName() {
            return "Construct project stats during build";
        }
    }
}

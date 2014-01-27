package com.oculusvr.teamcity.unityrunner;

import jetbrains.buildServer.serverSide.InvalidProperty;
import jetbrains.buildServer.serverSide.PropertiesProcessor;
import jetbrains.buildServer.serverSide.RunType;
import jetbrains.buildServer.serverSide.RunTypeRegistry;
import jetbrains.buildServer.web.openapi.PluginDescriptor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Alex Howland
 * Date: 1/17/14
 * Time: 3:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class UnityRunnerRunType extends RunType {
    PluginDescriptor pluginDescriptor;

    public UnityRunnerRunType(final RunTypeRegistry runTypeRegistry, final PluginDescriptor pluginDescriptor) {
        this.pluginDescriptor = pluginDescriptor;
        runTypeRegistry.registerRunType(this);
    }

    @NotNull
    @Override
    public String getType() {
        return UnityRunnerConstants.RUN_TYPE;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @NotNull
    @Override
    public String getDisplayName() {
        return UnityRunnerConstants.RUNNER_DISPLAY_NAME;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @NotNull
    @Override
    public String getDescription() {
        return UnityRunnerConstants.RUNNER_DESCRIPTION;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Nullable
    @Override
    public PropertiesProcessor getRunnerPropertiesProcessor() {
        return new PropertiesProcessor() {
            public Collection<InvalidProperty> process(Map<String, String> properties) {
                Collection<InvalidProperty> errors = new ArrayList<InvalidProperty>();

                if (properties.get(UnityRunnerConstants.FORM_UNITY_ACTION) != null) {
                    if (properties.get(UnityRunnerConstants.FORM_UNITY_ACTION).equals(UnityRunnerConstants.EXECUTE_METHOD) &&
                            !properties.containsKey("argument.execute_method")) {
                        errors.add(new InvalidProperty("argument.execute_method",
                                "Please specify the method to execute."));
                    }
                }
                if ( properties.get(UnityRunnerConstants.FORM_UNITY_ACTION) != null &&
                    properties.get(UnityRunnerConstants.FORM_UNITY_ACTION).equals(UnityRunnerConstants.EXPORT_PACKAGE)) {
                    if (!properties.containsKey(UnityRunnerConstants.FORM_EXPORT_PACKAGES)) {
                        errors.add(new InvalidProperty(UnityRunnerConstants.FORM_EXPORT_PACKAGES,
                                "Please specify a path to export as a .unityPackage."));
                    }

                    if (!properties.containsKey(UnityRunnerConstants.FORM_EXPORT_FILENAME)) {
                        errors.add(new InvalidProperty(UnityRunnerConstants.FORM_EXPORT_FILENAME,
                                "Please specify a filename for the .unityPackage."));
                    }
                }

                return errors;
            }
        };
    }

    @Nullable
    @Override
    public String getEditRunnerParamsJspFilePath() {
        return pluginDescriptor.getPluginResourcesPath("editRunParameters.jsp");
    }

    @Nullable
    @Override
    public String getViewRunnerParamsJspFilePath() {
        return pluginDescriptor.getPluginResourcesPath("viewRunParameters.jsp");
    }

    @Nullable
    @Override
    public Map<String, String> getDefaultRunnerProperties() {
        Map<String, String> defaults = new HashMap<String, String>();

        return defaults;
    }
}

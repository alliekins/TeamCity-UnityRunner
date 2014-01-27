package com.oculusvr.teamcity.unityrunner;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Alex Howland
 * Date: 1/17/14
 * Time: 3:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class UnityRunnerConfig {
    enum Platform { Windows, Mac, Android }

    public boolean noGraphics;
    public String projectPath;
    public String unityAction;
    public String executeMethod;
    public String workingDir;

    public Platform platform;
    public String unityPath;
    public List<String> exportPackages;
    public String exportFilename;
    private String forcedGraphics;

    private String logfile;


    public UnityRunnerConfig(Map<String, String> runnerParameters) {

        workingDir = runnerParameters.get("teamcity.build.workingDir");
        noGraphics = Boolean.parseBoolean(runnerParameters.get("argument.no_graphics"));
        projectPath = runnerParameters.get(UnityRunnerConstants.FORM_PROJECT_PATH);
        unityAction = runnerParameters.get("argument.unity_action");
        forcedGraphics = runnerParameters.get("argument.force_graphics");
        logfile = runnerParameters.get("argument.logfile");

        if (unityAction == null || unityAction.equals("")) {
            unityAction = "";
            executeMethod = null;
        } else if (unityAction.equals("executeMethod")) {
            executeMethod = runnerParameters.get("argument.execute_method");
        } else if (unityAction.equals(UnityRunnerConstants.EXPORT_PACKAGE)) {
            String rawExportPackages = runnerParameters.get(UnityRunnerConstants.FORM_EXPORT_PACKAGES);
            exportPackages = new ArrayList<String>();
            exportPackages.addAll(Arrays.asList(rawExportPackages.split(" ")));

            exportFilename = runnerParameters.get(UnityRunnerConstants.FORM_EXPORT_FILENAME);
        }
        else {
            executeMethod = null;
        }


        //platform = Platform.valueOf(runnerParameters.get("platform"));
        unityPath = runnerParameters.get(UnityRunnerConstants.UNITY_PATH);
        //exportPackages = runnerParameters.get("exportPackages");

    }

    public boolean isNoGraphics() {
        return noGraphics;
    }

    public String getProjectPath() {
        return projectPath;
    }

    public String getFullProjectPath() {
        return workingDir + "/" + projectPath;
    }

    public String getUnityAction() {
        return unityAction;
    }

    public String getExecuteMethod() {
        return executeMethod;
    }

    public String getWorkingDir() {
        return workingDir;
    }

    public Platform getPlatform() {
        return platform;
    }

    public String getUnityPath() {
        return unityPath;
    }

    public List<String> getExportPackages() {
        return exportPackages;
    }

    public String getExportFilename() {
        return exportFilename;
    }

    public String getForcedGraphics() {
        return (forcedGraphics == null)? "none" : forcedGraphics;

    }

    public String getLogfile() {
        return logfile;
    }
}

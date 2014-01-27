package com.oculusvr.teamcity.unityrunner;

import jetbrains.buildServer.RunBuildException;
import jetbrains.buildServer.agent.BuildProgressLogger;
import jetbrains.buildServer.agent.runner.CommandLineBuildService;
import jetbrains.buildServer.agent.runner.ProgramCommandLine;
import jetbrains.buildServer.agent.runner.SimpleProgramCommandLine;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Alex Howland
 * Date: 1/22/14
 * Time: 12:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class UnityBuildService extends CommandLineBuildService {

    private UnityRunnerConfig config;

    private BuildProgressLogger log;

    @NotNull
    @Override
    public ProgramCommandLine makeProgramCommandLine() throws RunBuildException {

        log = getRunnerContext().getBuild().getBuildLogger();

        /*log.message("---------------CONFIG PARAMS--------------------");
        for (String str : getRunnerContext().getConfigParameters().keySet()) {
            log.message(str + " : " + getRunnerContext().getConfigParameters().get(str));
        }

        log.message("---------------ALL BUILD PARAMS--------------------");
        for (String str : getRunnerContext().getBuildParameters().getAllParameters().keySet()) {
            log.message(str + " : " + getRunnerContext().getBuildParameters().getAllParameters().get(str));
        }*/

        log.message("---------------RUNNER PARAMS--------------------");
        for (String str : getRunnerContext().getRunnerParameters().keySet()) {
            log.message(str + " : " + getRunnerContext().getRunnerParameters().get(str));
        }
        log.message("---------------END RUNNER PARAMS--------------------");

        this.config = new UnityRunnerConfig(getRunnerContext().getRunnerParameters());
        config.unityPath = "unity";
        log.message("--------------DUMPING CONFIG PARAMS--------------------");

        log.message("config.unityPath : " + config.unityPath);
        log.message("config.projectPath : " + config.projectPath);
        log.message("config.nographics : " + config.isNoGraphics());
        log.message("config.unityAction : " + config.getUnityAction());
        log.message("config.executeMethod : " + config.executeMethod);


        return new SimpleProgramCommandLine(getRunnerContext(), config.unityPath, getArgs());
    }

    @NotNull
    public List<String> getArgs() {
        List<String> args = new ArrayList<String>();
        args.add("-batchmode");
        if (config.isNoGraphics()) {
            args.add("-noGraphics");
        }
        if (!config.getForcedGraphics().equals("none")) {
            args.add("-force-" + config.getForcedGraphics());
        }
        if (config.projectPath != null && !config.projectPath.equals("")) {
            args.add("-projectPath");
            args.add(config.getFullProjectPath());
        }

        args.add("-logFile");
        args.add("C:\\log_" + config.unityAction + ".txt");

        if (config.unityAction.equals(UnityRunnerConstants.EXECUTE_METHOD)) {
            //log.message("EXECUTE METHOD found!");
            args.add("-" + UnityRunnerConstants.EXECUTE_METHOD);
            args.add(config.executeMethod);
        } else if (config.unityAction.equals(UnityRunnerConstants.EXPORT_PACKAGE)) {
            //log.message("EXPORT PACKAGE found!");
            args.add("-" + UnityRunnerConstants.EXPORT_PACKAGE);
            args.addAll(config.getExportPackages());
            args.add(config.exportFilename);
        } else if (config.unityAction.equals(UnityRunnerConstants.STANDALONE_WINDOWS)) {
            //log.message("BUILD WINDOWS PLAYER found!");
            args.add("-" + UnityRunnerConstants.STANDALONE_WINDOWS);
        } else if (config.unityAction.equals(UnityRunnerConstants.STANDALONE_OSX)) {
            //log.message("BUILD WINDOWS PLAYER found!");
            args.add("-" + UnityRunnerConstants.STANDALONE_OSX);
        } else if (config.unityAction.equals(UnityRunnerConstants.STANDALONE_WEB)) {
            //log.message("BUILD WINDOWS PLAYER found!");
            args.add("-" + UnityRunnerConstants.STANDALONE_WEB);
        } else {
            log.message("Sorry, I don't recognize \"" + config.unityAction + "\"");
        }

        args.add("-quit");
        return args;
    }
}
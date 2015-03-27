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

        log.message("---------------RUNNER PARAMS--------------------");
        for (String str : getRunnerContext().getRunnerParameters().keySet()) {
            log.message(str + " : " + getRunnerContext().getRunnerParameters().get(str));
        }
        log.message("---------------END RUNNER PARAMS--------------------");

        this.config = new UnityRunnerConfig(getRunnerContext().getRunnerParameters());

        if (config.getUnityPath() == null || config.getUnityPath().isEmpty()) {
            config.unityPath = "unity";
        }

        log.message("--------------CONFIG PARAMS--------------------");

        log.message("config.unityPath : " + config.unityPath);
        log.message("config.projectPath : " + config.projectPath);
        log.message("config.nographics : " + config.isNoGraphics());
        log.message("config.unityAction : " + config.getUnityAction());
        log.message("config.executeMethod : " + config.executeMethod);
        if (config.getExtraParams() != null) {
            log.message("config.extraparams : ");
            for (String s : config.getExtraParams()) {
                log.message(s);
            }
        }

        log.message("--------------END CONFIG PARAMS--------------------");


        return new SimpleProgramCommandLine(getRunnerContext(), config.unityPath, getArgs());
    }

    @NotNull
    public List<String> getArgs() {
        List<String> args = new ArrayList<String>();
        args.add("-batchmode");
        if (config.isNoGraphics()) {
            args.add("-nographics");
        }
        if (!config.getForcedGraphics().equals("none")) {
            args.add("-force-" + config.getForcedGraphics());
        }
        args.add("-projectPath");
        args.add(config.getFullProjectPath());

       if ( config.getLogfile() != null && !(config.getLogfile().equals("")) ) {
            args.add("-logFile");
            args.add(config.getLogfile());
       }

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
        } else if (config.unityAction.equals("Nothing")) {
            //do nothing
        } else {
            log.message("Sorry, I don't recognize \"" + config.unityAction + "\"");
        }

        args.addAll(config.getExtraParams());
        args.add("-quit");
        return args;
    }
}

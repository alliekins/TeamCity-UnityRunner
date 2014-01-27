package com.oculusvr.teamcity.unityrunner;

import jetbrains.buildServer.agent.AgentBuildRunnerInfo;
import jetbrains.buildServer.agent.BuildAgentConfiguration;
import jetbrains.buildServer.agent.runner.CommandLineBuildService;
import jetbrains.buildServer.agent.runner.CommandLineBuildServiceFactory;
import org.jetbrains.annotations.NotNull;

/**
 * Created with IntelliJ IDEA.
 * User: Alex Howland
 * Date: 1/22/14
 * Time: 12:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class UnityBuildServiceFactory implements CommandLineBuildServiceFactory, AgentBuildRunnerInfo {
    @NotNull
    public CommandLineBuildService createService() {
        return new UnityBuildService();
    }

    @NotNull
    public AgentBuildRunnerInfo getBuildRunnerInfo() {
        return this;
    }

    @NotNull
    public String getType() {
        return UnityRunnerConstants.RUN_TYPE;
    }

    public boolean canRun(@NotNull BuildAgentConfiguration buildAgentConfiguration) {
        return true;
    }
}

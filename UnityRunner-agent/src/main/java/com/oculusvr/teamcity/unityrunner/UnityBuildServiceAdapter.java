package com.oculusvr.teamcity.unityrunner;

import jetbrains.buildServer.RunBuildException;
import jetbrains.buildServer.agent.runner.BuildServiceAdapter;
import jetbrains.buildServer.agent.runner.ProgramCommandLine;
import org.jetbrains.annotations.NotNull;

/**
 * Created with IntelliJ IDEA.
 * User: Alex Howland
 * Date: 1/22/14
 * Time: 12:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class UnityBuildServiceAdapter extends BuildServiceAdapter {
    @NotNull
    @Override
    public ProgramCommandLine makeProgramCommandLine() throws RunBuildException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}

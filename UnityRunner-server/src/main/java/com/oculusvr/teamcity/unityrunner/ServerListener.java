package com.oculusvr.teamcity.unityrunner;

import jetbrains.buildServer.serverSide.BuildServerAdapter;
import jetbrains.buildServer.serverSide.BuildServerListener;
import jetbrains.buildServer.serverSide.SBuildServer;
import jetbrains.buildServer.util.EventDispatcher;
import org.jetbrains.annotations.NotNull;

public class ServerListener extends BuildServerAdapter {
    private SBuildServer myServer;

    public ServerListener(@NotNull final EventDispatcher<BuildServerListener> dispatcher, SBuildServer server) {
        dispatcher.addListener(this);
        myServer = server;
    }
}
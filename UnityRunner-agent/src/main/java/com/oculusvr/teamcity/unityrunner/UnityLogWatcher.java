package com.oculusvr.teamcity.unityrunner;

import jetbrains.buildServer.agent.BuildProgressLogger;

import java.io.IOException;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;
import static java.nio.file.StandardWatchEventKinds.*;

/**
 * Created with IntelliJ IDEA.
 * User: Alex Howland
 * Date: 4/18/14
 * Time: 3:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class UnityLogWatcher {

    private BuildProgressLogger log;
    private WatchService watcher;
    private Map<WatchKey,Path> keys;

    @SuppressWarnings("unchecked")
    static <T> WatchEvent<T> cast(WatchEvent<?> event) {
        return (WatchEvent<T>)event;
    }

    public UnityLogWatcher(Path logfile, BuildProgressLogger log) throws IOException {
        this.log = log;
        this.watcher = FileSystems.getDefault().newWatchService();
        this.keys = new HashMap<WatchKey, Path>();
        WatchKey key = logfile.register(watcher, ENTRY_MODIFY);
        keys.put(key, logfile);
    }

    public void processLog() {
        for(;;) {
            WatchKey key;
            try {
                key = watcher.take();
            } catch (InterruptedException e) {
                log.message("interrupted.");
                return;
            }

            Path logfile = keys.get(key);
            if (logfile == null) {
                log.message("null key");
                continue;
            }

            for (WatchEvent event : key.pollEvents()) {
                WatchEvent.Kind kind = event.kind();

                if (kind == OVERFLOW) {
                    //error, skip this
                    continue;
                }

                WatchEvent<Path> ev = cast(event);
                Path name = ev.context();
                log.message("name created");
                log.message("name is " + name.toString());
                Path child = logfile.resolve(name);
                log.message("child created");
                log.message("child is " + child);
                log.message(event.kind().name() + ": " + child);

            }
        }

    }

}

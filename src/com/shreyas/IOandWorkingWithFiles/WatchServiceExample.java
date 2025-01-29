package com.shreyas.IOandWorkingWithFiles;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

/**
 * Represents the states and lifecycle of a WatchKey in a WatchService.
 * <p>
 * A WatchKey is initially in the {@code READY} state. When an event occurs
 * (e.g., file creation, update, or deletion), it transitions to the
 * {@code SIGNALLED} state, where it can be polled or processed.
 * Once signalled, it remains in this state until the {@code reset} method is invoked.
 * </p>
 *
 * <pre>
 *  +-------------+
 *  | Initialized |
 *  +-------------+
 *         |
 *         v
 *  +------+     Event Occurs    +------------+
 *  | READY |------------------->| SIGNALLED  |
 *  +------+                    +------------+
 *       |                             |
 *       | Reset                       |
 *       v                             v
 *  +--------+                   +------------+
 *  | WatchService Queue |       | WatchKey Events |
 *  +--------------------+       | - WatchEvent   |
 *                                | - WatchEvent   |
 *                                | - WatchEvent   |
 *                                +------------+
 * </pre>
 *
 * @see java.nio.file.WatchKey
 * @see java.nio.file.WatchService
 */

public class WatchServiceExample {
    public static void main(String[] args) throws IOException {
        // Service that let us monitor file system changes
        WatchService watchService = FileSystems.getDefault().newWatchService();
        Path directory = Path.of("."); // current directory

        // WatchKey is a handle to the service basically.
        // It is a token representing the registration of a watchable object with a WatchService.
        // It represents watchable Object registration with watch service
        WatchKey watchKey = directory.register(
                watchService,
                StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_DELETE,
                StandardWatchEventKinds.ENTRY_MODIFY
        );

        boolean keepGoing = true;
        while (keepGoing) {
            try {
                watchKey = watchService.take();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            List<WatchEvent<?>> events = watchKey.pollEvents();
            for (WatchEvent<?> event : events) {
                WatchEvent.Kind<?> kind = event.kind();
                Path path = (Path) event.context();
                System.out.println("Event Type : " + kind + " : " + path);

                if (path.getFileName().toString().equals("testing.txt") && kind == StandardWatchEventKinds.ENTRY_DELETE) {
                    System.out.println("Shutting down watch service");
                    watchService.close();
                    keepGoing = false;
                    break;
                }
            }
            watchKey.reset();
        }

    }
}

package de.novacyb.temporal.viewer.logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * Process Source Stream
 * Created on 06.01.19.
 */
public class ProcessSourceStream extends SourceStream {

    /**
     *
     * @param workingDirectory the working dictionary to work in
     * @param command the executing command like "myCommand", "myArg1", "myArg2"
     * @throws IOException
     */
    public void init(final File workingDirectory, final String... command) throws IOException {
        final var processBuilder = new ProcessBuilder("myCommand", "myArg1", "myArg2");

        // TODO make env variables accessible for the user
        // final var environment = processBuilder.environment();
        // environment.put("VAR1", "myValue");

        processBuilder.directory(workingDirectory);
        final var process = processBuilder.start();

        final var stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
        final var stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));

        setupHelperThread(new Thread(() -> observeStream(stdInput)));
        setupHelperThread(new Thread(() -> observeStream(stdError)));
    }

    private void setupHelperThread(final Thread thread) {
        thread.setDaemon(true);
        thread.run();
    }

    private void observeStream(final BufferedReader stream) {
        try {
            String s;
            while ((s = stream.readLine()) != null) {
                processInput(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

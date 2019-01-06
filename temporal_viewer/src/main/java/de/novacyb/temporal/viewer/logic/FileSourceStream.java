package de.novacyb.temporal.viewer.logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * File Source Stream
 *
 * @author Jan Schwien
 * Created on 06.01.19.
 */
public class FileSourceStream extends SourceStream {


    /**
     *
     * @param sourceFile the file to load and process
     */
    public void init(final File sourceFile) {
        try (final var br = new BufferedReader(new FileReader(sourceFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                processInput(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

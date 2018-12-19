package de.novacyb.temporal.viewer;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Temporal Main Application
 * Created on 18.12.18.
 */
public class MainApp extends Application {

    public static int appVersionMajor;
    public static int appVersionMinor;
    public static int appVersionRevision;
    public static int appVersionPatch;

    @Override
    public void start(final Stage primaryStage) throws Exception {
        // step 1
        loadAppProperties();

        // step 2
        setupUI(primaryStage);
    }

    /**
     * setup and show base ui
     * @param stage the primary stage
     * @throws Exception
     */
    private void setupUI(final Stage stage) throws Exception {
        stage.setTitle("Temporal " + getVersionString() +  " - Timing Debugger");

        // prepare parent
        final Parent root = FXMLLoader.load(getClass().getResource("/ui/window/main_window.fxml"));

        // setup and show the scene and stage
        stage.setScene(new Scene(root, 950, 680));
        stage.show();
    }

    /**
     * load application properties
     */
    private void loadAppProperties() {
        try(final var input = new InputStreamReader(getClass().getModule()
                .getResourceAsStream("/app.properties"))) {

            final var properties = new Properties();
            properties.load(input);

            // read required values
            appVersionMajor = Integer.parseInt(properties.getProperty("version_major"));
            appVersionMinor = Integer.parseInt(properties.getProperty("version_minor"));
            appVersionRevision = Integer.parseInt(properties.getProperty("version_revision"));
            appVersionPatch = Integer.parseInt(properties.getProperty("version_patch"));

        } catch (Exception e) {
            System.err.println("Error while loading app properties!");
            e.printStackTrace();
        }
    }

    public static String getVersionString() {
        return appVersionMajor + "." + appVersionMinor + "." + appVersionRevision + "." + appVersionPatch;
    }
}

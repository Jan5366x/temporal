package de.novacyb.temporal.viewer.ui.window;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * About Window Fx Controller
 *
 * @author Jan Schwien
 * Created on 19.09.19.
 */
public class AboutWindow {
    private final static int PREFERRED_WIDTH = 450;
    private final static int PREFERRED_HEIGHT = 210;

    public void showDialog(final Stage owner) {
        try {
            final var window = new Stage();

            final var fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/ui/window/about_window.fxml"));
            fxmlLoader.setController(this);
            final Parent root = fxmlLoader.load();

            // setup window
            if (owner != null)
                window.initOwner(owner);
            window.setResizable(false);
            window.setTitle("About");

            // set icon
            if (owner instanceof Stage && ((Stage) owner).getIcons().size() > 0)
                window.getIcons().add(((Stage) owner).getIcons().get(0));

            // setup and show the scene and stage
            window.setScene(new Scene(root, PREFERRED_WIDTH, PREFERRED_HEIGHT));
            window.setMinWidth(PREFERRED_WIDTH);
            window.setMinHeight(PREFERRED_HEIGHT);

            window.initModality(Modality.APPLICATION_MODAL);
            window.showAndWait();
        } catch (IOException e) {
            System.err.println("Error on show About Window!");
            e.printStackTrace();
        }
    }
}

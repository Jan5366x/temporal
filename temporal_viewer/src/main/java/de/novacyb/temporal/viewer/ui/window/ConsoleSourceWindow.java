package de.novacyb.temporal.viewer.ui.window;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Console Source Window
 * Created on 06.01.19.
 */
public class ConsoleSourceWindow implements Initializable {
    private final static int PREFERRED_WIDTH = 450;
    private final static int PREFERRED_HEIGHT = 210;

    public Button cancelButton;
    public Button okButton;
    public TextArea commandTextArea;
    public ChoiceBox commandModeChoiceBox;

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {

    }

    /**
     * show plugin dialog window
     *
     * @param owner the owner window
     */
    public void showDialog(final Window owner) {
        try {
            final var window = new Stage();

            final var fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/ui/window/console_source_window.fxml"));
            fxmlLoader.setController(this);
            final Parent root = fxmlLoader.load();

            // setup window
            if (owner != null)
                window.initOwner(owner);
            window.setResizable(false);
            window.setTitle("Add Source");

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
            System.err.println("Error on show Plugin Window!");
            e.printStackTrace();
        }
    }

}

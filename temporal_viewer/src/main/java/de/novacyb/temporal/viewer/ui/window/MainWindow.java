package de.novacyb.temporal.viewer.ui.window;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Main Window Controller
 * Created on 19.12.2018.
 */
public class MainWindow implements Initializable {

    public AnchorPane contentPane;
    public TextField filterTextField;

    /**
     * Main Window Constructor
     */
    public MainWindow() {
        // do nothing
    }

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        // do nothing
    }

    public void onClose(final ActionEvent actionEvent) {
        Platform.exit();
    }
}

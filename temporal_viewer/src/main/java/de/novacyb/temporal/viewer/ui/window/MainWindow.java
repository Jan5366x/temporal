package de.novacyb.temporal.viewer.ui.window;

import de.novacyb.temporal.viewer.ui.control.TimelineView;
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

    private TimelineView timeLineView = new TimelineView();

    /**
     * Main Window Constructor
     */
    public MainWindow() {
        // do nothing
    }

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {

        // setup timeline view
        contentPane.getChildren().add(timeLineView);
        AnchorPane.setTopAnchor(timeLineView,0D);
        AnchorPane.setBottomAnchor(timeLineView,0D);
        AnchorPane.setLeftAnchor(timeLineView,0D);
        AnchorPane.setRightAnchor(timeLineView,0D);
    }

    public void onClose(final ActionEvent actionEvent) {
        Platform.exit();
    }
}

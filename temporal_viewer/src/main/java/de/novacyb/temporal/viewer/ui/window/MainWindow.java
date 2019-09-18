package de.novacyb.temporal.viewer.ui.window;

import de.novacyb.temporal.viewer.ui.control.TimelineView;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Main Window FX Controller
 * Created on 19.12.2018.
 */
public class MainWindow implements Initializable {

    public AnchorPane contentPane;
    public TextField filterTextField;
    public Slider timeScaleSlider;
    public SeparatorMenuItem sourceContextDeleteSeparator;

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

        // bind timescale to ui
        timeScaleSlider.valueProperty().bindBidirectional(timeLineView.timeScaleProperty());
    }

    public void onAddLogfile(final ActionEvent actionEvent) {
        final var fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Logfile");
        fileChooser.getExtensionFilters()
                .addAll(new FileChooser.ExtensionFilter("All Files", "*.*"),
                        new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                        new FileChooser.ExtensionFilter("Log Files", "*.log"));

        //Show open file dialog
        final var file = fileChooser.showOpenDialog(getStage());

        // TODO add logfile to sources
    }

    public void onAddLiveSource(final ActionEvent actionEvent) {
        new ConsoleSourceWindow().showDialog(getStage());
    }

    public void onDeleteSource(final ActionEvent actionEvent) {

    }

    public void onClose(final ActionEvent actionEvent) {
        Platform.exit();
    }

    public Stage getStage() {
        return (Stage) contentPane.getScene().getWindow();
    }


    public void onAbout(ActionEvent actionEvent) {
        new AboutWindow().showDialog(getStage());
    }
}

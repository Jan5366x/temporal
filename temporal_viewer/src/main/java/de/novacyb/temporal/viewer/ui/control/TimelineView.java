package de.novacyb.temporal.viewer.ui.control;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import static javafx.scene.control.ScrollPane.ScrollBarPolicy.ALWAYS;

/**
 * Timeline View Control
 * TODO remove test code
 * Created on 19.12.2018.
 */
public class TimelineView extends AnchorPane {
    private final ScrollPane scrollPane = new ScrollPane();
    private final VBox scrollContent = new VBox();
    private final TimelineRuler ruler;
    private final VBox headerBox = new VBox();
    private final double headerSize = 120D;


    /**
     * units per millisecond
     */
    private final DoubleProperty timeScale = new SimpleDoubleProperty(20D);
    /**
     * higehst currently displayed time in millisecond
     */
    private final DoubleProperty maxTime = new SimpleDoubleProperty(100D);

    public TimelineView() {
        setupHeaderBox();
        setupScrollArea();


        getStylesheets().add(getClass().getResource("/ui/style/timeline.css").toString());

        ruler = new TimelineRuler(this);
        setupRuler();
        fillWithTestContent();

        // setup time scale update
        triggerTimeScaleUpdate(getTimeScale());
        timeScaleProperty().addListener((observable, oldValue, newValue)
                -> triggerTimeScaleUpdate(newValue.doubleValue()));
    }


    private void triggerTimeScaleUpdate(final double timeScale) {
        for (var node : scrollContent.getChildren()) {
            if (node instanceof ITimeScale) {
                ((ITimeScale) node).updateTimeScale(timeScale);
            }
        }
    }

    private void fillWithTestContent() {
        headerBox.getChildren().addAll(
                new TimelineGroupHeader("Timing Token A"),
                new TimelineGroupHeader("Timing Token B"),
                new TimelineGroupHeader("Timing Token C"));

        scrollContent.getChildren().addAll(new Timeline(this),new Timeline(this),new Timeline(this));
    }

    private void setupRuler() {

        // dock to the top
        AnchorPane.setTopAnchor(ruler,0D);

        // stretch to right side
        AnchorPane.setRightAnchor(ruler,0D);

        // start after header
        AnchorPane.setLeftAnchor(ruler, headerSize);

        scrollContent.getChildren().add(ruler);
    }

    private void setupHeaderBox() {

        // stretch to top, bottom and left side
        AnchorPane.setTopAnchor(headerBox, 0D);
        AnchorPane.setBottomAnchor(headerBox,0D);
        AnchorPane.setLeftAnchor(headerBox,0D);

        headerBox.setPrefWidth(headerSize);

        getChildren().add(headerBox);
    }

    private void setupScrollArea() {
        scrollPane.setVbarPolicy(ALWAYS);
        scrollPane.setHbarPolicy(ALWAYS);
        scrollPane.setContent(scrollContent);

        // stretch to top, bottom and right side
        AnchorPane.setTopAnchor(scrollPane,0D);
        AnchorPane.setBottomAnchor(scrollPane,0D);
        AnchorPane.setRightAnchor(scrollPane,0D);

        // start right to the header
        AnchorPane.setLeftAnchor(scrollPane, headerSize);


        getChildren().add(scrollPane);
    }

    public double getTimeScale() {
        return timeScale.get();
    }

    public DoubleProperty timeScaleProperty() {
        return timeScale;
    }

    public void setTimeScale(double timeScale) {
        this.timeScale.set(timeScale);
    }

    public double getMaxTime() {
        return maxTime.get();
    }

    public DoubleProperty maxTimeProperty() {
        return maxTime;
    }

    public void setMaxTime(double maxTime) {
        this.maxTime.set(maxTime);
    }
}

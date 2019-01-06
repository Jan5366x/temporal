package de.novacyb.temporal.viewer.ui.control;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import org.reactfx.util.FxTimer;

import java.time.Duration;

import static javafx.scene.control.ScrollPane.ScrollBarPolicy.ALWAYS;

/**
 * Timeline View Control
 * TODO remove test code
 * Created on 19.12.2018.
 */
public class TimelineView extends AnchorPane {
    private final ScrollPane scrollPane = new ScrollPane();
    private final AnchorPane scrollContent = new AnchorPane();
    private final Line timeCursorLine = new Line();
    private final TimelineRuler ruler;
    private double topOffset = 0D;
    private final double leftOffset = 120D;

    /**
     * units per millisecond
     */
    private final DoubleProperty timeScale = new SimpleDoubleProperty(20D);

    /**
     * highest currently displayed time in millisecond
     */
    private final DoubleProperty maxTime = new SimpleDoubleProperty(100D);

    /**
     * the time cursor marks the current time position
     */
    private final DoubleProperty timeCursor = new SimpleDoubleProperty(0D);


    public TimelineView() {
        setupScrollArea();
        setupTimeCursor();

        getStylesheets().add(getClass().getResource("/ui/style/timeline.css").toString());

        ruler = new TimelineRuler(this);
        setupRuler();
        fillWithTestContent();

        // setup time scale update
        triggerTimeScaleUpdate(getTimeScale());
        timeScaleProperty().addListener((observable, oldValue, newValue)
                -> triggerTimeScaleUpdate(newValue.doubleValue()));
    }

    private void setupTimeCursor() {
        timeCursorLine.startXProperty().bind(timeCursor);
        timeCursorLine.endXProperty().bind(timeCursor);

        // TODO scale to max size
        timeCursorLine.setEndY(200D);

        scrollContent.getChildren().add(timeCursorLine);


        // TODO placeholder
        FxTimer.runPeriodically(
                Duration.ofMillis(10),
                () -> timeCursor.set(timeCursor.getValue() + 0.1D));


    }


    private void triggerTimeScaleUpdate(final double timeScale) {
        for (var node : scrollContent.getChildren()) {
            if (node instanceof ITimeScale) {
                ((ITimeScale) node).updateTimeScale(timeScale);
            }
        }
    }

    private void fillWithTestContent() {
        addTimeLine();
        addTimeLine();
        addTimeLine();
        addTimeLine();
        addTimeLine();
        addTimeLine();
    }


    // TODO placeholder
    int timelineCount = 0;
    public void addTimeLine() {
        final var timelineHeader = new TimelineGroupHeader("Timing Token " + timelineCount);
        final var timeline = new Timeline(this);

        // set spacing
        timelineHeader.setLayoutY(topOffset + (timelineHeader.getPrefHeight() * timelineCount));

        timeline.setLayoutY(topOffset + (timeline.getPrefHeight() * timelineCount));
        timeline.setLayoutX(leftOffset);

        scrollContent.getChildren().add(timelineHeader);
        scrollContent.getChildren().add(timeline);

        timelineCount++;
    }

    private void setupRuler() {

        // dock to the top
        AnchorPane.setTopAnchor(ruler,0D);

        // stretch to right side
        AnchorPane.setRightAnchor(ruler,0D);

        // start after header
        AnchorPane.setLeftAnchor(ruler, leftOffset);

        scrollContent.getChildren().add(ruler);

        // add to top offset
        topOffset += ruler.getPrefHeight();
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
        AnchorPane.setLeftAnchor(scrollPane, 0D);


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

package de.novacyb.temporal.viewer.ui.control;

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
    private final TimelineRuler ruler = new TimelineRuler(this);
    private final VBox headerBox = new VBox();
    private final double headerSize = 120D;


    /**
     * pixel per millisecond
     */
    private int timeScale = 10;

    public TimelineView() {
        setupRuler();
        setupHeaderBox();
        setupScrollArea();
        fillWithTestContent();
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

        getChildren().add(ruler);
    }

    private void setupHeaderBox() {
        // start below ruler
        AnchorPane.setTopAnchor(headerBox, ruler.getPrefHeight());

        // stretch to bottom and left side
        AnchorPane.setBottomAnchor(headerBox,0D);
        AnchorPane.setLeftAnchor(headerBox,0D);

        headerBox.setPrefWidth(headerSize);

        getChildren().add(headerBox);
    }

    private void setupScrollArea() {
        scrollPane.setVbarPolicy(ALWAYS);
        scrollPane.setHbarPolicy(ALWAYS);
        scrollPane.setContent(scrollContent);

        // start below ruler
        AnchorPane.setTopAnchor(scrollPane,ruler.getPrefHeight());

        // stretch to bottom and right side
        AnchorPane.setBottomAnchor(scrollPane,0D);
        AnchorPane.setRightAnchor(scrollPane,0D);

        // start right to the header
        AnchorPane.setLeftAnchor(scrollPane, headerSize);


        getChildren().add(scrollPane);
    }

    public int getTimeScale() {
        return timeScale;
    }

    public void setTimeScale(int timeScale) {
        this.timeScale = timeScale;
    }
}

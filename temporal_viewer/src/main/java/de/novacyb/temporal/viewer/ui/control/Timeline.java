package de.novacyb.temporal.viewer.ui.control;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/**
 * Temporal Timeline Control
 * Created on 19.12.2018.
 */
public class Timeline extends AnchorPane implements ITimeScale {

    private final TimelineView view;

    /**
     * time line control constructor
     * @param view the time line view
     */
    public Timeline(final TimelineView view) {
        this.view = view;

        setPrefHeight(50D);

        // TODO test code for ui experiments
        final var line = new Line(0D,25D,1200D,25D);
        //widthProperty().addListener((observable, oldValue, newValue) -> line.setEndX(newValue.doubleValue()));

        line.getStrokeDashArray().addAll(15D, 10D);
        line.setStroke(new Color(0.4D,0.4D,0.4D,1D));
        getChildren().add(line);

        final var loc = Math.random() * 60D;
        final var length = 20D + Math.random() * 10D;

        final var f1 = new TimeFrame((int) loc, (int) length);
        final var ft1 = new TimeSpot((int) loc);
        final var ft2 = new TimeSpot((int) loc + (int) length);

        getChildren().addAll(f1,ft1,ft2);


    }

    @Override
    public void updateTimeScale(double timeScale) {
        for (var controlNode : getChildren()) {
            if (controlNode instanceof ITimeScale) {
                ((ITimeScale) controlNode).updateTimeScale(timeScale);
            }
        }
    }

    // TODO implement timeline
}

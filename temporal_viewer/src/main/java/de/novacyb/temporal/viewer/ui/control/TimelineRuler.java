package de.novacyb.temporal.viewer.ui.control;

import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;

/**
 * Timeline Ruler Control
 * Created on 20.12.2018.
 */
public class TimelineRuler extends AnchorPane {
    private final TimelineView view;

    public TimelineRuler(final TimelineView view) {
        this.view = view;

        // TODO test code for ui experiments
        setPrefHeight(25D);


        updateTimeScale(view,view.getTimeScale());
        view.timeScaleProperty().addListener((observable, oldValue, newValue)
                -> updateTimeScale(view, newValue.doubleValue()));

    }

    private void updateTimeScale(TimelineView view, double newValue) {
        getChildren().clear();
        final var count = view.getMaxTime();
        for (var i = 1; i < count; i++) {
            final var line = new Line(i * newValue,5D,i * newValue,20D);
            getChildren().add(line);
        }
    }
}

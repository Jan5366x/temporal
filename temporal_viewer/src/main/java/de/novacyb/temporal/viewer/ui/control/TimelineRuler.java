package de.novacyb.temporal.viewer.ui.control;

import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;

/**
 * Timeline Ruler Control
 * Created on 20.12.2018.
 */
public class TimelineRuler extends AnchorPane implements ITimeScale {
    private final TimelineView view;

    public TimelineRuler(final TimelineView view) {
        this.view = view;

        // TODO test code for ui experiments
        setPrefHeight(25D);

    }

    @Override
    public void updateTimeScale(final double newValue) {
        getChildren().clear();
        final var count = view.getMaxTime();
        for (var i = 0; i < count; i++) {
            if (i % 10 == 0) {
                final var line = new Line(i * newValue,5D,i * newValue,20D);
                getChildren().add(line);
            } else {
                final var line = new Line(i * newValue,8D,i * newValue,18D);
                getChildren().add(line);
            }
        }
    }
}

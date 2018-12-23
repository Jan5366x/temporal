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


        final var separation = view.getTimeScale();
        for (var i = 1; i < 100; i++) {
            final var line = new Line(i * separation,5D,i * separation,20D);
            getChildren().add(line);
        }

    }
}

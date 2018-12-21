package de.novacyb.temporal.viewer.ui.control;

import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

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
        setBackground(new Background(new BackgroundFill(Color.CORNFLOWERBLUE, CornerRadii.EMPTY, Insets.EMPTY)));

        /*
        final var separation = 15D;
        for (var i = 1; i < 55; i++) {
            final var line = new Line(i * separation,5D,i * separation,20D);
            getChildren().add(line);
        }
        */
    }
}

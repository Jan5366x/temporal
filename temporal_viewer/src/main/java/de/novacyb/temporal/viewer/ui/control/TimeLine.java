package de.novacyb.temporal.viewer.ui.control;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

/**
 * Temporal Time Line Control
 * Created on 19.12.2018.
 */
public class TimeLine extends AnchorPane {

    private final TimeLineView view;

    /**
     * time line control constructor
     * @param view the time line view
     */
    public TimeLine(final TimeLineView view) {
        this.view = view;

        setPrefHeight(50D);

        // TODO test code for ui experiments
        final var line = new Line(0D,25D,1200D,25D);
        //widthProperty().addListener((observable, oldValue, newValue) -> line.setEndX(newValue.doubleValue()));

        line.getStrokeDashArray().addAll(15D, 10D);
        line.setStroke(new Color(0.4D,0.4D,0.4D,1D));
        getChildren().add(line);

        final var loc = Math.random() * 600D;
        final var length = 50D + Math.random() * 100D;

        final var f1 = new TimeFrame( length);
        final var ft1 = new TimeSpot();
        final var ft2 = new TimeSpot();

        AnchorPane.setLeftAnchor(f1,loc);
        AnchorPane.setLeftAnchor(ft1,loc);
        AnchorPane.setLeftAnchor(ft2,loc + length);

        getChildren().addAll(f1,ft1,ft2);


    }

    // TODO implement timeline
}

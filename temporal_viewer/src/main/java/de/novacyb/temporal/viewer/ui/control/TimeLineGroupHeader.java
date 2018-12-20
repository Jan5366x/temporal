package de.novacyb.temporal.viewer.ui.control;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;


/**
 * Time Line Group Header
 * Created on 20.12.2018.
 */
public class TimeLineGroupHeader extends AnchorPane {

    private final Label titleLabel;

    public TimeLineGroupHeader(final String title) {
        // TODO test code for ui experiments

        titleLabel = new Label(title);
        titleLabel.setMaxWidth(Double.MAX_VALUE);
        AnchorPane.setTopAnchor(titleLabel, 0D);
        AnchorPane.setBottomAnchor(titleLabel, 0D);
        AnchorPane.setLeftAnchor(titleLabel, 0D);
        AnchorPane.setRightAnchor(titleLabel, 15D);
        titleLabel.setAlignment(Pos.CENTER_RIGHT);
        getChildren().addAll(titleLabel);

        setPrefHeight(50D);
        setPrefWidth(120D);
    }
}

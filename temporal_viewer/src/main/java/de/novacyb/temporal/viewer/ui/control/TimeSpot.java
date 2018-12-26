package de.novacyb.temporal.viewer.ui.control;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Time Spot Control
 * Created on 20.12.2018.
 */
public class TimeSpot extends AnchorPane implements ITimeScale {
    private final Rectangle rect1;


    public TimeSpot() {
        // TODO test code for ui experiments

        rect1 = new Rectangle(0 ,10D,5D,30D);
        rect1.setFill(new Color(0.2D,0.2D,0.2D,0.95D));
        getChildren().add(rect1);
    }

    @Override
    public void updateTimeScale(double timeScale) {
    }
}

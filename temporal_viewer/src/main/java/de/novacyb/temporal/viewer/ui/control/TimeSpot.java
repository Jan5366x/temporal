package de.novacyb.temporal.viewer.ui.control;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Time Spot Control
 * Created on 20.12.2018.
 */
public class TimeSpot extends AnchorPane implements ITimeScale {
    private final Rectangle rect1;

    private final SimpleIntegerProperty timeStart = new SimpleIntegerProperty();

    public TimeSpot(final int timeStart) {

        setTimeStart(timeStart);
        // TODO test code for ui experiments

        rect1 = new Rectangle(0 ,10D,4D,30D);
        rect1.setFill(new Color(0.2D,0.2D,0.2D,0.95D));
        getChildren().add(rect1);
    }

    @Override
    public void updateTimeScale(double timeScale) {
        setLayoutX((getTimeStart() * timeScale) - (rect1.getWidth() / 2));
    }

    public int getTimeStart() {
        return timeStart.get();
    }

    public SimpleIntegerProperty timeStartProperty() {
        return timeStart;
    }

    public void setTimeStart(int timeStart) {
        this.timeStart.set(timeStart);
    }
}

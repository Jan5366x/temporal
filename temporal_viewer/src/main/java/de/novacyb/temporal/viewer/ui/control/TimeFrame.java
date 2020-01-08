package de.novacyb.temporal.viewer.ui.control;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.css.PseudoClass;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * Time Frame Control
 * Created on 20.12.2018.
 */
public class TimeFrame extends AnchorPane implements ITimeScale {
    private static PseudoClass ACTIVE_PSEUDO_CLASS = PseudoClass.getPseudoClass("active");

    private final Label label;

    private final SimpleBooleanProperty active = new SimpleBooleanProperty();
    private final SimpleIntegerProperty timeWidth = new SimpleIntegerProperty();
    private final SimpleIntegerProperty timeStart = new SimpleIntegerProperty();

    private Pane barNode = new Pane();

    public TimeFrame(final int timeStart, final int timeWidth) {

        setTimeStart(timeStart);
        setTimeWidth(timeWidth);

        setHeight(25D);

        // setup pseudo classes
        active.addListener(e -> pseudoClassStateChanged(ACTIVE_PSEUDO_CLASS, active.get()));


        // setup label
        label = new Label(timeWidth + " ms");
        label.setId("timeFrameLabel");
        label.setMaxWidth(Double.MAX_VALUE);
        AnchorPane.setTopAnchor(label, 35.0D);
        AnchorPane.setLeftAnchor(label, 0.0D);
        AnchorPane.setRightAnchor(label, 0.0D);
        label.setAlignment(Pos.CENTER);

        // setup bar node
        barNode.setId("timeFrame");
        AnchorPane.setTopAnchor(barNode, 14.0D);
        AnchorPane.setBottomAnchor(barNode, 14.0D);
        AnchorPane.setLeftAnchor(barNode, 0.0D);
        AnchorPane.setRightAnchor(barNode, 0.0D);

        getChildren().addAll(barNode, label);
    }

    public int getTimeWidth() {
        return timeWidth.get();
    }

    public SimpleIntegerProperty timeWidthProperty() {
        return timeWidth;
    }

    public void setTimeWidth(final int timeWidth) {
        this.timeWidth.set(timeWidth);
    }

    public int getTimeStart() {
        return timeStart.get();
    }

    public SimpleIntegerProperty timeStartProperty() {
        return timeStart;
    }

    public void setTimeStart(final int timeStart) {
        this.timeStart.set(timeStart);
    }

    @Override
    public void updateTimeScale(final double timeScale) {
        setLayoutX(getTimeStart() * timeScale);
        setPrefWidth(getTimeWidth() * timeScale);
    }
}

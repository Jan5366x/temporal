package de.novacyb.temporal.viewer.ui.control;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.css.PseudoClass;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Time Frame Control
 * Created on 20.12.2018.
 */
public class TimeFrame extends AnchorPane {
    private static PseudoClass ACTIVE_PSEUDO_CLASS = PseudoClass.getPseudoClass("active");

    private final Rectangle rect1;
    private final Label label;

    private final SimpleBooleanProperty active = new SimpleBooleanProperty();


    public TimeFrame(final double width) {

        // setup pseudo classes
        active.addListener(e -> pseudoClassStateChanged(ACTIVE_PSEUDO_CLASS, active.get()));

        // setup base ui
        // TODO test code for ui experiments
        rect1 = new Rectangle(0D,15D,width,20D);
        rect1.setFill(new Color(0.4D,0.7D,0.1D,0.95D));

        label = new Label((int) width / 10 + " ms");
        label.setMaxWidth(Double.MAX_VALUE);
        AnchorPane.setTopAnchor(label, 35.0D);
        AnchorPane.setLeftAnchor(label, 0.0D);
        AnchorPane.setRightAnchor(label, 0.0D);
        label.setAlignment(Pos.CENTER);

        getChildren().addAll(rect1, label);
    }

}

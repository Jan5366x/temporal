package de.novacyb.temporal.viewer.ui.control;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

/**
 * Time Line View Control
 * Created on 19.12.2018.
 */
public class TimeLineView extends ScrollPane {

    private final VBox content = new VBox();
    public TimeLineView() {
        setVbarPolicy(ScrollBarPolicy.ALWAYS);
        setHbarPolicy(ScrollBarPolicy.ALWAYS);
        setContent(content);

        // TODO remove test code
        content.getChildren().add(new TimeLine(this));
        content.getChildren().add(new TimeLine(this));
        content.getChildren().add(new TimeLine(this));
        content.getChildren().add(new TimeLine(this));
        content.getChildren().add(new TimeLine(this));
    }
}

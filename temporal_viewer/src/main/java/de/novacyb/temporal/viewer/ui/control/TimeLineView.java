package de.novacyb.temporal.viewer.ui.control;

import javafx.scene.control.ScrollPane;

/**
 * Time Line View Control
 *
 * @author jschwien
 * Created on 19.12.2018.
 */
public class TimeLineView extends ScrollPane {

    public TimeLineView() {
        setVbarPolicy(ScrollBarPolicy.ALWAYS);
        setHbarPolicy(ScrollBarPolicy.ALWAYS);
    }
}

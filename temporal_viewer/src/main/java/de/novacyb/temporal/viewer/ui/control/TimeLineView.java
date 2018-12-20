package de.novacyb.temporal.viewer.ui.control;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import static javafx.scene.control.ScrollPane.ScrollBarPolicy.ALWAYS;

/**
 * Time Line View Control
 * Created on 19.12.2018.
 */
public class TimeLineView extends AnchorPane {

    private final ScrollPane scrollPane = new ScrollPane();
    private final VBox content = new VBox();

    public TimeLineView() {

        // TODO remove test code

        scrollPane.setVbarPolicy(ALWAYS);
        scrollPane.setHbarPolicy(ALWAYS);
        scrollPane.setContent(content);

        final var headerSize = 120D;

        final var headerBox = new VBox();
        headerBox.setPrefWidth(headerSize);

        getChildren().addAll(headerBox,scrollPane);

        AnchorPane.setTopAnchor(headerBox,0D);
        AnchorPane.setBottomAnchor(headerBox,0D);
        AnchorPane.setLeftAnchor(headerBox,0D);

        AnchorPane.setTopAnchor(scrollPane,0D);
        AnchorPane.setBottomAnchor(scrollPane,0D);
        AnchorPane.setLeftAnchor(scrollPane,headerSize);
        AnchorPane.setRightAnchor(scrollPane,0D);

        headerBox.getChildren().addAll(
                new TimeLineGroupHeader("Timing Token A"),
                new TimeLineGroupHeader("Timing Token B"),
                new TimeLineGroupHeader("Timing Token C"));

        content.getChildren().addAll(new TimeLine(this),new TimeLine(this),new TimeLine(this));


    }
}

package KupidonTeam.gui;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class DialogPaneController {

    @FXML
    private Pane pane;

    @FXML
    private Label label;

    @FXML
    private Button cancelButton;

    @FXML
    private Button okButton;

    @FXML
    void initialize() {
        assert pane != null : "fx:id=\"pane\" was not injected: check your FXML file 'dialog_pane.fxml'.";
        assert label != null : "fx:id=\"label\" was not injected: check your FXML file 'dialog_pane.fxml'.";
        assert cancelButton != null : "fx:id=\"cancelButton\" was not injected: check your FXML file 'dialog_pane.fxml'.";
        assert okButton != null : "fx:id=\"okButton\" was not injected: check your FXML file 'dialog_pane.fxml'.";

    }

    public void load(String message, EventHandler<Event> handler) {
        label.setText(message);
        cancelButton.setOnMouseClicked(ev -> {
                    System.out.println("Hello!");
                    DialogPaneWrapper.getCurrentStage().close();
                }
        );
        okButton.setOnMouseClicked(handler);
    }
}

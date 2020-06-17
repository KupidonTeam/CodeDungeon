package KupidonTeam.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

public class PrizesPaneController {

    @FXML
    private Pane pane;

    @FXML
    private Label label;

    @FXML
    private Button okButton;

    @FXML
    private FlowPane prizesPane;

    @FXML
    void initialize() {
        assert pane != null : "fx:id=\"pane\" was not injected: check your FXML file 'prizes_pane.fxml'.";
        assert label != null : "fx:id=\"label\" was not injected: check your FXML file 'prizes_pane.fxml'.";
        assert okButton != null : "fx:id=\"okButton\" was not injected: check your FXML file 'prizes_pane.fxml'.";
        assert prizesPane != null : "fx:id=\"prizesPane\" was not injected: check your FXML file 'prizes_pane.fxml'.";
    }
}

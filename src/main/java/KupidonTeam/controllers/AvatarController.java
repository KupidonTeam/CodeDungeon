package KupidonTeam.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AvatarController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Pane mainPane;

    @FXML
    void initialize() {
        assert mainPane != null : "fx:id=\"mainPane\" was not injected: check your FXML file 'avatar_pane.fxml'.";

        mainPane.getChildren()
                .filtered(el -> el.getStyleClass().toString().contains("avatar"))
                .forEach(el -> el.setOnMouseClicked(event -> {
                    setAvatarImage(el.getId());
                }));
    }

    private void setAvatarImage(String avatarId) {
        System.out.println("pressed = " + avatarId);
        try {
            FXMLLoader loader = new FXMLLoader();
            Parent currentContent = loader.load(getClass().getResourceAsStream("/fxml/avatar_pane.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

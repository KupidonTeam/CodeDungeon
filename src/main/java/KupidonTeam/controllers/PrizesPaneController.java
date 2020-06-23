package KupidonTeam.controllers;

import KupidonTeam.model.items.Armor;
import KupidonTeam.model.items.Item;
import KupidonTeam.model.items.Weapon;
import KupidonTeam.model.utils.SoundPlayer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

import java.util.List;

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

        okButton.setOnMouseClicked(event -> okButton.getScene().getWindow().hide());
    }

    public void loadPrizes(List<? extends Item> prizes) {
        new SoundPlayer().victory();

        prizes.forEach(el -> {
            ImageView imageView = new ImageView();
            imageView.setFitWidth(64);
            imageView.setFitHeight(64);
            System.out.println(el.getName());

            Tooltip tooltip;

            if (el instanceof Armor) {
                imageView.setImage(new Image(getClass().getResourceAsStream("/assets/armor/" + el.getName() + ".png")));
            } else if (el instanceof Weapon) {
                imageView.setImage(new Image(getClass().getResourceAsStream("/assets/weapons/" + el.getName() + ".png")));
            } else {
                imageView.setImage(new Image(getClass().getResourceAsStream("/assets/food/" + el.getName() + ".png")));
            }

            tooltip = new Tooltip(el.toString());
            Tooltip.install(imageView, tooltip);
            prizesPane.getChildren().add(imageView);
        });
    }
}

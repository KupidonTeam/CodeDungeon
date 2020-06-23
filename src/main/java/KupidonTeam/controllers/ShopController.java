package KupidonTeam.controllers;

import KupidonTeam.model.characters.player.Player;
import KupidonTeam.model.items.Armor;
import KupidonTeam.model.items.Weapon;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class ShopController {
    private Pane shopPane;
    private MainController mainController;

    public ShopController(MainController mainController, Pane shopPane) {
        this.shopPane = shopPane;
        this.mainController = mainController;
    }

    public void loadGoods() {
        Player player = Player.getInstance();
        shopPane.getChildren().clear();
        player.getInventory().getItems().forEach(item -> {

            VBox itemLine = new VBox();
            itemLine.setAlignment(Pos.CENTER);
            Tooltip tooltip;
            Button useBt = new Button();
            useBt.setPrefWidth(60);
            useBt.setStyle("" +
                    "-fx-background-color :  #961307;" +
                    "-fx-text-fill : #816d64;" +
                    "-fx-border-color:  #8e7c74;");

            ImageView itemImage = new ImageView();
            itemImage.setFitHeight(100);
            itemImage.setFitWidth(100);

            if (item instanceof Armor) {
                System.out.println("ARMOR = " + item.getName());
                itemImage.setImage(new Image("/assets/armor/" + item.getName() + ".png"));
            } else if (item instanceof Weapon) {
                System.out.println("WEAPON");
                itemImage.setImage(new Image("/assets/weapons/" + item.getName() + ".png"));
            } else {
                itemImage.setImage(new Image("/assets/food/" + item.getName() + ".png"));
            }

            itemLine.getChildren().add(itemImage);
            tooltip = new Tooltip(item.toString());
            Tooltip.install(itemImage, tooltip);
            useBt.setText("Sell");
            useBt.setOnMouseClicked(event -> {
                player.drop(item);
                player.setGold((int) (player.getGold() + item.getPrice()));
                mainController.update(false);
                loadGoods();
            });
            itemLine.getChildren().add(useBt);
            shopPane.getChildren().add(itemLine);
        });
    }
}

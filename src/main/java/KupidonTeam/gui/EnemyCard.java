package KupidonTeam.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class EnemyCard {
    private AnchorPane enemyCard;
    private EnemyCardController enemyCardController;

    public EnemyCard() throws IOException {
        String path = "/fxml/EnemyCard.fxml";
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(EnemyCard.class.getResourceAsStream(path));
        enemyCard = new AnchorPane(root);
        enemyCard.setOnMouseClicked(event -> {
            enemyCard.setLayoutX(event.getX());
            enemyCard.setLayoutY(event.getY());
        });
        enemyCardController = loader.getController();
    }

    public AnchorPane getEnemyCard() {
        return enemyCard;
    }

    public void setEnemyName(String name) {
        enemyCardController.setEnemyName(name);
    }

    public void setDescription(String description) {
        enemyCardController.setDescription(description);
    }

    public void setHp(int hp) {
        enemyCardController.setHp(hp);
    }

    public void setDamage(int damage) {
        enemyCardController.setDamage(damage);
    }

    public void setArmor(int armor) {
        enemyCardController.setArmor(armor);
    }

    public void setImage(ImageView image) {
        enemyCardController.setImage(image);
    }

    public void dispose() {

        enemyCard.getChildren().clear();
    }


}

package KupidonTeam.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class EnemyCard {
    private AnchorPane enemyCard;

    public EnemyCard() throws IOException {
        String path = "/fxml/EnemyCard.fxml";
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(EnemyCard.class.getResourceAsStream(path));
        enemyCard = new AnchorPane(root);
        enemyCard.setOnMouseClicked(event -> {
            enemyCard.setLayoutX(event.getX());
            enemyCard.setLayoutY(event.getY());
        });
        EnemyCardController enemyCardController = loader.getController();
    }

    public AnchorPane getEnemyCard() {
        return enemyCard;
    }

    public void setHp() {

    }

}

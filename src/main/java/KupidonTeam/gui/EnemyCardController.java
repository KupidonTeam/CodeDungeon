package KupidonTeam.gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import lombok.Getter;
import lombok.Setter;


public class EnemyCardController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane enemyCard;

    @FXML
    private Label enemyName;

    @FXML
    private ImageView image;

    @FXML
    private Label hp;

    @FXML
    private Label damage;

    @FXML
    private Label armor;

    @FXML
    private Label description;

    @FXML
    void initialize() {
        assert enemyCard != null : "fx:id=\"enemyCard\" was not injected: check your FXML file 'EnemyCard.fxml'.";
        assert enemyName != null : "fx:id=\"enemyName\" was not injected: check your FXML file 'EnemyCard.fxml'.";
        assert image != null : "fx:id=\"image\" was not injected: check your FXML file 'EnemyCard.fxml'.";
        assert hp != null : "fx:id=\"hp\" was not injected: check your FXML file 'EnemyCard.fxml'.";
        assert damage != null : "fx:id=\"damage\" was not injected: check your FXML file 'EnemyCard.fxml'.";
        assert armor != null : "fx:id=\"armor\" was not injected: check your FXML file 'EnemyCard.fxml'.";
        assert description != null : "fx:id=\"description\" was not injected: check your FXML file 'EnemyCard.fxml'.";

    }

    public void setEnemyName(String name) {
        this.enemyName.setText(name);
    }

    public void setDescription(String description) {
        this.description.setText(description);
    }

    public void setHp(int hp) {
        this.hp.setText("" + hp);
    }

    public void setDamage(int damage) {
        this.damage.setText("" + damage);
    }

    public void setArmor(int armor) {
        this.armor.setText("" + armor);
    }

    public void setImage(Image image) {
        this.image.setImage(image);
    }

}


package KupidonTeam.gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Stream;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.util.Duration;
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
        assert enemyCard != null : "fx:id=\"enemyCard\" was not injected: check your FXML file 'enemy_card.fxml'.";
        assert enemyName != null : "fx:id=\"enemyName\" was not injected: check your FXML file 'enemy_card.fxml'.";
        assert image != null : "fx:id=\"image\" was not injected: check your FXML file 'enemy_card.fxml'.";
        assert hp != null : "fx:id=\"hp\" was not injected: check your FXML file 'enemy_card.fxml'.";
        assert damage != null : "fx:id=\"damage\" was not injected: check your FXML file 'enemy_card.fxml'.";
        assert armor != null : "fx:id=\"armor\" was not injected: check your FXML file 'enemy_card.fxml'.";
        assert description != null : "fx:id=\"description\" was not injected: check your FXML file 'enemy_card.fxml'.";

       // enemyCard.setOnMouseClicked(ev -> setBorder(enemyCard));

    }

    public static void setBorder(Pane pane) {
        Color[] colors = Stream.of("tomato", "#961307", "#8e7c74", "#39100f", "#251a1a", "red", "#816d64")
                .map(Color::web)
                .toArray(Color[]::new);

        List<Border> list = new ArrayList<>();


        int[] mills = {-200};
        KeyFrame[] keyFrames = Stream.iterate(0, i -> i + 1)
                .limit(100)
                .map(i -> new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
                        new Stop[]{new Stop(0, colors[i % colors.length]),
                                new Stop(1, colors[(i + 1) % colors.length])}))
                .map(lg -> new Border(new BorderStroke(lg, BorderStrokeStyle.SOLID,
                        new CornerRadii(0), new BorderWidths(4))))
                .map(b -> new KeyFrame(Duration.millis(mills[0] += 200), new KeyValue(pane.borderProperty(),
                        b, Interpolator.EASE_IN)))
                .toArray(KeyFrame[]::new);

        Timeline timeline = new Timeline(keyFrames);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
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


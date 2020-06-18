package KupidonTeam.gui;

import KupidonTeam.characters.classes.enemies.Enemy;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.util.Duration;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Stream;

public class EnemyCard {
    private AnchorPane enemyCard;
    private Enemy enemy;
    @Getter
    @Setter
    private EnemyCardController enemyCardController;
    private Timeline timeline;

    public EnemyCard() {
        String path = "/fxml/enemy_card.fxml";
        FXMLLoader loader = new FXMLLoader();
        Parent root = null;
        try {
            root = loader.load(EnemyCard.class.getResourceAsStream(path));
            enemyCard = new AnchorPane(root);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public EnemyCard(Enemy enemy) {
        this.enemy = enemy;
        String path = "/fxml/enemy_card.fxml";
        FXMLLoader loader = new FXMLLoader();
        Parent root = null;
        try {
            System.out.println("enemy = " + enemy.toString());
            root = loader.load(EnemyCard.class.getResourceAsStream(path));
            enemyCard = new AnchorPane(root);
            enemyCardController = loader.getController();
            setImage(randomImage());
            setEnemyName(enemy.getName());
            setArmor(enemy.getStats().getArmorClass());
            setDamage(enemy.getStats().getDexterity());
            setDescription(enemy.getDescription());
            setHp(enemy.getStats().getHits());
            setImage(randomImage());
        } catch (IOException e) {
            e.printStackTrace();
        }

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

    public void setImage(Image image) {
        enemyCardController.setImage(image);
    }

    public void dispose() {
        enemyCard.getChildren().clear();
    }

    public void setBorder() {
        enemyCardController.setBorder(enemyCard);
    }

    private Image randomImage() {
        String path = "src/main/resources/assets/SIMPLEAvatarsIcons/64X64";
        File files = new File(path);
        List<String> images = new LinkedList<>();
        Random random = new Random();
        Stream.of(files.listFiles()).forEach(el -> images.add(el.getName()));
        path = "/assets/SIMPLEAvatarsIcons/64X64/" + images.get(random.nextInt(images.size()));
        System.out.println("PUT IMAGE  = " + path);
        return new Image(path);
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public void setBorder() {
        Color[] colors = Stream.of("tomato", "#961307", "#8e7c74", "#39100f", "#251a1a", "red", "#816d64")
                .map(Color::web)
                .toArray(Color[]::new);

        int[] mills = {-200};
        KeyFrame[] keyFrames = Stream.iterate(0, i -> i + 1)
                .limit(100)
                .map(i -> new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
                        new Stop[]{new Stop(0, colors[i % colors.length]),
                                new Stop(1, colors[(i + 1) % colors.length])}))
                .map(lg -> new Border(new BorderStroke(lg, BorderStrokeStyle.SOLID,
                        new CornerRadii(0), new BorderWidths(4))))
                .map(b -> new KeyFrame(Duration.millis(mills[0] += 200), new KeyValue(enemyCard.borderProperty(),
                        b, Interpolator.EASE_IN)))
                .toArray(KeyFrame[]::new);

        timeline = new Timeline(keyFrames);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public void deleteBorder() {
        Color[] colors = Stream.of("tomato", "#961307", "#8e7c74", "#39100f", "#251a1a", "red", "#816d64")
                .map(Color::web)
                .toArray(Color[]::new);

        int[] mills = {-200};
        KeyFrame[] keyFrames = Stream.iterate(0, i -> i + 1)
                .limit(100)
                .map(i -> new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
                        new Stop[]{new Stop(0, colors[i % colors.length]),
                                new Stop(1, colors[(i + 1) % colors.length])}))
                .map(lg -> new Border(new BorderStroke(lg, BorderStrokeStyle.SOLID,
                        new CornerRadii(0), new BorderWidths(0))))
                .map(b -> new KeyFrame(Duration.millis(mills[0] += 200), new KeyValue(enemyCard.borderProperty(),
                        b, Interpolator.EASE_IN)))
                .toArray(KeyFrame[]::new);

        timeline = new Timeline(keyFrames);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EnemyCard enemyCard = (EnemyCard) o;
        return Objects.equals(enemy, enemyCard.enemy);
    }
}

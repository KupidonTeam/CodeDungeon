package KupidonTeam.gui;

import KupidonTeam.characters.classes.enemies.Enemy;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class EnemyCard {
    private AnchorPane enemyCard;
    private Enemy enemy;
    private EnemyCardController enemyCardController;

    public EnemyCard() {
        String path = "/fxml/EnemyCard.fxml";
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
        String path = "/fxml/EnemyCard.fxml";
        FXMLLoader loader = new FXMLLoader();
        Parent root = null;
        try {
            root = loader.load(EnemyCard.class.getResourceAsStream(path));
            enemyCard = new AnchorPane(root);
            enemyCard.setOnMouseClicked(event -> {
                enemyCard.setLayoutX(event.getX());
                enemyCard.setLayoutY(event.getY());
            });
            enemyCardController = loader.getController();
            setEnemyName(enemy.getName());
            setArmor(enemy.getStats().getArmorClass());
            setDamage(enemy.getStats().getDexterity());
            setDescription(enemy.getDescription());
            setHp(enemy.getStats().getHits());
//            setImage(randomImage());
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

    public void setImage(ImageView image) {
        enemyCardController.setImage(image);
    }

    public void dispose() {
        enemyCard.getChildren().clear();
    }

    private ImageView randomImage() {
        String path = "src/main/resources/assets/SIMPLEAvatarsIcons/64X64";
        File files = new File(path);
        List<String> images = new LinkedList<>();
        Random random = new Random();
        Stream.of(files.listFiles()).forEach(el -> images.add(el.getName()));
        path = "/assets/SIMPLEAvatarsIcons/64X64/" + images.get(random.nextInt(images.size()));
        return new ImageView(path);
    }

    public Enemy getEnemy() {
        return enemy;
    }
}

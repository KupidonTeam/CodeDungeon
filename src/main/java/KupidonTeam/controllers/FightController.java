package KupidonTeam.controllers;

import KupidonTeam.characters.player.Player;
import KupidonTeam.characters.skills.Skill;
import KupidonTeam.gui.EnemyCard;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class FightController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label passBattle;

    @FXML
    private Label playerNameBattle;

    @FXML
    private Label enemyNameBattle;

    @FXML
    private StackPane playerImageBattle;

    @FXML
    private StackPane enemyImageBattle;

    @FXML
    private FlowPane attackImageBattle;

    @FXML
    private Label damageBattle;

    private int enemyDamage;
    private String playerSkillName;
    private int playerDamage;
    private String enemySkillName;
    private ImageView skillImage;
    private ImageView enemyImage;
    private Skill playerSkill;
    private Skill enemySkill;

    @FXML
    void initialize() {
        initFxml();

    }

    public void setData(Player player, EnemyCard enemyCard, int enemyDamage, String playerSkillName, int playerDamage, String enemySkillName) {
        this.enemyDamage = enemyDamage;
        this.playerSkillName = playerSkillName;
        this.playerDamage = playerDamage;
        this.enemySkillName = enemySkillName;

        for (Skill skill : player.getSkills()) {
            if (skill.getName().equalsIgnoreCase(playerSkillName))
                playerSkill = skill;
        }

        for (Skill skill : enemyCard.getEnemy().getSkillList()) {
            if (skill.getName().equalsIgnoreCase(enemySkillName)) {
                enemySkill = skill;
            }
        }

        setUpData(enemyCard);
        show();
    }

    private void show() {
        attackImageBattle.getChildren().add(skillImage);
        playerNameBattle.setText(Player.getInstance().getName());
        fight("Player turn", playerSkill, enemyDamage, enemyImageBattle);

        transition(new Pane()).setOnFinished(event -> {
            fight("Enemy turn", enemySkill, playerDamage, playerImageBattle);
            transition(new Pane()).setOnFinished(event2 -> attackImageBattle.getScene().getWindow().hide());
        });
    }

    private void fight(String turn, Skill skill, int damage, StackPane imageDamaged) {
        passBattle.setText(turn);
        damageBattle.setText("");
        skillImage.setImage(loadSkillImage(skill.getName()));
        transition(attackImageBattle);
        skill.skillSound();
        damageBattle.setText("-" + damage);
        blendEffect(imageDamaged);
    }

    private FadeTransition transition(Node node) {
        FadeTransition ft = new FadeTransition(Duration.millis(3000));
        ft.setNode(node);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.setAutoReverse(false);
        ft.play();

        return ft;
    }

    private Image loadSkillImage(String skillName) {
        String imageUri = "/assets/skills/" + skillName.toLowerCase() + ".png";

        return new Image(imageUri);
    }

    private void setUpData(EnemyCard enemyCard) {
        skillImage = new ImageView();
        skillImage.setFitHeight(100);
        skillImage.setFitWidth(100);

        ImageView playerImg = new ImageView(Player.getInstance().getAvatarIcon());
        playerImg.setFitWidth(160);
        playerImg.setFitHeight(160);

        enemyImage = new ImageView();
        enemyImage.setImage(enemyCard.getImage());
        enemyImage.setFitHeight(160);
        enemyImage.setFitWidth(160);

        enemyImageBattle.getChildren().add(enemyImage);
        playerImageBattle.getChildren().add(playerImg);

        playerNameBattle.setText(Player.getInstance().getName());
        enemyNameBattle.setText(enemyCard.getEnemy().getName());
    }

    private void initFxml() {
        assert passBattle != null : "fx:id=\"passBattle\" was not injected: check your FXML file 'fight.fxml'.";
        assert playerNameBattle != null : "fx:id=\"playerNameBattle\" was not injected: check your FXML file 'fight.fxml'.";
        assert enemyNameBattle != null : "fx:id=\"enemyNameBattle\" was not     injected: check your FXML file 'fight.fxml'.";
        assert playerImageBattle != null : "fx:id=\"playerImageBattle\" was not injected: check your FXML file 'fight.fxml'.";
        assert enemyImageBattle != null : "fx:id=\"enemyImageBattle\" was not injected: check your FXML file 'fight.fxml'.";
        assert attackImageBattle != null : "fx:id=\"attackImageBattle\" was not injected: check your FXML file 'fight.fxml'.";
        assert damageBattle != null : "fx:id=\"damageBattle\" was not injected: check your FXML file 'fight.fxml'.";
    }

    private void blendEffect(Pane imagePane) {
        Rectangle rectangle = new Rectangle();
        rectangle.setX(enemyImage.getX());
        rectangle.setY(enemyImage.getY());
        rectangle.setWidth(enemyImage.getFitWidth());
        rectangle.setHeight(enemyImage.getFitHeight());
        rectangle.setFill(Color.rgb(250, 0, 0, 0.3));
        imagePane.getChildren().add(rectangle);
        FadeTransition ft = new FadeTransition(Duration.millis(500));
        ft.setNode(rectangle);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.setCycleCount(3);
        ft.play();
    }
}



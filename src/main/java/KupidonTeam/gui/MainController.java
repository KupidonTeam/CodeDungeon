package KupidonTeam.gui;

import KupidonTeam.characters.classes.enemies.Enemy;
import KupidonTeam.characters.classes.skills.Skill;
import KupidonTeam.controllers.BattleController;
import KupidonTeam.locations.Dungeon;
import KupidonTeam.login.SignLogic;
import KupidonTeam.player.Player;
import KupidonTeam.server.Connection;
import KupidonTeam.utils.Container;
import KupidonTeam.utils.JSON;
import KupidonTeam.utils.SoundPlayer;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.SneakyThrows;

import java.net.URISyntaxException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class MainController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;


    @FXML
    private ScrollPane carriedPane;

    @FXML
    private TextArea wornPane;

    @FXML
    private TextArea nearPane;

    @FXML
    private FlowPane mapPane;

    @FXML
    private TextArea chatPane;

    @FXML
    private TextArea statsTextArea;

    @FXML
    private TextField messageInput;

    @FXML
    private Button sendButton;

    @FXML
    private TextArea directMessagePane;

    @FXML
    private TextField directMessageInput;

    @FXML
    private Button bt1;

    @FXML
    private MenuButton settingsBt;

    @FXML
    private Button bt3;

    @FXML
    private Button bt4;

    @FXML
    private Button exitButton;

    @FXML
    private Label locationName;

    @FXML
    private ImageView skill1;

    @FXML
    private ImageView skill2;

    @FXML
    private ImageView skill3;

    @FXML
    private ImageView skill4;

    @FXML
    private ImageView skill5;

    @FXML
    private Group skillGroup;

    @FXML
    private ImageView avatarIcon;

    @FXML
    private Label nickNameLabel;

    @FXML
    private ProgressBar hpBar;

    @FXML
    private AnchorPane enemyCard;

    @FXML
    private FlowPane cardTable;

    @FXML
    private AnchorPane attackButton;

    @FXML
    private Slider musicVolume;

    @FXML
    private ProgressBar expBar;

    private Connection server;
    private Player player;
    private Stage dialogStage;
    private List<ImageView> skillImages;
    private BattleController battleController;
    private Dungeon currentRoom;
    private List<EnemyCard> enemyCards;
    private int peekedSkill;


    @FXML
    void initialize() {
        loadFxmlData();
        setUp();
        loadPlayerInformation();
        List<EnemyCard> enemyCards = new LinkedList<>();
        enemyCards.add(new EnemyCard());


        bt3.setOnMouseClicked(event -> getDungeonSkeleton());
        bt4.setOnMouseClicked(event -> {
            cardTable.getChildren().clear();
            mapPane.getChildren().clear();
        });

    }

    private void setUp() {
        server = Connection.getConnection();
        dialogStage = new Stage();
        dialogStage.initStyle(StageStyle.UNDECORATED);
        panesSetUp();
        buttonsSetUp();
        server.setChatArea(chatPane);
        server.setCardTable(cardTable);

    }

    private void panesSetUp() {
        chatPane.setText("");
        chatPane.setWrapText(true);
        chatPane.setPrefColumnCount(30);
        chatPane.setEditable(false);
        statsTextArea.setWrapText(true);
        statsTextArea.setPrefColumnCount(30);
        statsTextArea.setPadding(new Insets(5));
        cardTable.getChildren().clear();
        cardTable.setAlignment(Pos.CENTER);

        cardTable.setPadding(new Insets(15));
        cardTable.setHgap(5);
        cardTable.setVgap(10);
        messageInput.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                sendChatMessage();
            }
        });

    }

    //Примерный тест инвенторя
    private void initInventory() {
        VBox inventory = new VBox();
        HBox weaponLine1 = new HBox();
        ImageView weapon = new ImageView("/assets/weapons/Export_64/AxeDouble.png");
        weapon.setFitHeight(32);
        weapon.setFitWidth(32);
        Button dropBt = new Button("Drop");
        weaponLine1.getChildren().addAll(weapon, dropBt);
        HBox weaponLine2 = new HBox();
        ImageView weapon2 = new ImageView("/assets/weapons/Export_64/Arrow.png");
        weapon2.setFitHeight(32);
        weapon2.setFitWidth(32);
        Button sellBt = new Button("Sell");
        weaponLine2.getChildren().addAll(weapon2, sellBt);
        weaponLine1.setStyle("-fx-background-color: red;");
        weaponLine2.setStyle("-fx-background-color: red;");
        inventory.getChildren().addAll(weaponLine1, weaponLine2);
        inventory.setSpacing(5);
        inventory.setStyle("-fx-background-color: red;");
        carriedPane.setContent(inventory);
        carriedPane.setStyle("-fx-background-color: red;");
    }


    //я просто проверял работу
    public void messageDialog(String messageText, EventHandler eventHandler) {

        Text message = new Text(messageText);
        message.setFill(Color.WHITE);
        message.setStyle(
                "-fx-font: 18 arial;" +
                        "-fx-text-alignment : center;");

        VBox win = new VBox();
        HBox buttons = new HBox();
        buttons.setSpacing(20);
        buttons.setPadding(new Insets(10));
        buttons.setAlignment(Pos.CENTER);
        win.setStyle(
                "-fx-background-image : url(/assets/GUI_Parts/Gui_parts/barmid_ready.png);" +
                        "-fx-background-size : 600 300;");

        win.setPrefSize(600, 300);
        win.setSpacing(20);
        Scene scene = new Scene(win);
        ImageView okImage = new ImageView("/assets/GUI_Parts/Gui_parts/button2.png");
        ImageView cancelImage = new ImageView("/assets/GUI_Parts/Gui_parts/button_ready_on.png");
        okImage.setFitHeight(40);
        cancelImage.setFitHeight(40);
        okImage.setFitWidth(80);
        cancelImage.setFitWidth(80);

        StackPane okBt = new StackPane();
        okBt.getChildren().add(okImage);
        StackPane ok = new StackPane();
        ok.getChildren().add(okImage);
        Label okWin = new Label("Ok");
        okWin.setTextFill(Color.BURLYWOOD);
        ok.getChildren().add(okWin);


        StackPane cancelBt = new StackPane();
        cancelBt.getChildren().add(cancelImage);
        StackPane cancel = new StackPane();
        cancel.getChildren().add(cancelImage);
        Label cancelWin = new Label("Cancel");
        cancelWin.setTextFill(Color.BURLYWOOD);
        cancel.getChildren().add(cancelWin);

        buttons.getChildren().add(ok);
        buttons.getChildren().add(cancel);

        win.getChildren().add(message);
        win.getChildren().add(buttons);
        win.setAlignment(Pos.CENTER);
        Stage b = new Stage();
        b.initStyle(StageStyle.UNDECORATED);
        b.setScene(scene);
        b.show();
        cancel.setOnMouseClicked(event -> b.close());
        ok.setOnMouseClicked(event -> {
            eventHandler.handle(event);
            b.close();
        });
    }

    private void loadPlayerInformation() {
        player = Player.getInstance();
        nickNameLabel.setText(player.getName());
        expBar.setProgress(player.getExperience());
        hpBar.setProgress(player.getStats().getHits());
        statsTextArea.setText(player.toString());
        skillsSetup();

    }

    private void skillsSetup() {

        skillImages = new LinkedList<>();
        skillImages.add(skill1);
        skillImages.add(skill2);
        skillImages.add(skill3);
        skillImages.add(skill4);
        skillImages.add(skill5);
        skillImages.forEach(el -> el.setImage(null));

        skillGroup.getChildren()
                .forEach(el -> el.setOnMouseClicked(event -> chooseSkill(el.getId())));

        for (int i = 0; i < player.getSkills().size(); i++) {
            Tooltip tooltip = new Tooltip();
            tooltip.setPrefSize(200, 150);
            Skill skill = player.getSkills().get(i);
            skillImages.get(i).setImage(new Image("/assets/skills/" + skill.getName() + ".png"));
            skillImages.get(i).setOnMouseClicked(event -> {
                peekedSkill = skill.getId();
                System.out.println("peeked skill #" + skill.getId() + " = " + skill.getName());

            });
            tooltip.setText(skill.toString());
            Tooltip.install(skillImages.get(i), tooltip);
        }
    }

    private void chooseSkill(String skillId) {
        skillGroup.getChildren()
                .filtered(el -> el.getStyleClass().contains("border"))
                .forEach(el -> el.setStyle(""));

        skillGroup.getChildren()
                .filtered(el -> el.getStyleClass().contains("border"))
                .filtered(el -> el.getId().equalsIgnoreCase(skillId))
                .forEach(el -> {
                    el.setStyle("-fx-border-color:red;  -fx-border-width : 3;");

                });

    }


    private void sendChatMessage() {
        if (!messageInput.getText().isEmpty()) {
            System.out.println(messageInput.getText());
            String message = JSON.message(messageInput.getText());
            System.out.println("message = " + message);
            server.sendMessageToServer(message);
            messageInput.setText("");
        }
    }


    private void loadFxmlData() {
        assert cardTable != null : "fx:id=\"cardTable\" was not injected: check your FXML file 'main_v2.fxml'.";
        assert enemyCard != null : "fx:id=\"enemyCard\" was not injected: check your FXML file 'main_v2.fxml'.";
        assert carriedPane != null : "fx:id=\"carriedPane\" was not injected: check your FXML file 'main_v2.fxml'.";
        assert wornPane != null : "fx:id=\"wornPane\" was not injected: check your FXML file 'main_v2.fxml'.";
        assert nearPane != null : "fx:id=\"nearPane\" was not injected: check your FXML file 'main_v2.fxml'.";
        assert mapPane != null : "fx:id=\"mapPane\" was not injected: check your FXML file 'main_v2.fxml'.";
        assert chatPane != null : "fx:id=\"chatPane\" was not injected: check your FXML file 'main_v2.fxml'.";
        assert messageInput != null : "fx:id=\"messageInput\" was not injected: check your FXML file 'main_v2.fxml'.";
        assert sendButton != null : "fx:id=\"sendButton\" was not injected: check your FXML file 'main_v2.fxml'.";
        assert directMessagePane != null : "fx:id=\"directMessagePane\" was not injected: check your FXML file 'main_v2.fxml'.";
        assert directMessageInput != null : "fx:id=\"directMessageInput\" was not injected: check your FXML file 'main_v2.fxml'.";
        assert bt1 != null : "fx:id=\"bt1\" was not injected: check your FXML file 'main_v2.fxml'.";
        assert settingsBt != null : "fx:id=\"settingsBt\" was not injected: check your FXML file 'main_v2.fxml'.";
        assert bt3 != null : "fx:id=\"bt3\" was not injected: check your FXML file 'main_v2.fxml'.";
        assert bt4 != null : "fx:id=\"bt4\" was not injected: check your FXML file 'main_v2.fxml'.";
        assert exitButton != null : "fx:id=\"exitButton\" was not injected: check your FXML file 'main_v2.fxml'.";
        assert locationName != null : "fx:id=\"locationName\" was not injected: check your FXML file 'main_v2.fxml'.";
        assert attackButton != null : "fx:id=\"attackButton\" was not injected: check your FXML file 'main_v2.fxml'.";
        assert skill1 != null : "fx:id=\"skill1\" was not injected: check your FXML file 'main_v2.fxml'.";
        assert skill2 != null : "fx:id=\"skill2\" was not injected: check your FXML file 'main_v2.fxml'.";
        assert skill3 != null : "fx:id=\"skill3\" was not injected: check your FXML file 'main_v2.fxml'.";
        assert skill4 != null : "fx:id=\"skill4\" was not injected: check your FXML file 'main_v2.fxml'.";
        assert skill5 != null : "fx:id=\"skill5\" was not injected: check your FXML file 'main_v2.fxml'.";
        assert skillGroup != null : "fx:id=\"skillGroup\" was not injected: check your FXML file 'main_v2.fxml'.";
        assert avatarIcon != null : "fx:id=\"avatarIcon\" was not injected: check your FXML file 'main_v2.fxml'.";
        assert nickNameLabel != null : "fx:id=\"nickNameLabel\" was not injected: check your FXML file 'main_v2.fxml'.";
        assert hpBar != null : "fx:id=\"hpBar\" was not injected: check your FXML file 'main_v2.fxml'.";
        assert expBar != null : "fx:id=\"expBar\" was not injected: check your FXML file 'main_v2.fxml'.";
        assert statsTextArea != null : "fx:id=\"statsTextArea\" was not injected: check your FXML file 'main_v2.fxml'.";
        assert musicVolume != null : "fx:id=\"musicVolume\" was not injected: check your FXML file 'main_v2.fxml'.";

    }

    private void buttonsSetUp() {
        exitButton.setOnMouseClicked(event -> {
            try {
                LoginWrapper.getCurrentStage().close();
                SignLogic.getSignLogic().closeAll();
            } catch (Exception ex) {
                System.err.println("Close problem occurred");
                ex.printStackTrace();
            } finally {
                System.exit(0);
            }
        });

        attackButton.setOnMouseClicked(event -> attack());
        sendButton.setOnMouseClicked(event -> sendChatMessage());
    }


    @SneakyThrows
    public synchronized void getDungeonSkeleton() {

        server.sendMessageToServer(JSON.getDungeonSkeleton());
        wait(1500);
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
//        System.out.println("====> "+Container.getDungeonList().toString());
        battleController = new BattleController(player, Container.getDungeonList(), cardTable, mapPane);

    }

    public void attack() {
        System.out.println("peeked skill =  " + peekedSkill);
        System.out.println(battleController.getChosenEnemyCard() == null);
        if (peekedSkill <= 0 && battleController.getChosenEnemyCard() == null) {
            System.out.println("You have to peek skill and choose enemy!");
            return;
        }

        startBattle();

    }

    private synchronized void startBattle() {
        server.sendMessageToServer(JSON.startBattle(peekedSkill, battleController.getChosenEnemyCard().getEnemy()));
        notifyAll();
    }

}

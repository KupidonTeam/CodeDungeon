package KupidonTeam.gui;

import KupidonTeam.characters.classes.skills.Skill;
import KupidonTeam.controllers.BattleController;
import KupidonTeam.items.Armor;
import KupidonTeam.items.Food;
import KupidonTeam.items.Weapon;
import KupidonTeam.locations.Dungeon;
import KupidonTeam.login.SignLogic;
import KupidonTeam.player.Inventory;
import KupidonTeam.player.Player;
import KupidonTeam.server.Connection;
import KupidonTeam.utils.Container;
import KupidonTeam.utils.JSON;
import KupidonTeam.utils.SoundPlayer;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.SneakyThrows;

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
    private AnchorPane mainPane;

    @FXML
    private FlowPane inventoryPane;

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
    private FlowPane hpPane;

    @FXML
    private Group skillGroup;

    @FXML
    private ImageView avatarIcon;

    @FXML
    private Label nickNameLabel;


    @FXML
    private AnchorPane enemyCard;

    @FXML
    private FlowPane cardTable;

    @FXML
    private Button attackButton;

    @FXML
    private Slider musicVolume;

    @FXML
    private FlowPane exPane;

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
            battleController.getLoot(this);
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
        musicVolume.setMin(0);
        musicVolume.setMax(1);
        musicVolume.setOnMouseClicked(event -> Container.setSoundVolume(musicVolume.getValue()));
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
        Container.setChatPane(chatPane);
        choosePaneMenu();
    }

    //Примерный тест инвенторя
    public void initInventory() {
        inventoryPane.getChildren().clear();
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
            itemImage.setFitHeight(64);
            itemImage.setFitWidth(64);

            if (item instanceof Armor) {
                System.out.println("ARMOR = " + item.getName());

                itemImage.setImage(new Image("/assets/armor/" + item.getName() + ".png"));
                itemLine.getChildren().add(itemImage);
                tooltip = new Tooltip(item.toString());
                Tooltip.install(itemImage, tooltip);
                useBt.setText("Drop");
                useBt.setOnMouseClicked(event -> {
                    player.drop(item);
                    initInventory();
                });
                itemLine.getChildren().add(useBt);
                inventoryPane.getChildren().add(itemLine);
            }
            if (item instanceof Weapon) {
                itemImage.setImage(new Image("/assets/weapons/" + item.getName() + ".png"));
                itemLine.getChildren().add(itemImage);
                ;
                tooltip = new Tooltip(item.toString());
                Tooltip.install(itemImage, tooltip);
                useBt.setText("Drop");
                useBt.setOnMouseClicked(event -> {
                    player.drop(item);
                    initInventory();
                });
                itemLine.getChildren().add(useBt);
                inventoryPane.getChildren().add(itemLine);
                System.out.println("WEAPOn");
            }
            if (item instanceof Food) {
                itemImage.setImage(new Image("/assets/food/" + item.getName() + ".png"));
                itemLine.getChildren().add(itemImage);
                tooltip = new Tooltip(item.toString());
                Tooltip.install(itemImage, tooltip);
                useBt.setText("Drop");
                useBt.setOnMouseClicked(event -> {
                    player.heal(item);
                    loadPlayerInformation();
                });
                itemLine.getChildren().add(useBt);
                inventoryPane.getChildren().add(itemLine);
            }


        });


    }


    //я просто проверял работу
    public void messageDialog(String messageText) {

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
//            eventHandler.handle(event);
            b.close();
        });
    }

    private void loadPlayerInformation() {
        player = Player.getInstance();
        nickNameLabel.setText(player.getName());
        Label exp = new Label(player.getExperience() + "");

        exp.setTextFill(Color.WHITE);
        exPane.getChildren().clear();
        exPane.getChildren().add(exp);
        statsTextArea.setText(player.toString());
        Label hp = new Label(player.getStats().getHits() + " %");
        hp.setTextFill(Color.WHITE);
        hpPane.getChildren().clear();

        hpPane.getChildren().add(hp);
        avatarIcon.setImage(player.getAvatarIcon());
        initInventory();
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
        assert mainPane != null : "fx:id=\"mainPane\" was not injected: check your FXML file 'main_v2.fxml'.";
        assert cardTable != null : "fx:id=\"cardTable\" was not injected: check your FXML file 'main_v2.fxml'.";
        assert enemyCard != null : "fx:id=\"enemyCard\" was not injected: check your FXML file 'main_v2.fxml'.";
        assert inventoryPane != null : "fx:id=\"carriedPane\" was not injected: check your FXML file 'main_v2.fxml'.";
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
        assert hpPane != null : "fx:id=\"hpPane\" was not injected: check your FXML file 'main_v2.fxml'.";
        assert exPane != null : "fx:id=\"exPane\" was not injected: check your FXML file 'main_v2.fxml'.";
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
        wait(2000);
//        System.out.println("====> "+Container.getDungeonList().toString());
        battleController = BattleController.getInstance();
        battleController.setBattleController(this, player, Container.getDungeonList(), cardTable, mapPane, hpPane);
    }

    public void attack() {
        System.out.println("peeked skill =  " + peekedSkill);
        if (peekedSkill <= 0 && battleController.getChosenEnemyCard() == null) {
            System.out.println("You have to peek skill and choose enemy!");
            return;
        }

        startBattle();

    }

    private synchronized void startBattle() {
        battleController = BattleController.getInstance();
        server.sendMessageToServer(JSON.startBattle(peekedSkill, battleController.getChosenEnemyCard().getEnemy()));
        notifyAll();
    }

    public void cleanPeekedSkill() {
        peekedSkill = -1;
        skillGroup.getChildren().forEach(el -> el.setStyle(""));
    }

    public void choosePaneMenu() {
        ImageView shopImg = new ImageView("/assets/weapons/Coin.png");
        ImageView dungeonImg = new ImageView("/assets/weapons/Sword.png");
        ImageView pvpImg = new ImageView("/assets/weapons/Short Bow.png");

        FlowPane shopPane = new FlowPane();
        shopPane.setAlignment(Pos.CENTER);
        Label shopLabel = new Label("Shop");
        shopLabel.setStyle("-fx-text-fill: #816d64;");
        shopLabel.setFont(new Font("Century", 16));
        VBox shopBox = new VBox();
        shopBox.setAlignment(Pos.CENTER);
        shopBox.getChildren().addAll(shopLabel, shopImg);
        shopPane.getChildren().add(shopBox);
//        shopPane.getChildren().addAll(shopLabel, shopImg);
        shopPane.setStyle("" +
                "-fx-background-color:  #201b1b;" +
                "-fx-border-color :  #8e7c74;" +
                "-fx-border-width : 2 1 2 2;");


        FlowPane dungeonPane = new FlowPane();
        dungeonPane.setAlignment(Pos.CENTER);
        Label dungeonLabel = new Label("Dungeon");
        dungeonLabel.setStyle("-fx-text-fill: #816d64;");
        dungeonLabel.setFont(new Font("Century", 16));
        dungeonPane.getChildren().addAll(dungeonLabel, dungeonImg);
        dungeonPane.setStyle("" +
                "-fx-background-color:  #201b1b;" +
                "-fx-border-color :  #8e7c74;" +
                "-fx-border-width : 2 1 2 1;");

        FlowPane pvpPane = new FlowPane();
        pvpPane.setAlignment(Pos.CENTER);
        Label pvpLabel = new Label("PvP");
        pvpLabel.setStyle("-fx-text-fill: #816d64;");
        pvpLabel.setFont(new Font("Century", 16));
        pvpPane.getChildren().addAll(pvpLabel, pvpImg);
        pvpPane.setStyle("" +
                "-fx-background-color:  #201b1b;" +
                "-fx-border-color :  #8e7c74;" +
                "-fx-border-width : 2 2 2 1;");

        Glow glow = new Glow(0.9);
        SoundPlayer soundPlayer = new SoundPlayer();
        shopPane.setOnMouseEntered(event -> {
            shopPane.setEffect(glow);
            soundPlayer.shop();
        });
        shopPane.setOnMouseExited(event -> shopPane.setEffect(null));

        dungeonPane.setOnMouseEntered(event -> {
            dungeonPane.setEffect(glow);
            soundPlayer.dungeon();
        });
        dungeonPane.setOnMouseExited(event -> dungeonPane.setEffect(null));

        pvpPane.setOnMouseEntered(event -> {
            pvpPane.setEffect(glow);
            soundPlayer.pvp();
        });
        pvpPane.setOnMouseExited(event -> pvpPane.setEffect(null));

        shopPane.setPrefWidth(220);
        shopPane.setPrefHeight(460);

        dungeonPane.setPrefWidth(220);
        dungeonPane.setPrefHeight(460);

        pvpPane.setPrefWidth(220);
        pvpPane.setPrefHeight(460);

        HBox hBox = new HBox();
        hBox.getChildren().addAll(shopPane, dungeonPane, pvpPane);
        FlowPane pane = new FlowPane();

        pane.getChildren().addAll(hBox);
        cardTable.getChildren().add(hBox);
    }

    public void update() {
        initInventory();
        choosePaneMenu();
    }


}

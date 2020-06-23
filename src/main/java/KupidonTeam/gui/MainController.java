package KupidonTeam.gui;

import KupidonTeam.characters.classes.skills.Skill;
import KupidonTeam.controllers.BattleController;
import KupidonTeam.items.Armor;
import KupidonTeam.items.Food;
import KupidonTeam.items.Weapon;
import KupidonTeam.login.SignLogic;
import KupidonTeam.player.Player;
import KupidonTeam.server.Connection;
import KupidonTeam.utils.Container;
import KupidonTeam.utils.JSON;
import KupidonTeam.utils.SoundPlayer;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.SneakyThrows;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Stream;

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
    private VBox statsPane;

    @FXML
    private TextField messageInput;

    @FXML
    private Button sendButton;

    @FXML
    private TextArea directMessagePane;

    @FXML
    private TextField directMessageInput;

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
    private Button mainMenuBt;

    @FXML
    private FlowPane hpPane;


    @FXML
    private ImageView avatarIcon;

    @FXML
    private Label nickNameLabel;

    @FXML
    private Label gold;

    @FXML
    private AnchorPane enemyCard;

    @FXML
    private FlowPane cardTable;

    @FXML
    private AnchorPane attack;


    @FXML
    private FlowPane exPane;

    private double xOffset = 0;
    private double yOffset = 0;
    private Connection server;
    private Player player;
    private Stage dialogStage;
    private List<ImageView> skillImages;
    private BattleController battleController;
    private int peekedSkill;
    private SoundPlayer soundPlayer;
    private ChooseMenuController chooseMenuController;
    private boolean isDungeon;

    @FXML
    void initialize() {
        loadFxmlData();
        setUp();
        setDraggable();
        loadPlayerInformation();
        setPlayerStats();
        List<EnemyCard> enemyCards = new LinkedList<>();
        enemyCards.add(new EnemyCard());

    }

    private void setUp() {
        soundPlayer = new SoundPlayer();
        AudioClip audioClip = soundPlayer.mainTheme();
        audioClip.play();
        server = Connection.getConnection();


        chooseMenuController = new ChooseMenuController(this, cardTable);
        buttonsSetUp();
        panesSetUp();
        server.setChatArea(chatPane);
        server.setCardTable(cardTable);

    }

    private void panesSetUp() {

        chatPane.setText("");
        chatPane.setWrapText(true);
        chatPane.setPrefColumnCount(30);
        chatPane.setEditable(false);

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
        chooseMenuController.choosePaneMenu();
    }

    //Примерный тест инвенторя
    private void initInventory() {
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
                useBt.setText("Heal");
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
        ImageView okImage = new ImageView(new Image(getClass().getResourceAsStream("/assets/GUI_Parts/Gui_parts/button2.png")));
        ImageView cancelImage = new ImageView(new Image(getClass().getResourceAsStream("/assets/GUI_Parts/Gui_parts/button_ready_on.png")));
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
            b.close();
        });
    }

    private void loadPlayerInformation() {
        hpPane.getChildren().clear();
        exPane.getChildren().clear();
        gold.setText("");
        player = Player.getInstance();
        nickNameLabel.setText(player.getName());
        Label exp = new Label(player.getLvl() + "");
        exp.setTextFill(Color.rgb(142, 124, 116));
        exPane.getChildren().add(exp);
        gold.setText("" + player.getGold());

        Label hp = new Label(player.getStats().getHits() + " %");
        hp.setTextFill(Color.rgb(142, 124, 116));
        hpPane.getChildren().add(hp);
        avatarIcon.setImage(player.getAvatarIcon());
        initInventory();
        skillsSetup();
    }

    @SneakyThrows
    private void skillsSetup() {
        skillImages = new LinkedList<>();
        skillImages.add(skill1);
        skillImages.add(skill2);
        skillImages.add(skill3);
        skillImages.add(skill4);


        for (int i = 0; i < player.getSkills().size() - 1; i++) {
            Tooltip tooltip = new Tooltip();
            tooltip.setPrefSize(200, 150);
            Skill skill = player.getSkills().get(i);
            System.out.println(skill.getName());
            skillImages.get(i).setImage(new Image(MainController.class.getResourceAsStream("/assets/skills/" + skill.getName().toLowerCase() + ".png")));
            int finalI = i;
            skillImages.get(i).setOnMouseClicked(event -> {
                skillImages.forEach(el -> el.setEffect(new Glow(0)));
                peekedSkill = skill.getId();
                skillImages.get(finalI).setEffect(new Glow(10));
                System.out.println("peeked skill #" + skill.getId() + " = " + skill.getName());

            });
            tooltip.setText(skill.toString());
            Tooltip.install(skillImages.get(i), tooltip);
        }
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
        assert inventoryPane != null : "fx:id=\"inventoryPane\" was not injected: check your FXML file 'main_v2.fxml'.";
        assert mapPane != null : "fx:id=\"mapPane\" was not injected: check your FXML file 'main_v2.fxml'.";
        assert chatPane != null : "fx:id=\"chatPane\" was not injected: check your FXML file 'main_v2.fxml'.";
        assert messageInput != null : "fx:id=\"messageInput\" was not injected: check your FXML file 'main_v2.fxml'.";
        assert sendButton != null : "fx:id=\"sendButton\" was not injected: check your FXML file 'main_v2.fxml'.";
        assert directMessagePane != null : "fx:id=\"directMessagePane\" was not injected: check your FXML file 'main_v2.fxml'.";
        assert directMessageInput != null : "fx:id=\"directMessageInput\" was not injected: check your FXML file 'main_v2.fxml'.";
        assert sendButton != null : "fx:id=\"sendButton\" was not injected: check your FXML file 'main_v2.fxml'.";
        assert mainMenuBt != null : "fx:id=\"mainMenuBt\" was not injected: check your FXML file 'main_v2.fxml'.";
        assert statsPane != null : "fx:id=\"statsPane\" was not injected: check your FXML file 'main_v2.fxml'.";
        assert gold != null : "fx:id=\"gold\" was not injected: check your FXML file 'main_v2.fxml'.";
        assert exitButton != null : "fx:id=\"exitButton\" was not injected: check your FXML file 'main_v2.fxml'.";
        assert locationName != null : "fx:id=\"locationName\" was not injected: check your FXML file 'main_v2.fxml'.";
        assert skill1 != null : "fx:id=\"skill1\" was not injected: check your FXML file 'main_v2.fxml'.";
        assert skill2 != null : "fx:id=\"skill2\" was not injected: check your FXML file 'main_v2.fxml'.";
        assert skill4 != null : "fx:id=\"skill4\" was not injected: check your FXML file 'main_v2.fxml'.";
        assert skill3 != null : "fx:id=\"skill3\" was not injected: check your FXML file 'main_v2.fxml'.";
        assert attack != null : "fx:id=\"attack\" was not injected: check your FXML file 'main_v2.fxml'.";
        assert avatarIcon != null : "fx:id=\"avatarIcon\" was not injected: check your FXML file 'main_v2.fxml'.";
        assert nickNameLabel != null : "fx:id=\"nickNameLabel\" was not injected: check your FXML file 'main_v2.fxml'.";
        assert hpPane != null : "fx:id=\"hpPane\" was not injected: check your FXML file 'main_v2.fxml'.";
        assert exPane != null : "fx:id=\"exPane\" was not injected: check your FXML file 'main_v2.fxml'.";
        assert cardTable != null : "fx:id=\"cardTable\" was not injected: check your FXML file 'main_v2.fxml'.";


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

        attack.setOnMouseClicked(event -> attack());
        sendButton.setOnMouseClicked(event -> sendChatMessage());
        mainMenuBt.setOnMouseClicked(event -> {
            if (isDungeon) {
                battleController.getLoot(this);
                isDungeon = false;
            } else {
                update(true);
            }

        });

    }


    @SneakyThrows
    public synchronized void getDungeonSkeleton() {
        server.sendMessageToServer(JSON.getDungeonSkeleton());
        wait(2000);
//        System.out.println("====> "+Container.getDungeonList().toString());
        battleController = BattleController.getInstance();
        battleController.setBattleController(this, player, Container.getDungeonList(), cardTable, mapPane, hpPane);
        isDungeon = true;
    }

    public void attack() {
        System.out.println("peeked skill =  " + peekedSkill);
        System.out.println("chosen enemy = " + battleController.getChosenEnemyCard());
        if (peekedSkill <= 0 || BattleController.getInstance().getChosenEnemyCard() == null) {
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

    public void cleanPeeked() {
        cardTable.getChildren().clear();
        peekedSkill = -1;

    }


    public void update(boolean menu) {
        cardTable.getChildren().clear();
        mapPane.getChildren().clear();
        loadPlayerInformation();
        initInventory();
        if (menu) {
            chooseMenuController.choosePaneMenu();
        }

    }

    private void setPlayerStats() {
        Stream.of(player.getStats().getStatsData())
                .forEach(el -> {
                    Label label = new Label("" + el);
                    label.setTextFill(Color.WHITE);
                    label.setFont(new Font("Arial", 20));
                    statsPane.getChildren().add(label);
                });
    }

    public void openShop() {
        FlowPane flowPane = new FlowPane();
        flowPane.setStyle("-fx-background-color : #201b1b;");
        Button buyButton = new Button();
        Button sellButton = new Button();
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(flowPane);
        scrollPane.setMaxHeight(450);
        scrollPane.setStyle("-fx-background-color : #201b1b");
        cardTable.getChildren().add(flowPane);
        ShopController shopController = new ShopController(this, cardTable);
        shopController.loadGoods();
    }

    private void setDraggable() {
        Stage primaryStage = LoginWrapper.getCurrentStage();
        mainPane.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = primaryStage.getX() - event.getScreenX();
                yOffset = primaryStage.getY() - event.getScreenY();
            }
        });

        mainPane.setOnMouseDragged(event ->
        {
            primaryStage.setX(event.getScreenX() + xOffset);
            primaryStage.setY(event.getScreenY() + yOffset);
        });


    }

    public void setIsDungeon(boolean value) {
        isDungeon = value;
    }
}

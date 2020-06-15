package KupidonTeam.gui;

import KupidonTeam.characters.classes.skills.Skill;
import KupidonTeam.login.SignLogic;
import KupidonTeam.player.Player;
import KupidonTeam.server.Connection;
import KupidonTeam.utils.JSON;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
    private ImageView mapPane;

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
    private Button bt2;

    @FXML
    private Button bt3;

    @FXML
    private Button bt4;

    @FXML
    private Button exitButton;

    @FXML
    private Label locationName;

    @FXML
    private ImageView downArrowButton;

    @FXML
    private ImageView upArrowButton;

    @FXML
    private ImageView leftArrowButton;

    @FXML
    private ImageView rightArrowButton;

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
    private ProgressBar expBar;

    private Connection server;
    private Player player;
    private Stage dialogStage;
    private List<ImageView> skillImages;
    private int peekedSkill;

    @FXML
    void initialize() {
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
        assert bt2 != null : "fx:id=\"bt2\" was not injected: check your FXML file 'main_v2.fxml'.";
        assert bt3 != null : "fx:id=\"bt3\" was not injected: check your FXML file 'main_v2.fxml'.";
        assert bt4 != null : "fx:id=\"bt4\" was not injected: check your FXML file 'main_v2.fxml'.";
        assert exitButton != null : "fx:id=\"exitButton\" was not injected: check your FXML file 'main_v2.fxml'.";
        assert locationName != null : "fx:id=\"locationName\" was not injected: check your FXML file 'main_v2.fxml'.";
        assert downArrowButton != null : "fx:id=\"downArrowButton\" was not injected: check your FXML file 'main_v2.fxml'.";
        assert upArrowButton != null : "fx:id=\"upArrowButton\" was not injected: check your FXML file 'main_v2.fxml'.";
        assert leftArrowButton != null : "fx:id=\"leftArrowButton\" was not injected: check your FXML file 'main_v2.fxml'.";
        assert rightArrowButton != null : "fx:id=\"rightArrowButton\" was not injected: check your FXML file 'main_v2.fxml'.";
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

        //TODO добавить сохранение прогресса перед закрытием
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


        sendButton.setOnMouseClicked(event -> sendChatMessage());
        messageInput.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                sendChatMessage();
            }
        });

        bt2.setOnMouseClicked(event -> defeatDialog("Connecting to server.\nPlease wait..."));

        setUp();
        loadPlayerInformation();

    }

    private void setUp() {
        server = Connection.getConnection();
        dialogStage = new Stage();
        dialogStage.initStyle(StageStyle.UNDECORATED);
        setUpTextPanes();
        server.setChatArea(chatPane);

    }

    private void setUpTextPanes() {
        chatPane.setText("");
        chatPane.setWrapText(true);
        chatPane.setPrefColumnCount(30);
        chatPane.setEditable(false);
        statsTextArea.setWrapText(true);
        statsTextArea.setPrefColumnCount(30);
        statsTextArea.setPadding(new Insets(5));

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

    private void initEnemies() {

    }

    //я просто проверял работу
    private void defeatDialog(String messageText, String... button) {
        Text message = new Text(messageText);
        message.setFill(Color.WHITE);
        message.setStyle(
                "-fx-font: 18 arial;" +
                        "-fx-text-alignment : center;");

        VBox win = new VBox();
        HBox buttons = new HBox();
        win.setStyle(
                "-fx-background-image : url(/assets/GUI_Parts/Gui_parts/barmid_ready.png);" +
                        "-fx-background-size : 600 300;");

        win.setPrefSize(600, 300);
        win.setSpacing(20);
        Scene scene = new Scene(win);
        ImageView closeImage = new ImageView("/assets/GUI_Parts/Gui_parts/button_ready_on.png");
        closeImage.setFitHeight(40);
        closeImage.setFitWidth(80);
        StackPane closeBt = new StackPane();
        closeBt.getChildren().add(closeImage);
        Label closeWindow = new Label("Abort");
        closeWindow.setTextFill(Color.BURLYWOOD);
        closeBt.getChildren().add(closeWindow);


        StackPane ok = new StackPane();
        ok.getChildren().add(closeImage);
        Label okWin = new Label("Aborasdasdasdasdasdasdasdt");
        okWin.setTextFill(Color.BURLYWOOD);
        ok.getChildren().add(okWin);

        ProgressIndicator progressIndicator = new ProgressIndicator();
        progressIndicator.setStyle("-fx-progress-color : red;");
        win.getChildren().add(message);
        win.getChildren().add(progressIndicator);
        win.getChildren().add(closeBt);
        win.getChildren().add(ok);
        win.setAlignment(Pos.CENTER);
        Stage b = new Stage();
        b.initStyle(StageStyle.UNDECORATED);
        b.setScene(scene);
        b.show();
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
        bt1.setTooltip(new Tooltip("adasdasdasdasdasdasd"));
        skillImages = new LinkedList<>();
        skillImages.add(skill1);
        skillImages.add(skill2);
        skillImages.add(skill3);
        skillImages.add(skill4);
        skillImages.add(skill5);

        skillGroup.getChildren()
                .forEach(el -> el.setOnMouseClicked(event -> chooseSkill(el.getId())));

        for (int i = 0; i < player.getSkills().size(); i++) {
            Tooltip tooltip = new Tooltip();
            tooltip.setPrefSize(200, 150);
            Skill skill = player.getSkills().get(i);
            skillImages.get(i).setImage(new Image("/assets/skills/" + skill.getName() + ".png"));
            skillImages.get(i).setOnMouseClicked(event -> {
                peekedSkill = skill.getId();
                System.out.println("peeked skill = " + skill.getName());

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
                .forEach(el -> el.setStyle("-fx-border-color:red;  -fx-border-width : 3;"));
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

}

package KupidonTeam.gui;

import java.net.URL;
import java.util.ResourceBundle;

import KupidonTeam.login.SignLogic;
import KupidonTeam.player.Player;
import KupidonTeam.server.Connection;
import KupidonTeam.utils.JSON;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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
    private ImageView avatarIcon;

    @FXML
    private Label nickNameLabel;

    @FXML
    private ProgressBar hpBar;

    @FXML
    private ProgressBar expBar;

    private Connection server;
    private Player player;

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
        assert avatarIcon != null : "fx:id=\"avatarIcon\" was not injected: check your FXML file 'main_v2.fxml'.";
        assert nickNameLabel != null : "fx:id=\"nickNameLabel\" was not injected: check your FXML file 'main_v2.fxml'.";
        assert hpBar != null : "fx:id=\"hpBar\" was not injected: check your FXML file 'main_v2.fxml'.";
        assert expBar != null : "fx:id=\"expBar\" was not injected: check your FXML file 'main_v2.fxml'.";

        setUp();

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

        sendButton.setOnMouseClicked(event -> {
            if (!messageInput.getText().isEmpty()) {
                String message = JSON.message(messageInput.getText(), nickNameLabel.getText());
                server.sendMessageToServer(messageInput.getText());
                messageInput.setText("");
            }
        });

        bt1.setOnMouseClicked(event -> initInventory());
        bt2.setOnMouseClicked(event -> defeatDialog());
    }

    private void setUp() {
        //server = Connection.getConnection();
        // nickNameLabel.setText(player.getName());
        setUpTextPanes();
    }

    private void setUpTextPanes() {
        chatPane.setWrapText(true);
        chatPane.setPrefColumnCount(30);
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
    private void defeatDialog() {
        Text a = new Text("HEllo!");
        FlowPane win = new FlowPane();
        Scene scene = new Scene(win);
        scene.setFill(Color.TRANSPARENT);

        Dialog dialog = new Dialog();
        dialog.getDialogPane().setStyle("-fx-background-color: transparent;");
        win.getChildren().add(new Text("Hi"));
        Button close = new Button("close");
        close.setOnMouseClicked(event -> {
            scene.getWindow().hide();
        });
        win.getChildren().add(close);
        win.setAlignment(Pos.CENTER);
        Stage b = new Stage();
        b.setScene(scene);
        b.show();
    }

    private void serverLisner() {
        new Thread(() -> {
            if (!server.getBuffer().isEmpty()) {
                chatPane.appendText(server.getBuffer() + "\n");
            }
        });

    }
}

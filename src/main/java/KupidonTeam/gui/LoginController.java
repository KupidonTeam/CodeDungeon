package KupidonTeam.gui;

import KupidonTeam.login.SignLogic;
import KupidonTeam.player.Player;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController {

    @FXML
    private AnchorPane MainPane;

    @FXML
    private Pane pane;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label usernameLabel;

    @FXML
    private Label passwordLabel;

    @FXML
    private ImageView closeButton;

    @FXML
    private Label loginButton;

    @FXML
    private Label signUpLink;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    private Paint labelColor;

    public Stage dialogStage;

    private Player player;

    @FXML
    void initialize() {
        assert MainPane != null : "fx:id=\"MainPane\" was not injected: check your FXML file 'login.fxml'.";
        assert pane != null : "fx:id=\"pane\" was not injected: check your FXML file 'login.fxml'.";
        assert usernameLabel != null : "fx:id=\"usernameLabel\" was not injected: check your FXML file 'login.fxml'.";
        assert passwordLabel != null : "fx:id=\"passwordLabel\" was not injected: check your FXML file 'login.fxml'.";
        assert closeButton != null : "fx:id=\"closeButton\" was not injected: check your FXML file 'login.fxml'.";
        assert loginButton != null : "fx:id=\"loginButton\" was not injected: check your FXML file 'login.fxml'.";
        assert signUpLink != null : "fx:id=\"signUpLink\" was not injected: check your FXML file 'login.fxml'.";
        assert usernameField != null : "fx:id=\"usernameField\" was not injected: check your FXML file 'login.fxml'.";
        assert passwordField != null : "fx:id=\"passwordField\" was not injected: check your FXML file 'login.fxml'.";

        dialogStage = new Stage();
        dialogStage.initStyle(StageStyle.UNDECORATED);
        labelColor = usernameLabel.getTextFill();

        loginButton.setCursor(Cursor.HAND);
        loginButton.setOnMouseClicked(ev -> {
            login();
        });
        usernameField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                login();
            }
        });
        passwordField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                login();
            }
        });

        closeButton.setCursor(Cursor.HAND);
        closeButton.setOnMouseClicked(ev -> {
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

        signUpLink.setCursor(Cursor.HAND);
        signUpLink.setOnMouseClicked(ev -> {

            switchToSignUp();

        });
    }


    @SneakyThrows
    private void loginCheck() {

        updateLabels();
        String username = usernameField.getText();
        String password = passwordField.getText();

        //System.out.println(username.isEmpty());
        if (username.isEmpty()) {
            usernameLabel.setText("Username - REQUIRED");
            usernameLabel.setTextFill(Color.RED);
        } else if (password.isEmpty()) {
            passwordLabel.setText("Password - REQUIRED");
            passwordLabel.setTextFill(Color.RED);
        } else {
            SignLogic signLogic = SignLogic.getSignLogic();
            if (signLogic.checkUserName(username)) {
                boolean loginStatus = signLogic.serverAuthorization(username, password);
                if (loginStatus) {
                    loadMainPane();
                    dialogStage.close();

                } else {
                    passwordLabel.setText("Password - WRONG");
                    passwordLabel.setTextFill(Color.RED);
                }
            } else {
                usernameLabel.setText("Username - no such user");
                usernameLabel.setTextFill(Color.RED);
            }

        }
        dialogStage.close();

    }

    private void loadSignUp() {
        String path = "/fxml/signup_final.fxml";
        LoginWrapper.getCurrentStage().setScene(SignUpWrapper.getScene());
        Parent parent;
        FXMLLoader loader = new FXMLLoader();
        try {
            parent = loader.load(getClass().getResourceAsStream(path));
            Scene newScene = new Scene(parent);
            LoginWrapper.getCurrentStage().setScene(newScene);
            LoginWrapper.getCurrentStage().show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void switchToSignUp() {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(300));
        fadeTransition.setNode(MainPane);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.setOnFinished(ev -> {
            loadSignUp();
        });
        fadeTransition.play();
    }

    public void loadMainPane() {
        String path = "/fxml/main_v2.fxml";
        LoginWrapper.getCurrentStage().setScene(SignUpWrapper.getScene());
        Parent parent;
        FXMLLoader loader = new FXMLLoader();

        try {
            parent = loader.load(getClass().getResourceAsStream(path));
            Scene newScene = new Scene(parent);
            LoginWrapper.getCurrentStage().setScene(newScene);
            LoginWrapper.getCurrentStage().centerOnScreen();
            LoginWrapper.getCurrentStage().show();

        } catch (IOException e) {
            e.printStackTrace();
            SignLogic.getSignLogic().closeAll();
        }
    }

    private void updateLabels() {
        usernameLabel.setText("Username");
        usernameLabel.setTextFill(labelColor);
        passwordLabel.setText("Password");
        passwordLabel.setTextFill(labelColor);

    }

    private void loginToServerDialog() {
        Text message = new Text("Connecting to server\nPlease wait...");
        message.setFill(Color.WHITE);
        message.setStyle(
                "-fx-font: 18 century;" +
                        "-fx-text-alignment : center;");
        VBox win = new VBox();
        HBox buttons = new HBox();
        win.setStyle(
                "-fx-background-image : url(/assets/GUI_Parts/Gui_parts/barmid_ready.png);" +
                        "-fx-background-size : 600 300;");

        win.setPrefSize(600, 300);
        win.setSpacing(20);
        Scene scene = new Scene(win);
        ProgressIndicator progressIndicator = new ProgressIndicator();
        progressIndicator.setStyle("-fx-progress-color : red;");
        win.getChildren().add(message);
        win.getChildren().add(progressIndicator);

        win.setAlignment(Pos.CENTER);
        dialogStage.setScene(scene);
        dialogStage.show();
    }

    private void login() {
        loginToServerDialog();
        Platform.runLater(() -> loginCheck());
    }
}




package KupidonTeam.gui;

import KupidonTeam.login.SignLogic;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SingUpController {

    private final String loginPath = "/fxml/login.fxml";

    private final String characterCreationPath = "/fxml/create_charachter.fxml";

    @FXML
    private javafx.scene.layout.AnchorPane AnchorPane;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label usernameLabel;

    @FXML
    private Label passwordLabel;

    @FXML
    private Label confirmPasswordLabel;

    @FXML
    private Label loginLink;

    @FXML
    private ImageView closeWindowButton;

    @FXML
    private TextField userNameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private Button signUpButton;

    @FXML
    void initialize() {
        assert usernameLabel != null : "fx:id=\"usernameLabel\" was not injected: check your FXML file 'signup_final.fxml'.";
        assert passwordLabel != null : "fx:id=\"passwordLabel\" was not injected: check your FXML file 'signup_final.fxml'.";
        assert confirmPasswordLabel != null : "fx:id=\"confirmPasswordLabel\" was not injected: check your FXML file 'signup_final.fxml'.";
        assert loginLink != null : "fx:id=\"loginLink\" was not injected: check your FXML file 'signup_final.fxml'.";
        assert closeWindowButton != null : "fx:id=\"closeWindowButton\" was not injected: check your FXML file 'signup_final.fxml'.";
        assert userNameField != null : "fx:id=\"userNameField\" was not injected: check your FXML file 'signup_final.fxml'.";
        assert passwordField != null : "fx:id=\"passwordField\" was not injected: check your FXML file 'signup_final.fxml'.";
        assert confirmPasswordField != null : "fx:id=\"confirmPasswordField\" was not injected: check your FXML file 'signup_final.fxml'.";
        assert signUpButton != null : "fx:id=\"signUpButton\" was not injected: check your FXML file 'signup_final.fxml'.";


        signUpButton.setOnAction((ev) -> {
            loginCheck();

        });

        closeWindowButton.setOnMouseClicked(event -> {
            LoginWrapper.getCurrentStage().close();
            SignLogic.getSignLogic().closeAll();
        });

        loginLink.setOnMouseClicked(ev -> switchToLogin());
    }

    private void loginCheck() {
        String username = userNameField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();
        System.out.println("User : " + username);
        System.out.println("Pass : " + password);
        System.out.println(username.isEmpty());
        if (username.isEmpty()) {
            usernameLabel.setText("Password - REQUIRED");
            usernameLabel.setTextFill(Color.RED);
        } else if (password.isEmpty()) {
            passwordLabel.setText("Password - REQUIRED");
            passwordLabel.setTextFill(Color.RED);
        } else if (confirmPassword.isEmpty()) {
            confirmPasswordLabel.setText("Confirm password - REQUIRED");
            confirmPasswordLabel.setTextFill(Color.RED);
        } else if (!confirmPassword.equals(password)) {
            confirmPasswordLabel.setText("Confirm password - NOT EQUAL");
            confirmPasswordLabel.setTextFill(Color.RED);
        } else {
            SignLogic signLogic = SignLogic.getSignLogic();
            System.out.println("before check");
            System.out.println("user name = " + username);
            System.out.println("pass = " + password);
            System.out.println(signLogic.checkUserName(username));

            if (!signLogic.checkUserName(username)) {
                System.out.println("========== Логин свободен ==============");
                characterCreation(username);

            } else {
                usernameLabel.setText(usernameLabel.getText() + " - no such user");
                usernameLabel.setTextFill(Color.RED);
            }
        }
    }

    private void loadFxml(String path) {

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

    private void switchToLogin() {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(300));
        fadeTransition.setNode(AnchorPane);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.setOnFinished((event) -> {
            loadFxml(loginPath);
        });
        fadeTransition.play();
    }

    private void characterCreation(String username) {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(300));
        fadeTransition.setNode(AnchorPane);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.setOnFinished((event) -> {
            loadFxml(characterCreationPath);
        });
        fadeTransition.play();
    }


}

package KupidonTeam.controllers;

import KupidonTeam.view.LoginWrapper;
import KupidonTeam.view.SignUpWrapper;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController {

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
    private ImageView github;

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
        assert github != null : "fx:id=\"github\" was not injected: check your FXML file 'login.fxml'.";

        github.setOnMouseClicked(event -> {
            try {
                Desktop.getDesktop().browse(new URL("https://github.com/KupidonTeam/CodeDungeon").toURI());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        });

        signUpButton.setOnAction((ev) -> {
            loginCheck();

        });

        closeWindowButton.setOnMouseClicked(event -> {
            LoginWrapper.getCurrentStage().close();
            SignInController.getSignInController().closeAll();
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
            SignInController signInController = SignInController.getSignInController();
            System.out.println("before check");
            System.out.println("user name = " + username);
            System.out.println("pass = " + password);
            System.out.println(signInController.checkUserName(username));

            if (!signInController.checkUserName(username)) {
                System.out.println("========== Логин свободен ==============");
                characterCreation(username, password);
            } else {
                usernameLabel.setText(usernameLabel.getText() + " - OCCUPIED");
                usernameLabel.setTextFill(Color.RED);
            }
        }
    }

    private <T> T loadFxml(String path) {
        LoginWrapper.getCurrentStage().setScene(SignUpWrapper.getScene());
        Parent parent;
        FXMLLoader loader = new FXMLLoader();
        T controller = null;

        try {
            parent = loader.load(getClass().getResourceAsStream(path));
            controller = loader.getController();
            Scene newScene = new Scene(parent);
            LoginWrapper.getCurrentStage().setScene(newScene);
            LoginWrapper.getCurrentStage().show();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return controller;
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

    private void characterCreation(String username, String password) {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(300));
        fadeTransition.setNode(AnchorPane);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.setOnFinished((event) -> {
            CharacterCreationController characterController = loadFxml(characterCreationPath);
            characterController.setUserData(username, password);
        });
        fadeTransition.play();
    }
}

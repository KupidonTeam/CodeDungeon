package KupidonTeam.fxml;

import KupidonTeam.login.SignLogic;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.util.EventListener;
import java.util.ResourceBundle;

public class SingUpController {

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
            signUpButton.setDisable(true);
        });

        closeWindowButton.setOnMouseClicked(event -> {
            Wrapper.getCurrentStage().close();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    private void loginCheck() {
        String username = userNameField.getText();
        String password = passwordField.getText();
        System.out.println("User : " + username);
        System.out.println("Pass : " + password);
        System.out.println(username.isEmpty());
        if (username.isEmpty()) {
            usernameLabel.setText(userNameField.getText() + " - REQUIRED");
            usernameLabel.setTextFill(Color.RED);
        } else if (password.isEmpty()) {
            passwordLabel.setText(passwordField.getText() + " - REQUIRED");
            passwordLabel.setTextFill(Color.RED);
        } else {
            SignLogic signLogic = SignLogic.getSignLogic();
            System.out.println("before check");
            System.out.println("user name = " + username);
            System.out.println("pass = " + password);
            System.out.println(signLogic.checkUserName(username));
            if (signLogic.checkUserName(username)) {
                signLogic.serverAuthorization(username, password);

            } else {
                usernameLabel.setText(usernameLabel.getText() + " - no such user");
                usernameLabel.setTextFill(Color.RED);
            }
        }
    }


}

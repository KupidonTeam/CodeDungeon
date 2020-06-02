package KupidonTeam.fxml;

import KupidonTeam.login.SignLogic;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController {

    @FXML
    private AnchorPane MainPane;


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

    @FXML
    void initialize() {
        assert MainPane != null : "fx:id=\"MainPane\" was not injected: check your FXML file 'login.fxml'.";
        assert usernameLabel != null : "fx:id=\"usernameLabel\" was not injected: check your FXML file 'login.fxml'.";
        assert passwordLabel != null : "fx:id=\"passwordLabel\" was not injected: check your FXML file 'login.fxml'.";
        assert closeButton != null : "fx:id=\"closeButton\" was not injected: check your FXML file 'login.fxml'.";
        assert loginButton != null : "fx:id=\"loginButton\" was not injected: check your FXML file 'login.fxml'.";
        assert signUpLink != null : "fx:id=\"signUpLink\" was not injected: check your FXML file 'login.fxml'.";
        assert usernameField != null : "fx:id=\"usernameField\" was not injected: check your FXML file 'login.fxml'.";
        assert passwordField != null : "fx:id=\"passwordField\" was not injected: check your FXML file 'login.fxml'.";


        loginButton.setOnMouseClicked(ev -> loginCheck());
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
        signUpLink.setOnMouseClicked(ev -> {
            switchToSignUp();

        });
    }


    private void loginCheck() {
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
            //System.out.println(signLogic.checkUserName(username));
            if (signLogic.checkUserName(username)) {
                signLogic.serverAuthorization(username, password);

            } else {
                usernameLabel.setText(usernameLabel.getText() + " - no such user");
                usernameLabel.setTextFill(Color.RED);
            }
        }
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


}

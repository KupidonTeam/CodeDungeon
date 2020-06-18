package KupidonTeam.gui;

import KupidonTeam.login.SignLogic;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CharacterCreationController {

    @FXML
    private Pane mainPane;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label usernameLabel;

    @FXML
    private ImageView closeButton;

    @FXML
    private Label hits;

    @FXML
    private Label speed;

    @FXML
    private Label strength;

    @FXML
    private Label dexterity;

    @FXML
    private Label intelligence;

    @FXML
    private Label wisdom;

    @FXML
    private Label chance;

    @FXML
    private Label constitution;

    @FXML
    private Label classLabel;

    @FXML
    private RadioButton maleRadioButton;

    @FXML
    private RadioButton femaleRadioButton;

    @FXML
    private Button confirmButton;

    @FXML
    private ChoiceBox<?> raceDropDown;

    @FXML
    private Label rogueClass;

    @FXML
    private Label rangerClass;

    @FXML
    private Label paladinClass;

    @FXML
    private Label bardClass;

    @FXML
    private Label druidClass;

    @FXML
    private Label wizardClass;

    @FXML
    private ImageView avatarImage;

    @FXML
    void initialize() {
        assert mainPane != null : "fx:id=\"mainPane\" was not injected: check your FXML file 'create_charachter.fxml'.";
        assert avatarImage != null : "fx:id=\"avatarImage\" was not injected: check your FXML file 'create_charachter.fxml'.";
        assert usernameLabel != null : "fx:id=\"usernameLabel\" was not injected: check your FXML file 'create_charachter.fxml'.";
        assert closeButton != null : "fx:id=\"closeButton\" was not injected: check your FXML file 'create_charachter.fxml'.";
        assert hits != null : "fx:id=\"hits\" was not injected: check your FXML file 'create_charachter.fxml'.";
        assert speed != null : "fx:id=\"speed\" was not injected: check your FXML file 'create_charachter.fxml'.";
        assert strength != null : "fx:id=\"strength\" was not injected: check your FXML file 'create_charachter.fxml'.";
        assert dexterity != null : "fx:id=\"dexterity\" was not injected: check your FXML file 'create_charachter.fxml'.";
        assert intelligence != null : "fx:id=\"intelligence\" was not injected: check your FXML file 'create_charachter.fxml'.";
        assert wisdom != null : "fx:id=\"wisdom\" was not injected: check your FXML file 'create_charachter.fxml'.";
        assert chance != null : "fx:id=\"chance\" was not injected: check your FXML file 'create_charachter.fxml'.";
        assert constitution != null : "fx:id=\"constitution\" was not injected: check your FXML file 'create_charachter.fxml'.";
        assert classLabel != null : "fx:id=\"classLabel\" was not injected: check your FXML file 'create_charachter.fxml'.";
        assert maleRadioButton != null : "fx:id=\"maleRadioButton\" was not injected: check your FXML file 'create_charachter.fxml'.";
        assert femaleRadioButton != null : "fx:id=\"femaleRadioButton\" was not injected: check your FXML file 'create_charachter.fxml'.";
        assert confirmButton != null : "fx:id=\"confirmButton\" was not injected: check your FXML file 'create_charachter.fxml'.";
        assert raceDropDown != null : "fx:id=\"raceDropDown\" was not injected: check your FXML file 'create_charachter.fxml'.";
        assert rogueClass != null : "fx:id=\"rogueClass\" was not injected: check your FXML file 'create_charachter.fxml'.";
        assert rangerClass != null : "fx:id=\"rangerClass\" was not injected: check your FXML file 'create_charachter.fxml'.";
        assert paladinClass != null : "fx:id=\"paladinClass\" was not injected: check your FXML file 'create_charachter.fxml'.";
        assert bardClass != null : "fx:id=\"bardClass\" was not injected: check your FXML file 'create_charachter.fxml'.";
        assert druidClass != null : "fx:id=\"druidClass\" was not injected: check your FXML file 'create_charachter.fxml'.";
        assert wizardClass != null : "fx:id=\"wizardClass\" was not injected: check your FXML file 'create_charachter.fxml'.";

        mainPane.getChildren()
                .filtered(el -> el.getStyleClass().toString().contains("classes"))
                .forEach(el -> el.setOnMouseClicked(event -> chooseClass(el.getId())));

        closeButton.setOnMouseClicked(ev -> {
            try {
                LoginWrapper.getCurrentStage().close();
                SignLogic.getSignLogic().closeAll();
            } catch (Exception ex) {
                System.err.println("<!--------Close problem occurred---------!>");
                ex.printStackTrace();
            } finally {
                System.exit(0);
            }
        });

        avatarImage.setOnMouseClicked(event -> openAvatarWindow());
    }

    private void openAvatarWindow() {
        String path = "/fxml/avatar_pane.fxml";
        Parent parent;
        FXMLLoader loader = new FXMLLoader();

        try {
            parent = loader.load(getClass().getResourceAsStream(path));
            mainPane.getChildren().addAll(parent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void chooseClass(String clazz) {
        classLabel.setText("Class : " + clazz.toUpperCase());
        //delete border
        mainPane.getChildren()
                .filtered(el -> el.getStyleClass().contains("classes")).forEach(el -> el.setStyle("-fx-border:none"));
        //set border on chosen element
        mainPane.getChildren()
                .filtered(el -> el.getStyleClass().contains("classes"))
                .filtered(el -> el.getId().equalsIgnoreCase(clazz))
                .forEach(el -> el.setStyle("-fx-border-color:red;  -fx-border-width : 3;"));
    }

    private void createCharacter() {
        // TODO
    }

    public static void setAvatarImage(String imageName) {
        // TODO
    }
}

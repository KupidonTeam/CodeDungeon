package KupidonTeam.gui;


import KupidonTeam.DB.DBConnection;
import KupidonTeam.characters.classes.skills.Skill;
import KupidonTeam.login.SignLogic;
import KupidonTeam.server.Connection;
import KupidonTeam.utils.JSON;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class CharacterCreationController {


    @FXML
    private AnchorPane parentPane;

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
    private Button confirmButton;

    @FXML
    private ChoiceBox<String> raceDropDown;

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
    private FlowPane skillPane;

    private int chosenClassId;

    private String username;
    private String password;

    private List<ImageView> classSkills;
    private Popup popupMessage;
    private DBConnection db;

    @FXML
    void initialize() {
        initFxml();

//TODO добавть armor_class
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


        raceDropDown.getItems().addAll(loadRaces());
        raceDropDown.setStyle(".choice-box > .label { -fx-text-fill: white; }");
        avatarImage.setOnMouseClicked(event -> openAvatarWindow());
        confirmButton.setOnMouseClicked(event -> confirmBtSetUp());

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
                .forEach(el -> {
                    el.setStyle("-fx-border-color:red;  -fx-border-width : 3;");
                    loadClassData(clazz);
                    loadSkills();
                });
    }

    private void createCharacter() {
        // TODO
    }

    public static void setAvatarImage(String imageName) {
        // TODO
    }

    private void loadClassData(String chosenClass) {

        try {
            DBConnection db = DBConnection.getDbConnection();
            String query = String.format("Select * from Stats join Classes on Classes.class_id = Stats.hero_id " +
                    "Where class_name = '%s'", chosenClass);
            ResultSet resultSet = db.select(query);
            if (resultSet.next()) {
                System.out.println("my super query = \n" + resultSet.toString());
                hits.setText(resultSet.getString("hits"));
                speed.setText(resultSet.getString("speed"));
                strength.setText(resultSet.getString("strength"));
                dexterity.setText(resultSet.getString("dexterity"));
                intelligence.setText(resultSet.getString("intelligence"));
                wisdom.setText(resultSet.getString("wisdom"));
                chance.setText(resultSet.getString("chance"));
                constitution.setText(resultSet.getString("constitution"));
                chosenClassId = resultSet.getInt("class_id");
            } else {
                hits.setText("null");
                speed.setText("null");
                strength.setText("null");
                dexterity.setText("null");
                intelligence.setText("null");
                wisdom.setText("null");
                chance.setText("null");
                constitution.setText("null");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void setUserData(String username, String password) {
        this.username = username;
        this.password = password;
        usernameLabel.setText(username);
    }

    private ObservableList<String> loadRaces() {
        List<String> tempRaceList = new LinkedList<>();
        try {
            db = DBConnection.getDbConnection();
            String query = String.format("SELECT name FROM `Races`");
            ResultSet resultSet = db.select(query);
            while (resultSet.next()) {
                System.out.println("res = " + resultSet.getString("name"));
                tempRaceList.add(resultSet.getString("name"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return FXCollections.observableList(tempRaceList);
    }

    private void initFxml() {
        assert parentPane != null : "fx:id=\"parentPane\" was not injected: check your FXML file 'create_charachter.fxml'.";
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
        assert confirmButton != null : "fx:id=\"confirmButton\" was not injected: check your FXML file 'create_charachter.fxml'.";
        assert raceDropDown != null : "fx:id=\"raceDropDown\" was not injected: check your FXML file 'create_charachter.fxml'.";
        assert rogueClass != null : "fx:id=\"rogueClass\" was not injected: check your FXML file 'create_charachter.fxml'.";
        assert rangerClass != null : "fx:id=\"rangerClass\" was not injected: check your FXML file 'create_charachter.fxml'.";
        assert paladinClass != null : "fx:id=\"paladinClass\" was not injected: check your FXML file 'create_charachter.fxml'.";
        assert bardClass != null : "fx:id=\"bardClass\" was not injected: check your FXML file 'create_charachter.fxml'.";
        assert druidClass != null : "fx:id=\"druidClass\" was not injected: check your FXML file 'create_charachter.fxml'.";
        assert wizardClass != null : "fx:id=\"wizardClass\" was not injected: check your FXML file 'create_charachter.fxml'.";
        assert skillPane != null : "fx:id=\"skillPane\" was not injected: check your FXML file 'create_charachter.fxml'.";
    }


    private void loadSkills() {
        skillPane.getChildren().clear();
        List<Skill> skills;
        try {
            db = DBConnection.getDbConnection();
            String query = String.format("SELECT * FROM `Attacks` NATURAL JOIN Classes_Attacks\n" +
                    "Where class_id = '%d'", chosenClassId);
            ResultSet resultSet = db.select(query);
            classSkills = new LinkedList<>();
            skills = new LinkedList<>();
            while (resultSet.next()) {
                //  System.out.println("res = " + resultSet.getString("name"));
                skills.add(new Skill(
                        resultSet.getInt("attack_id"),
                        resultSet.getString("attack_name"),
                        resultSet.getInt("lvl"),
                        resultSet.getString("type_attack"),
                        resultSet.getInt("count_of_random"),
                        resultSet.getInt("cooldown"),
                        resultSet.getInt("random_diapason"),
                        resultSet.getString("effect")
                ));
            }
            skills.forEach(el -> {
                System.out.println("skill name = " + el.getName());
                ImageView skillImg = new ImageView("/assets/skills/" + el.getName() + ".png");
                skillImg.setFitWidth(64);
                skillImg.setFitHeight(64);
                Tooltip.install(skillImg, new Tooltip(el.toString()));
                classSkills.add(skillImg);
            });

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        skillPane.getChildren().addAll(classSkills);
    }

    private int getRaceId() {
        try {
            db = DBConnection.getDbConnection();
            String query = String.format(
                    "SELECT race_id FROM `Races` where name = '%s'", raceDropDown.getSelectionModel().getSelectedItem());
            ResultSet resultSet = db.select(query);
            if (resultSet.next()) {
                return resultSet.getInt("race_id");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    private void showError() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("missed choice");
        alert.setHeaderText("Please choose both : Race and Class");
        alert.initModality(Modality.NONE);
        alert.show();
    }

    private void confirmBtSetUp() {
        System.out.println("Choseeeeeeeeen class  = " + chosenClassId);
        if (chosenClassId <= 0 && getRaceId() <= 0) {
            showError();

            return;
        } else {
            Connection
                    .getConnection()
                    .sendMessageToServer(
                            JSON.registration(username, password, chosenClassId, getRaceId()));
            switchToLogin();
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
        fadeTransition.setNode(parentPane);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.setOnFinished((event) -> {
            loadFxml("/fxml/login.fxml");
        });
        fadeTransition.play();
    }
}

package KupidonTeam.gui;

import KupidonTeam.characters.skills.Skill;
import KupidonTeam.db.DBConnection;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import lombok.SneakyThrows;

import java.io.File;
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
    private String avatar = "default";

    private List<ImageView> classSkills;
    private DBConnection db;

    @FXML
    void initialize() {
        initFxml();

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
        avatarImage.setOnMouseClicked(event -> openAvatarWindow());
        confirmButton.setOnMouseClicked(event -> confirmBtSetUp());
    }

    private void openAvatarWindow() {
        chooseAvatar();
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
                    loadSkills(clazz);
                });
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


    private void loadSkills(String clazz) {
        skillPane.getChildren().clear();
        List<Skill> skills;
        try {
            db = DBConnection.getDbConnection();
            System.out.println("class = " + clazz);
            String query = String.format("SELECT * from Attacks NATURAL JOIN Classes_Attacks NATURAL JOIN Classes WHERE class_name = '%s';", clazz);
            System.out.println(query);
            ResultSet resultSet = db.select(query);
            classSkills = new LinkedList<>();
            skills = new LinkedList<>();
            if (resultSet.next()) chosenClassId = resultSet.getInt("class_id");
            while (resultSet.next()) {
                skills.add(new Skill(
                        resultSet.getInt("attack_id"),
                        resultSet.getString("attack_name"),
                        resultSet.getInt("attack_lvl"),
                        resultSet.getString("attack_type"),
                        resultSet.getInt("count_of_random"),
                        resultSet.getInt("attack_cooldown"),
                        resultSet.getInt("random_diapason"),
                        resultSet.getString("attack_effect")
                ));

            }

            skills.forEach(el -> {
                System.out.println("skill name = " + el.getName());
                ImageView skillImg = new ImageView(new Image(getClass().getResourceAsStream("/assets/skills/" + el.getName().toLowerCase() + ".png")));
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
        return 1;
    }

    private void showError() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("missed choice");
        alert.setHeaderText("Please choose both : Race and Class");
        alert.initModality(Modality.NONE);
        alert.show();
    }

    private void confirmBtSetUp() {
        if (chosenClassId <= 0 && getRaceId() <= 0) {
            showError();
            return;
        } else {
            Connection
                    .getConnection()
                    .sendMessageToServer(
                            JSON.registration(username, password, chosenClassId, getRaceId(), avatar));
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
        fadeTransition.setOnFinished(event -> loadFxml("/fxml/login.fxml"));
        fadeTransition.play();
    }


    @SneakyThrows
    private void chooseAvatar() {
        FlowPane avatarImgPane = new FlowPane();
        avatarImgPane.setPrefWidth(390);
        File file = new File(getClass().getResource("/assets/SIMPLEAvatarsIcons/512X512/").toURI());
        Stage stage = new Stage();

        for (File f : file.listFiles()) {
            if (!f.isDirectory()) {
                System.out.println("file = " + f.getName());
                ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream("/assets/SIMPLEAvatarsIcons/512X512/" + f.getName())));
                imageView.setFitHeight(64);
                imageView.setFitWidth(64);
                imageView.setOnMouseClicked(event -> {
                    avatar = f.getName().substring(0, f.getName().indexOf('.'));
                    System.out.println("Chosen avatar = " + avatar);
                    avatarImage.setImage(new Image(getClass().getResourceAsStream("/assets/SIMPLEAvatarsIcons/512X512/" + f.getName())));
                    stage.close();
                });
                avatarImgPane.getChildren().add(imageView);
            }
        }
        avatarImgPane.setStyle("-fx-border-color :  #8e7c74; -fx-border-width : 3; ");
        Scene scene = new Scene(avatarImgPane);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.toFront();
        stage.show();
    }
}

package KupidonTeam.gui;

import KupidonTeam.server.Connection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LoginWrapper extends Application {
    private static Stage currentStage;
    private static Scene scene;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Connection server = Connection.getConnection();
        currentStage = primaryStage;
        String path = "/fxml/login.fxml";
        primaryStage.initStyle(StageStyle.UNDECORATED);
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResourceAsStream(path));
        scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static Stage getCurrentStage() {
        return currentStage;
    }

    public static void setCurrentStage(Stage currentStage) {
        LoginWrapper.currentStage = currentStage;
    }

    public static Scene getScene() {
        return scene;
    }
}

package KupidonTeam.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CharacterCreationWrapper extends Application {
    private static Stage currentStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        currentStage = primaryStage;
        String path = "/fxml/create_charachter.fxml";
        primaryStage.initStyle(StageStyle.UNDECORATED);
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResourceAsStream(path));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static Stage getCurrentStage() {
        return currentStage;
    }
}


package KupidonTeam.fxml;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import KupidonTeam.DB.DBConnection;
import KupidonTeam.server.Connection;
import KupidonTeam.utils.JSON;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class ChatDraftController {
    Connection server;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button sendButton;

    @FXML
    public static TextArea chatPanel;

    @FXML
    private TextArea inputPanel;

    @FXML
    private Label userName;

    @FXML
    void initialize() {
        assert sendButton != null : "fx:id=\"sendButton\" was not injected: check your FXML file 'chat_draft.fxml'.";
        assert chatPanel != null : "fx:id=\"chatPanel\" was not injected: check your FXML file 'chat_draft.fxml'.";
        assert inputPanel != null : "fx:id=\"inputPanel\" was not injected: check your FXML file 'chat_draft.fxml'.";
        assert userName != null : "fx:id=\"userName\" was not injected: check your FXML file 'chat_draft.fxml'.";
        server = Connection.getConnection();

        sendButton.setOnAction(event -> {
            if (inputPanel.getText().isEmpty()) {
                return;
            }
        });
    }

    private void sendMsgToServer(String msg) {
        DBConnection connection = DBConnection.getDbConnection();
        try {
            String id = connection.select("(Select player_id where player_name = '%s');").getString("player_id");
            String message = String.format("{\n" +
                    "    \"action\": \"sendMessage\",\n" +
                    "    \"data\":{\n" +
                    "        \"player_id\": \"%s\",\n" +
                    "        \"message\": \"%s\"\n" +
                    "    }\n" +
                    "}\n", id, msg);
            server.sendMessageToServer(JSON.normalize(message));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}

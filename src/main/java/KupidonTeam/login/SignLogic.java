package KupidonTeam.login;

import KupidonTeam.DB.DBConnection;
import KupidonTeam.player.Player;
import KupidonTeam.server.Connection;
import KupidonTeam.utils.JSON;
import lombok.Getter;
import lombok.SneakyThrows;
import org.json.JSONObject;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;

//Авторизация игрока и подключения модуля баз данных
@Getter
public class SignLogic {
    private static SignLogic signLogic;
    private Connection server;
    private DBConnection database;
    private Boolean responseFlag;
    private int login;
    private Player player;

    private SignLogic() {
        this.server = Connection.getConnection();
        database = DBConnection.getDbConnection();
        responseFlag = false;
    }

    //Возвращаем 1 = имя есть в БД, 0 = имени нет, -1 = не правильный запрос
    public boolean checkUserName(String name) {
        try {
            String query = String.format(
                    "(SELECT player_name FROM `Players` WHERE player_name = '%s' );", name);
            System.out.println("My query = " + query);
            ResultSet result = database.select(
                    query);

            if (result.next()) {
                String res = result.getString("player_name");
                if (res.equalsIgnoreCase(name)) return true;
            } else {
                System.err.println("Empty query");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Wrong query");
        }
        return false;
    }

    //отправляем запрос сереверу на авторизацию
    @SneakyThrows
    public synchronized int serverAuthorization(String username, String password) {
        String msg = JSON.login(username, password);
        server.sendMessageToServer(msg);
        System.out.println("Message to server : \n" + msg);
        System.out.println("------Set socket timeout 5 sec-------");
        wait(3000);
        System.out.println("after wait");
        if (!responseFlag) {
            connectionFailedAlert();
            closeAll();
            System.exit(-200);
        } else {
            System.err.println("=======> response flag = " + responseFlag);
            System.out.println("login status = " + login);
        }
        return login;
    }

    public void serverRegistration(String username, String password) {

    }

    public synchronized void serverResponse(String msg) {
        //подтверждаем ответ
        responseFlag = true;
        System.out.println("Я получил вот такой ответ : " + msg);
        login = loginAnalyze(msg);
        notifyAll();
        //TODO!!!!!!! после успешного логина открываем основную панель

    }

    //принимает json от сервера и десериализует
    private Player createPLayer(String json) {
        return null;

    }

    public void closeAll() {
        database.closeConnection();
        server.closeConnection();
        System.exit(0);
    }

    public static SignLogic getSignLogic() {
        if (signLogic == null) {
            signLogic = new SignLogic();
        }
        return signLogic;
    }

    private int loginAnalyze(String msg) {
        int code = new JSONObject(msg).getInt("code");
        if (code == 203) return 1;
        else return -1;
    }

    private void connectionFailedAlert() {
        JOptionPane.showMessageDialog(null,
                "Server does not response!\nPlease restart the application.",
                "SERVER ERROR",
                JOptionPane.ERROR_MESSAGE);
        System.exit(-503);
    }

}

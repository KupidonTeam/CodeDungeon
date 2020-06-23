package KupidonTeam.controllers;

import KupidonTeam.utils.DBConnection;
import KupidonTeam.model.characters.player.Player;
import KupidonTeam.server.Connection;
import KupidonTeam.utils.JSON;
import KupidonTeam.utils.SoundPlayer;
import lombok.Getter;
import lombok.SneakyThrows;
import org.json.JSONObject;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;

//Авторизация игрока и подключения модуля баз данных
@Getter
public class SignInController {
    private static SignInController signInController;
    private Connection server;
    private DBConnection database;
    private Boolean responseFlag;
    private boolean login;

    private SignInController() {
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
    public synchronized boolean serverAuthorization(String username, String password) {
        String msg = JSON.login(username, password);
        server.sendMessageToServer(msg);
        System.out.println("Message to server : \n" + msg);
        System.out.println("------Set socket timeout 5 sec-------");
        wait(3000);
        System.out.println("after wait");
        System.out.println("response flag = " + responseFlag);

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

    private void createPLayer(String json) {
        JSONObject data = new JSONObject(json).getJSONObject("data");
        Player.createPlayer(data.getString("player_name"),
                data.getString("class_name"),
                data.getInt("level"),
                data.getInt("experience"),
                JSON.stats(data),
                JSON.skills(data),
                JSON.armor(data),
                JSON.weapons(data),
                JSON.animals(data),
                data.getString("avatar")
        );
    }

    public void closeAll() {
        database.closeConnection();
        server.closeConnection();
        System.exit(0);
    }

    public static SignInController getSignInController() {
        if (signInController == null) {
            signInController = new SignInController();
        }

        return signInController;
    }

    public synchronized void loginAnalyze(String msg) {
        responseFlag = true;
        int code = new JSONObject(msg).getInt("code");

        if (code == 203) {
            login = true;
            createPLayer(msg);
            System.out.println(Player.getInstance().toString());

        } else {
            login = false;
        }

        notifyAll();
    }

    private void connectionFailedAlert() {
        JOptionPane.showMessageDialog(null,
                "Server does not response!\nPlease restart the application.",
                "SERVER ERROR",
                JOptionPane.ERROR_MESSAGE);
        System.exit(-503);
    }
}

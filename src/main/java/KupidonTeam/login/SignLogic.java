package KupidonTeam.login;

import KupidonTeam.DB.DBConnection;
import KupidonTeam.GUI.Login;
import KupidonTeam.player.Player;
import KupidonTeam.server.Connection;
import KupidonTeam.utils.JSON;


import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

//Авторизация игрока и подключения модуля баз данных

public class SignLogic {
    private static SignLogic signLogic;
    private String username;
    private String password;
    private Scanner input;
    private Connection server;
    private DBConnection database;
    private Boolean responseFlag;
    private Player player;

//    public SingIn() {
//
//        //input = new Scanner(System.in);
//        database = DBConnection.getDbConnection();
//       // registration();
//    }

    private SignLogic() {
        this.server = Connection.getConnection();
        database = DBConnection.getDbConnection();
        responseFlag = false;
    }


//    private void singInUp() {

//        String action;
//        boolean regFlag = false;
//        while (!regFlag) {
//            action = input.nextLine();
//            switch (action) {
//                case "/registration":
//                    // registration();
//                    break;
//                case "/authorization":
//                    // authorization();
//                    break;
//                default:
//                    System.out.println("No such option!\nPlease type '/registration' or '/authorization'");
//                    break;
//            }
//        }
//
//    }

    //TODO
//    private void registration() {
//        System.out.println("Input Login : ");
//        name = input.nextLine();
//        if (checkUserName(name) == 0) {
//
//        }
//        //server.sendMessageToServer();
//    }


//    private void authorization() {
//        String name;
//        String password;
//        System.out.println("«Authorization»");
//        do {
//            System.out.print("Name : ");
//            name = input.nextLine();
//            System.out.print("\nPassword : ");
//            password = input.nextLine();
//            if (checkUserName(name) != 1) {
//                System.out.println("User with such name does not exist.");
//            }
//        } while (checkUserName(name) == 1);
//        serverAuthorization(name, password);
//    }

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
    public void serverAuthorization(String username, String password) {
        String msg = JSON.login(username, password);
        server.sendMessageToServer(msg);
        System.out.println("------Set socket timeout 5 sec-------");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (!responseFlag) {
            System.err.println("Server does not response");
            JOptionPane.showMessageDialog(null,
                    "Server does not response!\nPlease restart the application.",
                    "SERVER ERROR",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(-200);
        }
    }

    public void serverRegistration(String username, String password) {

    }

    public void serverResponse(String msg) {
        //подтверждаем ответ
        responseFlag = true;
        System.out.println(msg);


        //TODO!!!!!!! после успешного логина открываем основную панель
        //Application.launch(ChatWrapper.class, Game.argz);

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

}

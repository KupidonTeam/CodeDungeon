package KupidonTeam.login;

import KupidonTeam.DB.DBConnection;
import KupidonTeam.server.Connection;
import KupidonTeam.utils.JSON;
import KupidonTeam.utils.Timer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

//Авторизация игрока и подключения модуля баз данных

public class SingIn {
    private String name;
    private String password;
    private Scanner input;
    private Connection server;
    private DBConnection database;
    private Boolean responseFlag;

    public SingIn() {

        input = new Scanner(System.in);
        database = new DBConnection();
        registration();
    }

    public SingIn(Connection server) {
        this.server = server;
        input = new Scanner(System.in);
        database = new DBConnection();
        responseFlag = false;
        singInUp();
    }


    private void singInUp() {
//TODO regFlag doesn't change
        String action;
        boolean regFlag = false;
        while (!regFlag) {
            action = input.nextLine();
            switch (action) {
                case "/registration":
                    registration();
                    break;
                case "/authorization":
                    authorization();
                    break;
                default:
                    System.out.println("No such option!\nPlease type '/registration' or '/authorization'");
                    break;
            }
        }

    }

    //TODO
    private void registration() {
        System.out.println("Input Login : ");
        name = input.nextLine();
        if (checkUserName(name) == 0) {

        }
        //server.sendMessageToServer();
    }


    private void authorization() {
        String name;
        String password;
        System.out.println("«Authorization»");
        do {
            System.out.print("Name : ");
            name = input.nextLine();
            System.out.print("\nPassword : ");
            password = input.nextLine();
            if (checkUserName(name) != 1) {
                System.out.println("User with such name does not exist.");
            }
        } while (checkUserName(name) == 1);
        serverAuthorization(name, password);
    }

    //Возвращаем 1 = имя есть в БД, 0 = имени нет, -1 = не правильный запрос
    private int checkUserName(String name) {
        try {
            ResultSet result = database.select(
                    // ResultSet result = server.DBselect(
                    String.format(
                            "(SELECT player_name FROM `Players` WHERE player_name = '%s');", name));

            if (result.next()) {
                while (result.next()) {
                    String st = result.getString("player_name");
                    if (st.equalsIgnoreCase(name)) {
                        return 1;
                    }
                }
            } else {
                System.out.println("Name is free");
                return 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Wrong query");
        }
        return -1;
    }

    //отправляем запрос сереверу на авторизацию
    private void serverAuthorization(String name, String password) {
        String msg = String.format("{\n" +
                "    \"action\": \"authorizationPlayer\",\n" +
                "    \"data\":{\n" +
                "        \"player_name\": \"%s\",\n" +
                "        \"player_password\": \"%s\"\n" +
                "    }\n" +
                "}\n", name, password);
        msg = JSON.normalize(msg);

        server.sendMessageToServer(msg);

        //устанавливам таймер в 5 сек, если по истечению их не поступить ответ от сервера, выводим ошибку
        Timer.timer(5000);
        if (!responseFlag) {
            System.err.println("Server does not response");
        }
    }

    public void serverResponse(String msg) {
        //подтверждаем ответ
        responseFlag = true;
        System.out.println(msg);

    }

}

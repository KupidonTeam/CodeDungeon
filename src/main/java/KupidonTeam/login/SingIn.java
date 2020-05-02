package KupidonTeam.login;

import KupidonTeam.DB.DBConnection;
import KupidonTeam.server.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


public class SingIn {

    private String login;
    private String password;
    private Scanner input;
    private Connection server;
    private DBConnection database;

    public SingIn() {

        input = new Scanner(System.in);
        database = new DBConnection();
        registration();
    }

    public SingIn(Connection server) {
        this.server = server;
        input = new Scanner(System.in);
        registration();
    }

    private void registration() {
        System.out.println("Input Login : ");
        login = input.nextLine();
        checkUserName(login);
        //server.sendMessageToServer();
    }


    public void checkUserName(String name) {
        try {
            ResultSet result = database.select(
                    // ResultSet result = server.DBselect(
                    String.format(
                            "(SELECT player_name FROM `Players` WHERE player_name = '%s');", name));

            if (result.next()) {
                while (result.next()) {
                    String st = result.getString("player_name");
                    System.out.println(st.isEmpty());
                }
            } else {
                System.out.println("Name is free");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Wrong query");
        }
    }
}

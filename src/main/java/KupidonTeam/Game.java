package KupidonTeam;


import KupidonTeam.gui.LoginWrapper;
import KupidonTeam.server.Connection;
import javafx.application.Application;

//Class starter
public class Game {
    public static String[] argz;

    public static void main(String[] args) {

        Connection server = Connection.getConnection();
        Application.launch(LoginWrapper.class, args);


    }
}
package KupidonTeam;


import KupidonTeam.gui.LoginWrapper;
import KupidonTeam.server.Connection;
import javafx.application.Application;

import java.io.IOException;


//Class starter
public class Game {
    public static String[] argz;

    public static void main(String[] args) throws IOException {

        Connection server = Connection.getConnection();
        Application.launch(LoginWrapper.class, args);

    }

}
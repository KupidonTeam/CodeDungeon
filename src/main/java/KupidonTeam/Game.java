package KupidonTeam;


import KupidonTeam.gui.LoginWrapper;
import KupidonTeam.server.Connection;
import javafx.application.Application;
import javafx.scene.control.Alert;
import lombok.SneakyThrows;

import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
//Class starter
public class Game {
    public static String[] argz;

    public static void main(String[] args) {

        Connection server = Connection.getConnection();
        Application.launch(LoginWrapper.class, args);
    }
}

package KupidonTeam;

import KupidonTeam.GUI.Login;
import KupidonTeam.fxml.Wrapper;
import KupidonTeam.login.SignLogic;
import javafx.application.Application;

import javax.swing.*;

public class Game {
    public static String[] argz;
    public static void main(String[] args) {
        argz = args;
//        JFrame mainFrame = new JFrame();
        //Connection connection = Connection.getConnection();
//        SignLogic singIn = SignLogic.getSignLogic();
//        mainFrame = Login.getLogin();
//        mainFrame.setVisible(true);
        Application.launch(Wrapper.class, args);

    }
}

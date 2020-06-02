package KupidonTeam;

import KupidonTeam.GUI.Login;

import KupidonTeam.fxml.LoginWrapper;
import KupidonTeam.login.SignLogic;
import KupidonTeam.server.Connection;
import javafx.application.Application;

import javax.swing.*;


//Class starter
public class Game {
    public static String[] argz;

    public static void main(String[] args) {

//        JFrame mainFrame = new JFrame();
//        Connection connection = Connection.getConnection();
        Application.launch(LoginWrapper.class, args);
//         SignLogic singIn = SignLogic.getSignLogic();
//        mainFrame = Login.getLogin();
//        mainFrame.setVisible(true);
        //Application.launch(Wrapper.class, args);

    }
}

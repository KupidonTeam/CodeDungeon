package KupidonTeam;

import KupidonTeam.GUI.Login;

import javax.swing.*;

public class Game {
    public static void main(String[] args) {
        JFrame mainFrame = new JFrame();
        //Connection connection = Connection.getConnection();
        // SingIn singIn = SingIn.getSingIn();
        mainFrame = new Login();
        mainFrame.setVisible(true);


    }
}

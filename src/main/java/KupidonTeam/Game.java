package KupidonTeam;


import KupidonTeam.gui.LoginWrapper;
import javafx.application.Application;


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

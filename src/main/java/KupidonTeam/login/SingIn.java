package KupidonTeam.login;

import KupidonTeam.server.Connection;

import java.util.Scanner;

public class SingIn {

    private String login;
    private String password;
    private Scanner input;
    private Connection server;

    public SingIn(){
        input = new Scanner(System.in);
        registration();
    }

    public SingIn(Connection server){
        this.server = server;
        input = new Scanner(System.in);
        registration();
    }

    private void registration(){
        System.out.println("Input Login : ");
        login = input.nextLine();
        checkLogin(login);
        //server.sendMessageToServer();
    }


    private boolean checkLogin(String login){
        String loginForm = String.format("{" +
                "\"action\": \"checkPlayer\"," +
                " \"name\": \"%s\"" +
                "}",login);
        System.out.println(loginForm);
        server.sendMessageToServer(loginForm);
       // server.
        return true;
    }
}

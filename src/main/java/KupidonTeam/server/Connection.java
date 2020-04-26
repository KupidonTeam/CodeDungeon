package KupidonTeam.server;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;
import java.util.Properties;
import java.util.Scanner;

public class Connection {
    private Socket clientSocket;
    private Properties properties;
    String data;


    private void connection() {
        try {
            properties.load(new FileInputStream("src/main/resources/connection.properties"));
        } catch (IOException e) {
            System.out.println("Could not load connection property file");
            e.printStackTrace();
        }
    }

}

package KupidonTeam.server;

import KupidonTeam.exceptions.PropertiesException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;
import java.util.Properties;
import java.util.Scanner;

public class Connection {
    private Socket clientSocket;
    private Properties properties;


    private void connection() throws PropertiesException {
        try {
            properties.load(new FileInputStream("src/main/resources/connection.properties"));
        } catch (IOException e) {
            throw new PropertiesException("CantFindPropetiesFile");
        }
        String host = properties.getProperty("host");
        String port = properties.getProperty("port");

    }

}

package KupidonTeam.server;

import KupidonTeam.controllers.ResponseAnalyzerController;
import KupidonTeam.exceptions.FailedToConnectException;
import KupidonTeam.exceptions.PropertiesException;
import lombok.Getter;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Properties;
import java.util.Scanner;

//Singleton
@Getter
public class Connection {
    private static Connection connection;
    private Socket clientSocket;
    private String host;
    private int port;
    private Scanner inMessage;
    private PrintWriter outMessage;
    private ResponseAnalyzerController responseAnalyzerController;


    private Connection() {
        setup();
    }
    public static Connection getConnection() {
        if (connection == null) {
            connection = new Connection();
        }

        return connection;
    }
    private void setup() {
        try {
            setProperties();
            responseAnalyzerController = ResponseAnalyzerController.getInstance();
        } catch (PropertiesException e) {
            e.printStackTrace();
        }

        try {
            connection();
            serverListener(clientSocket);
        } catch (IOException e) {
            System.err.println("Socket connection failed ");
            System.exit(-500);
        }
    }

    private void connection() throws IOException {
        System.out.println("Try to connect");
        try {
            clientSocket = new Socket(host, port);
            inMessage = new Scanner(clientSocket.getInputStream());
            outMessage = new PrintWriter(clientSocket.getOutputStream());

        } catch (FailedToConnectException ex) {
            System.err.println("can not connect to server");
            clientSocket.close();
            System.exit(-1);
        }

        System.out.println("connected successful");
    }
    public void sendMessageToServer(String msg) {
        if (!msg.isEmpty()) {
            System.out.println("=========send msg to ser method=======");
            System.out.println("msg = " + msg);
            outMessage.println(msg);
            outMessage.flush();
        }
    }


    private void serverListener(Socket clientSocket) {
        new Thread(() -> {
            // бесконечный цикл
            while (true) {
                String serverMsg = "";
                // если есть входящее сообщение
                if (inMessage.hasNext()) {
                    // считываем его
                    String inMes = inMessage.nextLine();
                    // выводим сообщение
                    System.err.println("===>Server<===");
                    System.out.println("server answer  = " + inMes);
                    serverMsg += inMes;
                    responseAnalyzerController.analyze(serverMsg);
                }
            }
        }).start();
    }


    //Анализируем ответ сервера и запускаем соответствующие методы


    private void setProperties() throws PropertiesException {
        Properties properties;

        try {
            properties = new Properties();
            properties.load(new FileInputStream("src/main/resources/properties/connection.properties"));
            host = properties.getProperty("host");
            port = Integer.parseInt(properties.getProperty("port"));
            System.out.println("Host = " + host + " : " + port);
        } catch (IOException e) {
            throw new PropertiesException("CantFindPropertiesFile");
        }
    }

    public void closeConnection() {
        try {
            clientSocket.close();

            if (clientSocket.isClosed()) {
                System.out.println("Server is closed");
            } else
                System.err.println("Server isn't closed !!!");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.exit(-200);
        }
    }
}






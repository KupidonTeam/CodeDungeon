package KupidonTeam.server;

import KupidonTeam.DB.DBConnection;
import KupidonTeam.exceptions.FiledToConnectException;
import KupidonTeam.exceptions.PropertiesException;
import KupidonTeam.login.SingIn;
import lombok.SneakyThrows;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Properties;
import java.util.Scanner;

public class Connection {
    private Socket clientSocket;
    private Properties properties;
    private String host;
    private int port;
    private Scanner inMessage;
    private PrintWriter outMessage;
    private DBConnection database;
    private SingIn singIn;


    public Connection(){
        setup();
    }

    private void setup(){
        try {
            setProperties();
        }catch (PropertiesException e){
            e.printStackTrace();
        }
        try {
            connection();
        }catch (IOException e){
            System.err.println("Socket fail ");
            e.printStackTrace();
        }
        database = new DBConnection();
        serverListener(clientSocket);
        singIn = new SingIn(this); //для локальных тестов, для коннекта нуже new SingIn(Connection con)

    }

    private void connection() throws IOException {
        System.out.println("Try to connect");
        //пытаемся подключиться к серверу
        try {
            clientSocket = new Socket(host, port);
            inMessage = new Scanner(clientSocket.getInputStream());
            outMessage = new PrintWriter(clientSocket.getOutputStream());
        } catch (FiledToConnectException ex) {
            System.err.println("can not connect to server");
            clientSocket.close();
        }
        System.out.println("connected successful");
    }

    public void sendMessageToServer() {
        Scanner input = new Scanner(System.in);
        while (true) {
            String buff = input.next();
            if (!buff.isEmpty()) {
                outMessage.println(buff);
                outMessage.flush();
            }
        }
    }

    public void sendMessageToServer(String msg) {

        if (!msg.isEmpty()) {
            outMessage.println(msg);
            outMessage.flush();
        }
    }


    private void serverListener(Socket clientSocket) {
        new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                try {
                    // бесконечный цикл
                    while (true) {
                        // если есть входящее сообщение
                        if (inMessage.hasNext()) {
                            // считываем его
                            String inMes = new String(inMessage.nextLine());
                            // выводим сообщение
                            System.out.println(inMes);
                        }
                    }
                } catch (Exception e) {
                    clientSocket.close();
                }
            }
        }).start();
    }


    private void analyzeServer(String msg) {

    }

    private void setProperties() throws PropertiesException {
        try {
            properties = new Properties();
            properties.load(new FileInputStream("src/main/resources/connection.properties"));
        } catch (IOException e) {
            throw new PropertiesException("CantFindPropertiesFile");
        }
         host = properties.getProperty("host");
         port = Integer.parseInt(properties.getProperty("port"));

    }


}





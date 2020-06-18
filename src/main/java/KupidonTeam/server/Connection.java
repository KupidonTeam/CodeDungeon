package KupidonTeam.server;

import KupidonTeam.exceptions.FailedToConnectException;
import KupidonTeam.exceptions.PropertiesException;
import KupidonTeam.login.SignLogic;
import KupidonTeam.utils.Container;
import KupidonTeam.utils.JSON;
import javafx.scene.control.TextArea;
import javafx.scene.layout.FlowPane;
import lombok.Getter;
import org.json.JSONObject;

import javax.swing.*;
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

    private FlowPane cardTable;
    private TextArea chatArea;

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
            System.out.println("socket = " + clientSocket.toString());
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
                    responseAnalyzer(serverMsg);
                }
            }
        }).start();
    }


    //Анализируем ответ сервера и запускаем соответствующие методы
    private void responseAnalyzer(String msg) {
        SignLogic logic = SignLogic.getSignLogic();
        JSONObject serverResponse = new JSONObject(msg);
        String action = serverResponse.getString("action");
        System.out.println("action = " + action);
        System.out.println("Server response : \n" + msg +
                "\n=======================================================");

        switch (action) {
            case "playerAuthorization":
                logic.loginAnalyze(msg);
                break;
            case "sendChatMessage":
                addMessageToChat(serverResponse.getJSONObject("data"));
                break;
            case "getDungeonSkeleton":

                createDungeonSkeleton(msg);
                break;

            case "connectToServer":
                if (serverResponse.getInt("code") != 100) {
                    connectionFailedAlert();
                }
                break;
            default:
                System.out.println("Invalid response : " + msg);
                System.err.println("Wrong server response");
        }
    }

    private void setProperties() throws PropertiesException {
        Properties properties;

        try {
            String propFile = "src/main/resources/connection.properties";
            properties = new Properties();
            properties.load(new FileInputStream("src/main/resources/connection.properties"));
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


    private void connectionFailedAlert() {
        JOptionPane.showMessageDialog(null,
                "Server does not response!\nPlease restart the application.",
                "SERVER ERROR",
                JOptionPane.ERROR_MESSAGE);
        System.exit(-200);
    }


    public void setChatArea(TextArea chatArea) {
        this.chatArea = chatArea;
    }

    public void setCardTable(FlowPane cardTable) {
        this.cardTable = cardTable;
    }

    private void addMessageToChat(JSONObject data) {
        String buffer = JSON.inboxMessage(data);
        chatArea.appendText(buffer);
    }


    //create a dungeon skeleton and add it into common object 'Container'
    public synchronized void createDungeonSkeleton(String dungeonJson) {
        System.out.println("Cotainer = " + dungeonJson);
        JSONObject dungeonData = new JSONObject(dungeonJson).getJSONObject("data");
        System.out.println("dunge data = " + dungeonData);
        Container.setDungeonList(JSON.dungeons(dungeonData));
        System.out.println("CONNNNNNNNNNNNNNTAiner = " + Container.getDungeonList());

        notify();
    }

}






package KupidonTeam.server;

import KupidonTeam.exceptions.FiledToConnectException;
import KupidonTeam.exceptions.PropertiesException;
import KupidonTeam.login.SignLogic;
import lombok.SneakyThrows;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Properties;
import java.util.Scanner;

//Singleton
public class Connection {
    private static Connection connection;
    private Socket clientSocket;
    private String host;
    private int port;
    private Scanner inMessage;
    private PrintWriter outMessage;
    private SignLogic signLogic;
    private String serverResponse; //переменная отправленя ответа в другие методы


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
//            sendMessageToServer("lolololololol");
            serverListener(clientSocket);
//            singIn = SingIn.getSingIn();
        } catch (IOException e) {
            System.err.println("Socket connection failed ");
        }

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
            System.exit(-1);
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
                            String inMes = inMessage.nextLine();
                            // выводим сообщение
                            responseAnalyzer(inMes);
                            System.out.println(inMes);
                        }
                    }
                } catch (Exception e) {
                    clientSocket.close();
                }
            }
        }).start();
    }


    //Анализируем ответ сервера и запускаем соответствующие методы
    private void responseAnalyzer(String msg) {
        String action = new JSONObject(msg).getString("action");
        switch (action) {
            case "playerAuthorization":
                signLogic.serverResponse(msg);
                break;
            default:
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





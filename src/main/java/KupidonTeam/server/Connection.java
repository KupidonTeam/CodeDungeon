package KupidonTeam.server;

import KupidonTeam.exceptions.FiledToConnectException;
import KupidonTeam.exceptions.PropertiesException;
import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Properties;
import java.util.Scanner;

public class Connection {
    private ZMQ.Socket clientSocket;
    private Properties properties;
    private Scanner inMessage;
    private PrintWriter outMessage;

    public Connection() throws IOException {
        try {
            connection();
        } catch (PropertiesException | FiledToConnectException e) {
            e.printStackTrace();
        }

    }

    private void conn2() {

    }

    private void connection() throws IOException {
        //пытаемся загрузть файл настроек
        System.out.println("Try to connect");
        try {
            properties = new Properties();
            properties.load(new FileInputStream("src/main/resources/connection.properties"));
        } catch (IOException e) {
            throw new PropertiesException("CantFindPropertiesFile");
        }
        String host = properties.getProperty("host");
        int port = Integer.parseInt(properties.getProperty("port"));
        //пытаемся подключиться к серверу
//        ZContext context = new ZContext();
//        clientSocket = context.createSocket(SocketType.REQ);
//        clientSocket.connect("tcp://178.132.156.98:1308");
        sendMessageToServer();
    }

    public void sendMessageToServer() {
        Scanner input = new Scanner(System.in);
        try (ZContext context = new ZContext()) {
            //  Socket to talk to server
            System.out.println("Connecting to hello world server");
            ZMQ.Socket socket = context.createSocket(SocketType.REQ);
            socket.connect("tcp://178.132.156.98:1308");
            //serverListener(socket);
            while (true) {
                String request = input.nextLine();
                System.out.println("Sending Message ");
                socket.send(request.getBytes(ZMQ.CHARSET), 0);
//                byte[] reply = socket.recv(0);
//                System.out.println(
//                        "Received " + new String(reply, ZMQ.CHARSET) + " ");
            }
        }
    }


    private void serverListener(ZMQ.Socket socket) {
        while (true) {
            try (ZContext context = new ZContext()) {
                //TODO добавить обработчик сообщений
                byte[] reply = socket.recv(0);
                System.out.println(
                        "Received " + new String(reply, ZMQ.CHARSET) + " ");
            }
        }
    }
}





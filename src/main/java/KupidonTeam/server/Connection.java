package KupidonTeam.server;

import KupidonTeam.exceptions.FiledToConnectException;
import KupidonTeam.exceptions.PropertiesException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Properties;
import java.util.Scanner;

public class Connection{
    private Socket clientSocket;
    private Properties properties;
    private Scanner inMessage;
    private PrintWriter outMessage;

    public Connection(){
        try {
            connection();
        } catch (PropertiesException | FiledToConnectException e) {
            e.printStackTrace();
        }

    }

    private void connection() throws PropertiesException, FiledToConnectException {
        //пытаемся загрузть файл настроек
        try {
            properties = new Properties();
            properties.load(new FileInputStream("src/main/resources/connection.properties"));
        } catch (IOException e) {
            throw new PropertiesException("CantFindPropertiesFile");
        }
        String host = properties.getProperty("host");
        int port = Integer.parseInt(properties.getProperty("port"));
        //пытаемся подключиться к серверу
        try {
            clientSocket = new Socket(host, port);
            inMessage = new Scanner(clientSocket.getInputStream());
            outMessage = new PrintWriter(clientSocket.getOutputStream());
        }catch (IOException e){
            throw new FiledToConnectException(String.format("Can't connect to server('%s','%d')",host,port));
        }

    }

    public void sendMessageToServer(String msg){
        outMessage.println(msg);
        outMessage.flush();
    }

    private void serverListener(){
        new Thread(()->{
            while(true){
                //TODO добавить обработчик сообщений
                if(inMessage.hasNext()){
                    String inMes = inMessage.nextLine();
                    System.out.println(inMes);
                }
            }
        }).start();
    }


}

package KupidonTeam.DB;

import KupidonTeam.exceptions.PropertiesException;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DBConnection {
    private Connection con;
    private int lport;
    private String rhost;
    private int rport;
    private String user;
    private String password;
    private String host;
    private int port;        //локальный порт клиента
    private String url;
    private String db;
    private String dbUser;
    private String dbPassword;


    public DBConnection() {
        try {
            setUp();
        } catch (SQLException e) {
        }
    }

    private void connect() {
        try {
            JSch jsch = new JSch();
            Session session = jsch.getSession(user, host, port);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            System.out.println("Establishing Connection...");
            session.connect();
            int assinged_port = session.setPortForwardingL(lport, rhost, rport);
            System.out.println("localhost:" + assinged_port + " -> " + rhost + ":" + rport);
        } catch (JSchException e) {
            System.err.println("Failed BD connection ");
        }
    }

    private void setUp() throws SQLException {
        try {
            setProperties();
            connect();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("An example for updating a Row from Mysql Database!");
        con = null;
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://" + rhost + ":" + lport + "/";
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url + db, dbUser, dbPassword);

        } catch (Exception e) {
            e.printStackTrace();
            con.close();
            // System.exit(0);
        }
    }

    public ResultSet select(String sql) {

        try {
            Statement st = con.createStatement();
            //String sql = "UPDATE Players SET race_id = 3 WHERE player_name = 'test'";
            return st.executeQuery(sql);
            // while(resultSet.)

        } catch (SQLException s) {
            System.out.println(s.getMessage());
            System.out.println("SQL statement is not executed!");
            //con.close();
            // System.exit(0);
        }
        return null;
    }

    private void setProperties() throws PropertiesException {
        Properties properties;
        try {
            properties = new Properties();
            properties.load(new FileInputStream("src/main/resources/DB.properties"));
            lport = Integer.parseInt(properties.getProperty("lport"));
            rhost = properties.getProperty("rhost");
            rport = Integer.parseInt(properties.getProperty("rport"));
            user = properties.getProperty("user");
            password = properties.getProperty("password");
            host = properties.getProperty("host");
            port = Integer.parseInt(properties.getProperty("port"));        //локальный порт клиента
            url = properties.getProperty("url");
            db = properties.getProperty("db");
            dbUser = properties.getProperty("dbUser");
            dbPassword = properties.getProperty("dbPassword");
        } catch (IOException e) {
            throw new PropertiesException("CantLoadProperties");
        }
    }
}
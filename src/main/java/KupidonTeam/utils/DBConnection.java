package KupidonTeam.utils;

import KupidonTeam.exceptions.FailedDBConnection;
import KupidonTeam.exceptions.PropertiesException;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

// Использован паттерн SingleTone для избежания создания множественных экземпляров
public class DBConnection {
    private static DBConnection dbConnection;
    private Connection con;
    private int lport;
    private String rhost;
    private int rport;
    private String user;
    private String password;
    private String host;
    private int port;
    private String url;
    private String db;
    private String dbUser;
    private String dbPassword;

    private DBConnection() throws FailedDBConnection {
        try {
            setUp();
        } catch (SQLException e) {
            throw new FailedDBConnection();
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

        con = null;
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://" + rhost + ":" + lport + "/";

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url + db, dbUser, dbPassword);
        } catch (Exception e) {
            e.printStackTrace();
            con.close();
        }
    }

    public ResultSet select(String sql) {
        try {
            Statement st = con.createStatement();

            return st.executeQuery(sql);
        } catch (SQLException s) {
            System.out.println(s.getMessage());
            System.out.println("SQL statement is not executed!");
        }

        return null;
    }

    private void setProperties() throws PropertiesException {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("src/main/resources/properties/DB.properties"));
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

    public static DBConnection getDbConnection() {
        if (dbConnection == null) {
            try {
                dbConnection = new DBConnection();
            } catch (FailedDBConnection failedDBConnection) {
                System.err.println("DB connection FAILED");
                failedDBConnection.printStackTrace();
            }
        }

        return dbConnection;
    }

    public void closeConnection() {
        try {
            con.close();

            if (con.isClosed()) {
                System.out.println("DB is closed");
            } else {
                System.err.println("Can not close DB");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
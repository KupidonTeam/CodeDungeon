package KupidonTeam.DB;

import KupidonTeam.exceptions.FailedBDConnection;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import java.sql.*;

public class DBConnection {
    private  int lport;
    private String rhost;
    private int rport;
    private Connection con;
    public DBConnection(){
        try {
            setUp();
        }catch (SQLException e){}
    }

    private void connect() {
        String user = "codedungeon";
        String password = "kqKVZS6M";
        String host = "178.132.156.98";
        int port = 22458;

        try {
            JSch jsch = new JSch();
            Session session = jsch.getSession(user, host, port);
            lport = 4321;
            rhost = "localhost";
            rport = 3306;
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
            connect();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("An example for updating a Row from Mysql Database!");
        con = null;

        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://" + rhost + ":" + lport + "/";
        String db = "CodeDungeon";
        String dbUser = "CodeDungeon";
        String dbPasswd = "CodeDungeon";
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url + db, dbUser, dbPasswd);

        } catch (Exception e) {
            e.printStackTrace();
            con.close();
           // System.exit(0);
        }
    }

    public String select(String sql){
        try {
            Statement st = con.createStatement();
            //String sql = "UPDATE Players SET race_id = 3 WHERE player_name = 'test'";
            StringBuilder stringBuilder = new StringBuilder();

            ResultSet resultSet = st.executeQuery(sql);
           // while(resultSet.)

        } catch (SQLException s) {
            System.out.println(s.getMessage());
            System.out.println("SQL statement is not executed!");
            //con.close();
           // System.exit(0);
        }
        return "";
    }
}
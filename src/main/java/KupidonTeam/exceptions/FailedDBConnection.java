package KupidonTeam.exceptions;

import com.jcraft.jsch.JSchException;

public class FailedDBConnection extends JSchException {
    public FailedDBConnection(){
        super("Failed to connect to DB");
    }
}

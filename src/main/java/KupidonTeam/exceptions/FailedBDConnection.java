package KupidonTeam.exceptions;

import com.jcraft.jsch.JSchException;

public class FailedBDConnection extends JSchException {
    public FailedBDConnection(){
        super("Failed to connect to DB");
    }
}

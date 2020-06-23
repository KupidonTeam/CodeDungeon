package KupidonTeam.model.exceptions;

import java.io.IOException;

public class FailedToConnectException extends IOException {
    public FailedToConnectException(String s) {
        super(s);
    }
}

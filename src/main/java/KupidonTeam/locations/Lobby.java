package KupidonTeam.locations;

import KupidonTeam.enums.Direction;

import java.util.List;

public class Lobby extends Room {
    public Lobby() {
        super();
    }

    public Lobby(int roomId, List<Direction> availableDirections, String roomName, String roomDescription) {
        super(roomId, availableDirections, roomName, roomDescription);
    }
}


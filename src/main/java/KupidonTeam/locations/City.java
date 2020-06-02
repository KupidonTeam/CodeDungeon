package KupidonTeam.locations;

import KupidonTeam.enums.Direction;

import java.util.List;

public class City extends Room {
    public City() {
        super();
    }

    public City(int roomId, List<Direction> availableDirections, String roomName, String roomDescription) {
        super(roomId, availableDirections, roomName, roomDescription);
    }
}


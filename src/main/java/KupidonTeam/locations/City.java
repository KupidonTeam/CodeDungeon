package KupidonTeam.locations;

import KupidonTeam.enums.Direction;

import java.util.List;

public class City extends Room {
    public City() {
        super();
    }

    public City(int roomId, List<Direction> availableDirections, String roomDescription) {
        super(roomId, availableDirections, roomDescription);
    }
}


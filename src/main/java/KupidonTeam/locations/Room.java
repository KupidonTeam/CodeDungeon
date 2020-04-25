package KupidonTeam.locations;

import java.util.List;

public abstract class Room {
    private int roomId;
    private List<Direction> availableDirections;
    private String roomDescription;

    public Room(int roomId, List<Direction> availableDirections, String roomDescription) {
        this.roomId = roomId;
        this.availableDirections = availableDirections;
        this.roomDescription = roomDescription;
    }
}

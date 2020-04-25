package KupidonTeam.locations;

import KupidonTeam.enums.Direction;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
public abstract class Room {
    private int roomId;
    private List<Direction> availableDirections;
    private String roomDescription;

    public Room() {
        roomId = 0;
        availableDirections = new LinkedList<>();
        roomDescription = "Start city";
    }

    public Room(int roomId, List<Direction> availableDirections, String roomDescription) {
        this.roomId = roomId;
        this.availableDirections = availableDirections;
        this.roomDescription = roomDescription;
    }
}

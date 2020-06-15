package KupidonTeam.locations;

import KupidonTeam.enums.Direction;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
public abstract class Room {
    private int roomId;
    private Integer[] availableDirections;
    private String roomName;
    private String roomDescription;

    public Room() {
        roomId = 0;
        availableDirections = new Integer[1];
        roomDescription = "Start city";
    }

    public Room(int roomId, Integer[] availableDirections, String roomName, String roomDescription) {
        this.roomId = roomId;
        this.availableDirections = availableDirections;
        this.roomName = roomName;
        this.roomDescription = roomDescription;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomId=" + roomId +
                ", availableDirections=" + Arrays.toString(availableDirections) +
                ", roomName='" + roomName + '\'' +
                ", roomDescription='" + roomDescription + '\'' +
                '}';
    }
}

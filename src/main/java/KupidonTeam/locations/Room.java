package KupidonTeam.locations;

import KupidonTeam.enums.Direction;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
public abstract class Room {
    private int roomId;
    private List<Integer> availableDirections;
    private String roomName;
    private String roomDescription;

    public Room() {
        roomId = 0;
        availableDirections = new ArrayList<>();
        roomDescription = "Start city";
    }

    public Room(int roomId, List<Integer> availableDirections, String roomName, String roomDescription) {
        this.roomId = roomId;
        this.availableDirections = availableDirections;
        this.roomName = roomName;
        this.roomDescription = roomDescription;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomId=" + roomId +
                ", availableDirections=" + availableDirections.toString() +
                ", roomName='" + roomName + '\'' +
                ", roomDescription='" + roomDescription + '\'' +
                '}';
    }
}

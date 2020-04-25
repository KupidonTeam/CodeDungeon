package KupidonTeam.locations;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class City extends Room {
    public City(int roomId, List<Direction> availableDirections, String roomDescription) {
        super(roomId, availableDirections, roomDescription);
    }
}

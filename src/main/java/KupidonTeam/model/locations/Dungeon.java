package KupidonTeam.model.locations;

import KupidonTeam.model.characters.enemies.Enemy;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Dungeon extends Room {
    private List<Enemy> enemies;

    public Dungeon(int roomId, List<Integer> availableDirections, String roomName, String roomDescription, List<Enemy> enemies) {
        super(roomId, availableDirections, roomName, roomDescription);
        this.enemies = enemies;
    }

    @Override
    public String toString() {
        return "Dungeon{" +
                "enemies=" + enemies.toString() +
                "\nData : \n" + super.toString() +
                '}';
    }
}

package KupidonTeam.locations;

import KupidonTeam.characters.classes.enemies.Enemy;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Dungeon extends Room {
    private List<Enemy> enemies;

    public Dungeon(int roomId, List<Direction> availableDirections, String roomDescription, List<Enemy> enemies) {
        super(roomId, availableDirections, roomDescription);
        this.enemies = enemies;
    }
}

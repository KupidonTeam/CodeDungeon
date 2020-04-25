package KupidonTeam.locations;

import KupidonTeam.characters.classes.enemies.EnemyLogic;
import KupidonTeam.enums.Direction;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Dungeon extends Room {
    private EnemyLogic enemies;


    public Dungeon(int roomId, List<Direction> availableDirections, String roomDescription, EnemyLogic enemies) {
        super(roomId, availableDirections, roomDescription);
        this.enemies = enemies;
    }
}

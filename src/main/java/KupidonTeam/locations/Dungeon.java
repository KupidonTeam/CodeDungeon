package KupidonTeam.locations;


import KupidonTeam.enums.Direction;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Dungeon extends Room {
    //private EnemyContainer enemies;

    public Dungeon(int roomId, List<Direction> availableDirections, String roomName, String roomDescription) {
        super(roomId, availableDirections, roomName, roomDescription);
        // this.enemies = enemies;
    }

    //public EnemyContainer getEnemyContainer() {
//        return enemies;
//    }
}

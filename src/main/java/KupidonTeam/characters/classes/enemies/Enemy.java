package KupidonTeam.characters.classes.enemies;

import KupidonTeam.characters.classes.Stats;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Enemy {
    private Stats stats;
    private long enemyId;
    private String name;
}

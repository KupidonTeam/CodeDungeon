package KupidonTeam.characters.classes.enemies;

import KupidonTeam.characters.classes.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Enemy extends Entity {
    private long enemyId;
    private String name;
}

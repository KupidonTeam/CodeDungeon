package KupidonTeam.characters.classes.enemies;

import KupidonTeam.characters.classes.Stats;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Enemy {
    private int enemyId;
    private String name;
    private String description;
    private int level;

    private Stats stats;

    @Override
    public String toString() {
        return "Enemy{" +
                "enemyId=" + enemyId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", level=" + level +
                ", stats=" + stats.toString() +
                '}';
    }
}

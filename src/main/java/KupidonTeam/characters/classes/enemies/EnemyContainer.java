package KupidonTeam.characters.classes.enemies;

import KupidonTeam.items.Item;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

//Класс обертка, содержит список игроков, их дроп(выпадаемые предметы), так же методы для ENEMYTURN

@Getter
@Setter
public class EnemyContainer {
    private List<Enemy> enemies = new LinkedList<>();
    private List<Item> rewards = new LinkedList<>();

    public EnemyContainer() {
    }

    public EnemyContainer(Enemy... enemies) {
        this.enemies.addAll(Arrays.asList(enemies));
    }

    public EnemyContainer(Enemy[] enemies, Item... rewards) {
        this.enemies.addAll(Arrays.asList(enemies));
        this.rewards.addAll(Arrays.asList(rewards));
    }

    public void enemyTurn() {
        // TODO
    }

    public boolean isAlive() {
        for (Enemy enemy : enemies) {
            if (enemy.getHp() > 0) {

                return true;
            }
        }

        return false;
    }
}

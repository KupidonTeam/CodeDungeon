package KupidonTeam.controllers;

import KupidonTeam.characters.classes.enemies.Enemy;
import KupidonTeam.characters.classes.enemies.EnemyLogic;
import KupidonTeam.enums.Battlestate;
import KupidonTeam.locations.Dungeon;
import KupidonTeam.player.Player;

import java.util.*;

// TODO
//Класс-контроллер боя
public class BattleController {
    private Battlestate battlestate;
    private Player mainPlayer;
    private EnemyLogic enemies;     // Класс-обертка над обычными врагами

    public BattleController(Player mainPlayer) {
        this.mainPlayer = mainPlayer;
        this.enemies = ((Dungeon) mainPlayer.getLocation()).getEnemies();
        battleMode();
    }

    public void battleMode() {
        boolean exitFlag = false;

        if (mainPlayer.getLocation() instanceof Dungeon) {
            enemies = ((Dungeon) mainPlayer.getLocation()).getEnemies();
        } else {
            return;
        }

        battlestate = calculateInitiative(mainPlayer, enemies);

        while (!exitFlag) {
            switch (battlestate) {
                case PLAYERTURN:
                    mainPlayer.playerTurn();
                    if (mainPlayer.isAlive()) {
                        battlestate = Battlestate.ENEMYTURN;
                    } else {
                        battlestate = Battlestate.LOSE;
                    }

                    break;
                case ENEMYTURN:
                    enemies.enemyTurn();
                    if (enemies.isAlive()) {
                        battlestate = Battlestate.PLAYERTURN;
                    } else {
                        battlestate = Battlestate.WIN;
                    }

                    break;
                case LOSE:
                    mainPlayer.playerDefeat();
                    exitFlag = true;

                    break;
                case WIN:
                    mainPlayer.playerRewards(enemies.getRewards());
                    exitFlag = true;

                    break;
            }
        }

        battlestate = Battlestate.FREE;
    }

    // Метод вычисления инициативы, чтобы понять кто ходит первый
    private Battlestate calculateInitiative(Player player, EnemyLogic enemies) {
        Map<Long, Integer> initiative = new HashMap<>();

        initiative.put(player.getId(), player.getIntelligence());

        List<Enemy> enemyList = enemies.getEnemies();
        for (Enemy enemy : enemyList) {
            initiative.put(enemy.getEnemyId(), enemy.getIntelligence());
        }

        long firstTurn = Collections.max(initiative.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey();

        if (firstTurn == player.getId()) {
            return Battlestate.PLAYERTURN;
        } else {
            return Battlestate.ENEMYTURN;
        }
    }
}

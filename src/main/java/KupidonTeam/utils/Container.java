package KupidonTeam.utils;

import KupidonTeam.characters.classes.enemies.Enemy;
import KupidonTeam.locations.Dungeon;

import java.util.List;

public class Container {
    private static String serverAnswer = "empty";
    private static List<Enemy> enemyList;
    private static List<Dungeon> dungeonList;


    public static List<Enemy> getEnemyList() {
        return enemyList;
    }

    public static String getServerAnswer() {
        return serverAnswer;
    }

    public static void setServerAnswer(String data) {
        Container.serverAnswer = data;
    }

    public static void setEnemyList(List<Enemy> enemyList) {
        Container.enemyList = enemyList;
    }

    public static void setDungeonList(List<Dungeon> dungeonList) {
        Container.dungeonList = dungeonList;
    }

    public static List<Dungeon> getDungeonList() {
        return dungeonList;
    }

}

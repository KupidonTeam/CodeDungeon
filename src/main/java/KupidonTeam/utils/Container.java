package KupidonTeam.utils;

import KupidonTeam.characters.classes.enemies.Enemy;
import KupidonTeam.locations.Dungeon;
import javafx.scene.control.TextArea;

import java.awt.*;
import java.util.List;

public class Container {
    private static String serverAnswer = "empty";
    private static List<Enemy> enemyList;
    private static List<Dungeon> dungeonList;
    private static int[][] routes;
    private static SoundPlayer soundPlayer;
    private static TextArea chatPane;


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

    public static void setRoutes(int[][] routes) {
        Container.routes = routes;
    }

    public static int[][] getRoutes() {
        return routes;
    }

    public static void playSound(String path) {
        soundPlayer = new SoundPlayer();
        soundPlayer.playSound(path).play();
    }

    public static void setSoundVolume(double volume) {
        soundPlayer.setVolume(volume);
    }

    public static void setChatPane(TextArea chat) {
        if (chatPane == null) {
            chatPane = chat;
        }
    }

    public static void addMessageToChat(String msg) {
        chatPane.appendText(msg);
    }
}

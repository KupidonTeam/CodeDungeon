package KupidonTeam.utils;

import KupidonTeam.items.Item;
import KupidonTeam.locations.Dungeon;
import javafx.scene.control.TextArea;

import java.util.List;

public class Container {
    private static List<Dungeon> dungeonList;
    private static int[][] routes;
    private static SoundPlayer soundPlayer;
    private static TextArea chatPane;
    private static List<? extends Item> prizes;


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

    public static void setPrizes(List<? extends Item> prizesList) {
        prizes = prizesList;
    }

    public static List<? extends Item> getPrizes() {
        List<? extends Item> temp = prizes;
        prizes = null;
        return temp;
    }
}

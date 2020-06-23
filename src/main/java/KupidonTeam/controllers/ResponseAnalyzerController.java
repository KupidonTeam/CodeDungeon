package KupidonTeam.controllers;

import KupidonTeam.model.characters.player.Player;
import KupidonTeam.model.items.Item;
import KupidonTeam.utils.Container;
import KupidonTeam.utils.JSON;
import javafx.application.Platform;
import org.json.JSONObject;

import javax.swing.*;
import java.util.LinkedList;
import java.util.List;

public class ResponseAnalyzerController {
    private static ResponseAnalyzerController responseAnalyzerController;

    private ResponseAnalyzerController(){

    }

    public static ResponseAnalyzerController getInstance(){
        if(responseAnalyzerController ==null){
            responseAnalyzerController = new ResponseAnalyzerController();
        }
        return responseAnalyzerController;
    }

    public void analyze(String msg) {
        SignInController logic = SignInController.getSignInController();
        JSONObject serverResponse = new JSONObject(msg);
        String action = serverResponse.getString("action");
        System.out.println("action = " + action);
        System.out.println("Server response : \n" + msg +
                "\n=======================================================");

        switch (action) {
            case "playerAuthorization":
                logic.loginAnalyze(msg);

                break;
            case "sendChatMessage":
                addMessageToChat(serverResponse.getJSONObject("data"));

                break;
            case "getDungeonSkeleton":
                createDungeonSkeleton(msg);

                break;
            case "connectToServer":
                if (serverResponse.getInt("code") != 100) {
                    connectionFailedAlert();
                }
                break;
            case "startBattle":
                Platform.runLater(() -> setBattleResults(msg));

                break;
            case "getLoot":
                setLoot(msg);

                break;
            default:
                System.out.println("Invalid response : " + msg);
                System.err.println("Wrong server response");
        }
    }

    private void addMessageToChat(JSONObject data) {
        String buffer = JSON.inboxMessage(data);
        Container.addMessageToChat(buffer);
    }

    //create a dungeon skeleton and add it into common object 'Container'
        private synchronized void createDungeonSkeleton(String dungeonJson) {
            System.out.println("Cotainer = " + dungeonJson);
            JSONObject dungeonData = new JSONObject(dungeonJson).getJSONObject("data");
            System.out.println("dunge data = " + dungeonData);
            Container.setDungeonList(JSON.dungeons(dungeonData));
            notify();
        }

    private void setBattleResults(String msg) {
        JSONObject battleResult = new JSONObject(msg).getJSONObject("data").getJSONObject("battle_result");
        JSONObject playerRes = battleResult.getJSONObject("player_attack");
        JSONObject enemyRes = battleResult.getJSONObject("mob_attack");
        BattleController.getInstance()
                .setBattleResults(
                        playerRes.getInt("damage_given"),
                        playerRes.getJSONObject("attack").getString("attack_name"),
                        enemyRes.getInt("damage_given"),
                        enemyRes.getJSONObject("attack").getString("name")
                );
    }

    private synchronized void setLoot(String msg) {
        System.out.println("Set loot");
        Player player = Player.getInstance();
        JSONObject data = new JSONObject(msg).getJSONObject("data");
        System.out.println(data.getJSONObject("level"));
        System.out.println(data.getJSONObject("level").getBoolean("gotNewLevel"));

        if (data.getJSONObject("level").getBoolean("gotNewLevel")) {
            player.newLvl();
        }

        player.getInventory().addAll(JSON.armor(data));
        player.getInventory().addAll(JSON.weapons(data));
        player.getInventory().addAll(JSON.foods(data));
        List<Item> prizes = new LinkedList<>();
        prizes.addAll(JSON.armor(data));
        prizes.addAll(JSON.weapons(data));
        prizes.addAll(JSON.foods(data));
        Container.setPrizes(prizes);
        System.out.println("Notifty");
        notify();
    }

    private void connectionFailedAlert() {
        JOptionPane.showMessageDialog(null,
                "Server does not response!\nPlease restart the application.",
                "SERVER ERROR",
                JOptionPane.ERROR_MESSAGE);
        System.exit(-200);
    }
}

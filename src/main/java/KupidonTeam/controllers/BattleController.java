package KupidonTeam.controllers;

import KupidonTeam.characters.enemies.Enemy;
import KupidonTeam.gui.*;
import KupidonTeam.locations.Dungeon;
import KupidonTeam.player.Player;
import KupidonTeam.server.Connection;
import KupidonTeam.utils.Container;
import KupidonTeam.utils.JSON;
import KupidonTeam.utils.SoundPlayer;
import javafx.animation.FadeTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import lombok.SneakyThrows;

import java.util.*;

// TODO
// Класс-контроллер боя
public class BattleController {
    private static BattleController battleController;
    private Player mainPlayer;
    private List<Dungeon> dungeonList;
    private Dungeon currentRoom;
    private Set<Integer> passedRooms;
    private MainController mainController;
    private FlowPane cardTable;
    private FlowPane mapPane;
    private Graph map;
    private List<Enemy> killedEnemies;
    private FlowPane hpPane;
    private List<EnemyCard> enemyCards;


    private EnemyCard chosenEnemyCard;

    private BattleController() {
    }

    private BattleController(MainController mainController, Player mainPlayer, List<Dungeon> dungeonsList, FlowPane cardTable, FlowPane mapPane, FlowPane hpPane) {
        this.mainController = mainController;
        this.mainPlayer = mainPlayer;
        this.dungeonList = dungeonsList;
        this.cardTable = cardTable;
        this.mapPane = mapPane;
        this.hpPane = hpPane;
        killedEnemies = new ArrayList<>();
        currentRoom = dungeonsList.get(0);
        passedRooms = new HashSet<>();
        passedRooms.add(0);
        map = new Graph();
        updatePlayerInfo();
        updateRoom();
    }

    public static BattleController getInstance() {
        if (battleController == null) {
            battleController = new BattleController();
        }
        return battleController;
    }

    public void setBattleController(MainController mainController, Player mainPlayer, List<Dungeon> dungeonsList, FlowPane cardTable, FlowPane mapPane, FlowPane hpPane) {
        battleController = new BattleController(mainController, mainPlayer, dungeonsList, cardTable, mapPane, hpPane);

    }


    @SneakyThrows
    public void battleStatus() {
        System.out.println("player hp = " + mainPlayer.getStats().getHits());
        currentRoom.getEnemies()
                .forEach(enemy -> {
                    if (enemy.getStats().getHits() <= 0) {
                        System.out.println(enemy.getName() + " : is killed");
                        killedEnemies.add(enemy);
                    }
                });
        killedEnemies.forEach(enemy -> deleteCard(enemy));
        killedEnemies.clear();
        updateRoom();
        if (mainPlayer.getStats().getHits() <= 0) {
            mainController.messageDialog("You are dead");
            return;
        }
        if (killedEnemies.containsAll(currentRoom.getEnemies())) {
            System.out.println("<--Current room is clear-->");
            cardTable.getChildren().clear();
        }
        if (isDungeonClear()) {
            System.out.println("All enemies are killed!");
            getLoot(mainController);
        }
    }

    private void loadEnemyCards() {
        //  mainController.cleanPeeked();
        enemyCards = new ArrayList<>();
        currentRoom.getEnemies().forEach(el -> {
            enemyCards.add(new EnemyCard(el));
            System.out.println(el.getName());
        });

        enemyCards.forEach(el -> {
            el.getEnemyCard().setOnMouseClicked(event -> {
                if (chosenEnemyCard == null) {
                    el.setBorder();

                    chosenEnemyCard = el;
                    System.out.println("Chosen enemy = " + chosenEnemyCard.getEnemy().getName());
                    System.out.println("Chosen enemy = " + chosenEnemyCard.getEnemy().getEnemyId());
                } else if (chosenEnemyCard.equals(el)) {
                    // delete border
                    el.deleteBorder();

                    chosenEnemyCard = null;
                } else {
                    //delete border from chosen enemy
                    //Timeline timeline = EnemyCardController.setBorder(chosenEnemy);
                    chosenEnemyCard.deleteBorder();
                    el.setBorder();

                    chosenEnemyCard = el;
                    System.out.println("New chosen enemy = " + chosenEnemyCard.getEnemy().getName());
                }
            });
        });

        enemyCards.forEach(enemyCard -> {
            cardTable.getChildren().add(enemyCard.getEnemyCard());
            FadeTransition ft = new FadeTransition(Duration.millis(500), enemyCard.getEnemyCard());
            ft.setFromValue(0);
            ft.setToValue(1);
            ft.setAutoReverse(false);
            ft.play();
        });

        new SoundPlayer().spawnEffect();
    }

    @SneakyThrows
    private void initMainController() {
        String path = "/fxml/main_v2.fxml";
        Parent parent;
        FXMLLoader loader = new FXMLLoader();
        parent = loader.load(getClass().getResourceAsStream(path));
        mainController = loader.getController();
    }


    private void drawMap() {
        mapPane.getChildren().add(map.getPane());
        Integer[] rooms = new Integer[dungeonList.size()];
        Set<Integer> roomSet = new HashSet<>();
        dungeonList.forEach(el -> roomSet.add(el.getRoomId()));
        roomSet.toArray(rooms);
        int[][] routes = Container.getRoutes();
        Integer[] visitedRooms = new Integer[passedRooms.size()];
        passedRooms.toArray(visitedRooms);
        //TODO исправить баг с картой
        map.updateDungeon(rooms, routes, new Integer[]{0}, currentRoom.getRoomId());

    }

    public EnemyCard getChosenEnemyCard() {
        return chosenEnemyCard;
    }


    public void changeRoom(int newRoomId) {
        System.out.println("Current room = " + currentRoom.getRoomId());
        System.out.println("routes = " + currentRoom.getAvailableDirections());
        if (!killedEnemies.containsAll(currentRoom.getEnemies())) {
            System.out.println("Kill all enemies ! ");
            return;
        }
        if (currentRoom.getAvailableDirections().contains(newRoomId)) {
            System.out.println(currentRoom.getAvailableDirections());
            System.out.println("Available route");

            passedRooms.add(currentRoom.getRoomId());
            dungeonList.forEach(el -> {
                if (el.getRoomId() == newRoomId) currentRoom = el;
            });
            updateRoom();
            battleStatus();
        } else {
            System.out.println("Can't go to that room");
        }
    }

    private void deleteCard(Enemy killedEnemy) {
        EnemyCard cardToDelete = null;
        for (EnemyCard enemyCard : enemyCards) {
            if (enemyCard.getEnemy().getName().equalsIgnoreCase(killedEnemy.getName())) {
                cardToDelete = enemyCard;
            }
        }
        SoundPlayer soundPlayer = new SoundPlayer();
        FadeTransition ft = new FadeTransition(Duration.millis(500), cardToDelete.getEnemyCard());
        ft.setFromValue(1);
        ft.setToValue(0);
        ft.setAutoReverse(false);
        ft.play();
        soundPlayer.deathEffect();
        EnemyCard finalCardToDelete = cardToDelete;
        ft.setOnFinished(event -> {
            cardTable.getChildren().remove(finalCardToDelete);
        });
        currentRoom.getEnemies().remove(killedEnemy);

    }

    private void updatePlayerInfo() {
        hpPane.getChildren().clear();
        Label playerHp = new Label(mainPlayer.getStats().getHits() + " %");
        playerHp.setTextFill(Color.rgb(142, 124, 116));
        hpPane.getChildren().add(playerHp);
    }

    private void updateRoom() {
        map = new Graph();
        cardTable.getChildren().clear();
        mapPane.getChildren().clear();
        loadEnemyCards();
        drawMap();

    }

    //Check if all enemies in all rooms are killed
    private boolean isDungeonClear() {
        System.out.println("Dung size = " + dungeonList.size());
        System.out.println("passedrooms=" + passedRooms.size());
        System.out.println(passedRooms.size() == dungeonList.size());
        if (killedEnemies.containsAll(currentRoom.getEnemies()) && passedRooms.size() - 1 == dungeonList.size() - 1) {
            return true;
        }
        return false;
    }


    public void setBattleResults(int enemyDamage, String playerSkill, int playerDamage, String enemySkill) {
        System.out.println("Set battle results");
        chosenEnemyCard.getEnemy().getStats().setDamage(enemyDamage);
        mainPlayer.getStats().setDamage(playerDamage);
        fightPane(mainPlayer,
                chosenEnemyCard,
                enemyDamage,
                playerSkill,
                playerDamage,
                enemySkill);
        updatePlayerInfo();
        battleStatus();
        chosenEnemyCard = null;

    }

    @SneakyThrows
    public FadeTransition fightPane(Player player, EnemyCard enemyCard, int enemyDamage, String playerSkill, int playerDamage, String enemySkill) {
        Stage fightStage = new Stage();
        String path = "/fxml/fight.fxml";
        fightStage.initStyle(StageStyle.UNDECORATED);
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResourceAsStream(path));
        FightController fightController = loader.getController();

        Scene scene = new Scene(root);
        fightStage.setScene(scene);
        fightStage.toFront();
        fightController.setData(player, enemyCard, enemyDamage, playerSkill, playerDamage, enemySkill);
        fightStage.show();
        return new FadeTransition(new Duration(1000));
    }

    @SneakyThrows
    public synchronized void getLoot(MainController mainController) {
        Connection.getConnection().sendMessageToServer(JSON.getLoot());
        wait(1000);
//        initMainController();
        System.out.println("After wait");
        prizePane();
        mainController.setIsDungeon(false);
        mainController.update(true);


    }

    @SneakyThrows
    private void prizePane() {
        Stage prizeStage = new Stage();
        String path = "/fxml/prizes_pane.fxml";
        prizeStage.initStyle(StageStyle.UNDECORATED);
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResourceAsStream(path));
        PrizesPaneController prizesPaneController = loader.getController();

        Scene scene = new Scene(root);
        prizeStage.setScene(scene);
        prizeStage.toFront();
        prizesPaneController.loadPrizes(Container.getPrizes());
        prizeStage.show();

    }
}

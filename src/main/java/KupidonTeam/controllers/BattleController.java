package KupidonTeam.controllers;

import KupidonTeam.characters.classes.enemies.Enemy;
import KupidonTeam.gui.EnemyCard;
import KupidonTeam.gui.Graph;
import KupidonTeam.gui.MainController;
import KupidonTeam.locations.Dungeon;
import KupidonTeam.player.Player;
import KupidonTeam.server.Connection;
import KupidonTeam.utils.Container;
import KupidonTeam.utils.SoundPlayer;
import javafx.animation.FadeTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
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
    private List<Integer> passedRooms;
    private String enemyTurn;
    private Connection server;
    private String actionToServer;
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

    private BattleController(Player mainPlayer, List<Dungeon> dungeonsList, FlowPane cardTable, FlowPane mapPane, FlowPane hpPane) {
        this.mainPlayer = mainPlayer;
        this.dungeonList = dungeonsList;
        this.cardTable = cardTable;
        this.mapPane = mapPane;
        this.hpPane = hpPane;
        killedEnemies = new ArrayList<>();

        initMainController();
        currentRoom = dungeonsList.get(0);
        passedRooms = new ArrayList<>();
        passedRooms.add(0);
        server = Connection.getConnection();

        updatePLayerInfo();
        loadEnemyCards();
        map = new Graph();

        drawMap();
        battleStatus();

    }

    public static BattleController getInstance() {
        if (battleController == null) {
            battleController = new BattleController();
        }
        return battleController;
    }

    public void setBattleController(Player mainPlayer, List<Dungeon> dungeonsList, FlowPane cardTable, FlowPane mapPane, FlowPane hpPane) {
        battleController = new BattleController(mainPlayer, dungeonsList, cardTable, mapPane, hpPane);

    }


    @SneakyThrows
    public void battleStatus() {
        System.out.println("player hp = " + mainPlayer.getStats().getHits());
        currentRoom.getEnemies()
                .forEach(enemy -> {
                    if (enemy.getStats().getHits() <= 0) {
                        System.out.println(enemy.getName() + " : is killed");
                        killedEnemies.add(enemy);
                        deleteCard(enemy);
                    }
                });
        if (mainPlayer.getStats().getHits() <= 0) {
            mainController.messageDialog("You are dead");
            return;
        }
        if (killedEnemies.containsAll(currentRoom.getEnemies())) {
            System.out.println("<--Current room is clear-->");

        }


    }

    private void loadEnemyCards() {

        initMainController();

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
        map.updateDungeon(rooms, routes, visitedRooms, currentRoom.getRoomId());

    }

    public EnemyCard getChosenEnemyCard() {
        return chosenEnemyCard;
    }

    public void setBattleResults(int enemyDamage, String playerSkill, int playerDamage, String enemySkill) {
        System.out.println("Set battle results");
        chosenEnemyCard.getEnemy().getStats().setDamage(enemyDamage);
        mainPlayer.getStats().setDamage(playerDamage);
        chosenEnemyCard = null;
        mainController.cleanPeekedSkill();
        updatePLayerInfo();
        battleStatus();
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
            dungeonList.forEach(el -> {
                if (el.getRoomId() == newRoomId) currentRoom = el;
            });
            updateRoom();
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

    }

    private void updatePLayerInfo() {
        hpPane.getChildren().clear();
        Label playerHp = new Label(mainPlayer.getStats().getHits() + " %");
        playerHp.setTextFill(Color.WHITE);
        hpPane.getChildren().add(playerHp);
    }

    private void updateRoom() {
        cardTable.getChildren().clear();
        mapPane.getChildren().clear();
        loadEnemyCards();
        drawMap();
        battleStatus();
    }
}

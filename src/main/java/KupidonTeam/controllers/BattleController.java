package KupidonTeam.controllers;

import KupidonTeam.enums.Battlestate;
import KupidonTeam.gui.EnemyCard;
import KupidonTeam.gui.MainController;
import KupidonTeam.locations.Dungeon;
import KupidonTeam.player.Player;
import KupidonTeam.server.Connection;
import javafx.animation.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.*;
import javafx.util.Duration;
import lombok.SneakyThrows;


import java.util.*;

// TODO
// Класс-контроллер боя
public class BattleController {
    private Battlestate battlestate;
    private Player mainPlayer;
    private List<Dungeon> dungeonList;
    private Dungeon currentRoom;
    private int[] passedRooms;
    private String enemyTurn;
    private Connection server;
    private String actionToServer;
    private MainController mainController;
    private FlowPane cardTable;

    private EnemyCard chosenEnemyCard;

    public BattleController() {
    }

    public BattleController(Player mainPlayer, List<Dungeon> dungeonsList, FlowPane cardTable) {
        this.mainPlayer = mainPlayer;
        this.dungeonList = dungeonsList;
        this.cardTable = cardTable;
        initMainController();
        currentRoom = dungeonsList.get(0);
        server = Connection.getConnection();
        loadEnemyCards();
        battleMode();

    }

    public void battle() {
        int killedEnemies = 0;

    }

    @SneakyThrows
    public synchronized void battleMode() {
        boolean exitFlag = false;

        int killedEnemies = 0;
        while (killedEnemies < currentRoom.getEnemies().size()) {
            switch (battlestate) {
                case PLAYERTURN:
                    if (mainPlayer.getStats().getHits() < 0) {
                        battlestate = Battlestate.LOSE;
                        break;
                    }
                    System.out.println("go to sleeeeeeeeeeeeeeeeeeeeeeeep");
                    wait();
                    System.out.println("i woke up !!!!!!!!!!!!!!!");

                    break;
                case ENEMYTURN:
//                    enemies.enemyTurn();
//                    if (enemies.isAlive()) {
//                        battlestate = Battlestate.PLAYERTURN;
//                    } else {
//                        battlestate = Battlestate.WIN;
//                    }
//
//                    break;
//                case LOSE:
//                    mainPlayer.playerDefeat();
//                    exitFlag = true;
//
//                    break;
//                case WIN:
//                    mainPlayer.playerRewards(enemies.getRewards());
//                    exitFlag = true;
//
//                    break;
            }
        }

        battlestate = Battlestate.FREE;
    }

    public synchronized void enemyTurn(String enemyTurn) {

    }

    public synchronized void playerTurn() {

    }

    private void startBattle() {

    }
    // Метод вычисления инициативы, чтобы понять кто ходит первый


    public String getActionToServer() {
        return actionToServer;
    }

    public void setActionToServer(String actionToServer) {
        this.actionToServer = actionToServer;
    }

    private void loadEnemyCards() {
        initMainController();

        List<EnemyCard> enemyCards = new ArrayList<>();
        currentRoom.getEnemies().forEach(el -> {
            enemyCards.add(new EnemyCard(el));
            System.out.println(el.getName());
        });

        enemyCards.forEach(el -> {
            el.getEnemyCard().setOnMouseClicked(event -> {
//                enemyCards.forEach(enemyCard -> enemyCard.getEnemyCard().setStyle("-fx-box-border: transparent;"));
//                Timeline timeline = EnemyCardController.setBorder(el.getEnemyCard());
//
//                chosenEnemy = el.getEnemy();
//                System.out.println("Chosen enemy = " + chosenEnemy.getName());
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
            FadeTransition ft = new FadeTransition(Duration.millis(800), enemyCard.getEnemyCard());
            ft.setFromValue(0);
            ft.setToValue(1);
            ft.setAutoReverse(false);
            ft.play();
        });
    }

    @SneakyThrows
    private void initMainController() {
        String path = "/fxml/main_v2.fxml";
        Parent parent;
        FXMLLoader loader = new FXMLLoader();
        parent = loader.load(getClass().getResourceAsStream(path));
        mainController = loader.getController();
    }

    public void animation() {

    }

    public EnemyCard getChosenEnemyCard() {
        return chosenEnemyCard;
    }

}

package KupidonTeam.controllers;

import KupidonTeam.characters.classes.enemies.Enemy;
import KupidonTeam.enums.Battlestate;
import KupidonTeam.gui.EnemyCard;
import KupidonTeam.gui.MainController;
import KupidonTeam.locations.Dungeon;
import KupidonTeam.player.Player;
import KupidonTeam.server.Connection;
import javafx.animation.*;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.util.Duration;
import lombok.SneakyThrows;


import java.util.*;
import java.util.stream.Stream;

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

    private Enemy chosenEnemy;

    public BattleController() {
    }

    public BattleController(Player mainPlayer, List<Dungeon> dungeonsList, FlowPane cardTable) {
        this.mainPlayer = mainPlayer;
        this.dungeonList = dungeonsList;
        this.cardTable = cardTable;
        initMainController();
        currentRoom = dungeonsList.get(0);
        server = Connection.getConnection();
        battlestate = Battlestate.PLAYERTURN;
        loadEnemyCards();
//        battleMode();

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
                enemyCards.forEach(enemyCard -> enemyCard.getEnemyCard().setStyle("-fx-box-border: transparent;"));
                setBorder(el.getEnemyCard());

                chosenEnemy = el.getEnemy();
                System.out.println("Chosen enemy #" + chosenEnemy.getEnemyId() + " = " + chosenEnemy.getName());
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

    public Enemy getChosenEnemy() {
        return chosenEnemy;
    }


    private void setBorder(Pane pane) {
        Color[] colors = Stream.of("tomato", "#961307", "#8e7c74", "#39100f", "#251a1a", "red", "#816d64")
                .map(Color::web)
                .toArray(Color[]::new);

        List<Border> list = new ArrayList<>();

        int[] mills = {-200};
        KeyFrame[] keyFrames = Stream.iterate(0, i -> i + 1)
                .limit(100)
                .map(i -> new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
                        new Stop[]{new Stop(0, colors[i % colors.length]),
                                new Stop(1, colors[(i + 1) % colors.length])}))
                .map(lg -> new Border(new BorderStroke(lg, BorderStrokeStyle.SOLID,
                        new CornerRadii(0), new BorderWidths(4))))
                .map(b -> new KeyFrame(Duration.millis(mills[0] += 200), new KeyValue(pane.borderProperty(),
                        b, Interpolator.EASE_IN)))
                .toArray(KeyFrame[]::new);

        Timeline timeline = new Timeline(keyFrames);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
}

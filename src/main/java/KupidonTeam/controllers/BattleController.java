//package KupidonTeam.controllers;
//
//import KupidonTeam.characters.classes.enemies.Enemy;
//import KupidonTeam.enums.Battlestate;
//import KupidonTeam.locations.Dungeon;
//import KupidonTeam.player.Player;
//import KupidonTeam.server.Connection;
//import lombok.SneakyThrows;
//
//import java.util.*;
//
//// TODO
//// Класс-контроллер боя
//public class BattleController {
//    private Battlestate battlestate;
//    private Player mainPlayer;
//    private List<Dungeon> dungeonList;
//    private Dungeon currentRoom;
//    private int[] passedRooms;
//    private String enemyTurn;
//    private Connection server;
//    //private EnemyContainer enemies;     // Класс-обертка над обычными врагами
//
//    public BattleController(Player mainPlayer,List<Dungeon> dungeonsList) {
//        this.mainPlayer = mainPlayer;
//        this.dungeonList = dungeonsList;
//        currentRoom = dungeonsList.get(0);
//        server = Connection.getConnection();
//        battleMode();
//    }
//
//    public void battle(){
//        int killedEnemies = 0;
//
//    }
//
//    @SneakyThrows
//    public synchronized void battleMode() {
//        boolean exitFlag = false;
//        server.
//        int killedEnemies = 0;
//        while (killedEnemies<currentRoom.getEnemies().size()) {
//            switch (battlestate) {
//                case PLAYERTURN:
//                    if(mainPlayer.getStats().getHits()<0){
//                        battlestate = Battlestate.LOSE;
//                        break;
//                    }
//                    playerTurn();
//
//                    wait();
//
//                    notify();
//                    break;
//                case ENEMYTURN:
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
//            }
//        }
//
//        battlestate = Battlestate.FREE;
//    }
//
//    public synchronized void enemyTurn(String enemyTurn){
//
//    }
//
//    public synchronized void playerTurn(){
//
//    }
//    // Метод вычисления инициативы, чтобы понять кто ходит первый
//
//}

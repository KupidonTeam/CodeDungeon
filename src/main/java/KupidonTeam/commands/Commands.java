package KupidonTeam.commands;

import KupidonTeam.characters.classes.enemies.Enemy;
import KupidonTeam.enums.Direction;
import KupidonTeam.locations.Dungeon;
import KupidonTeam.player.Player;
import KupidonTeam.server.Connection;

import java.util.List;

// TODO
// Класс-обертка для команд, не доделан, не трогай :)
public class Commands {
    private Player player;
    private String[] currentCommand;
    private Connection server;

    public Commands(Player player) {
        this.player = player;

    }

    public void executeCommand(String command) {
        currentCommand = command.split(" ");

        switch (currentCommand[0]) {
            case "/stats":
                getStats();
                break;
            case "/move":
                move();
                break;
            case "/attack":

            default:
                System.out.println("No such command");
        }

    }

    private void getStats() {
        System.out.println((player.getName() + player.getPlayerClass().toString()));
    }

    private void move() {
        System.out.println("move method");
        if (currentCommand.length < 2) {
            argumentMistake();
            return;
        }
        String direction = currentCommand[1].toUpperCase();
        if (directionInputCheck(direction)) {
            Direction dir = Direction.valueOf(direction);
            if (isAvailableDirection(dir)) {
                //переход в др локацию
            } else {
                wrongDirection();
            }
        }
    }

    private void attack() {
        if (currentCommand.length < 3) {
            wrongCommandInput();
            return;
        }
        if (player.getLocation() instanceof Dungeon) {
            if (enemyToAttackCheck((Dungeon) player.getLocation())&&skillCheck()){

            }
        } else {
            notDungeonWarning();
        }

    }

    private boolean enemyToAttackCheck(Dungeon dungeon) {
        for (Enemy enemy : dungeon.getEnemyContainer().getEnemies()) {
            if (enemy.getName().equalsIgnoreCase(currentCommand[1])) {
                return true;
            }
        }
        noSuchEnemy();
        return false;
    }

    private boolean skillCheck(){
        //TODO
        return false;
    }

    private boolean directionInputCheck(String direction) {
        for (Direction dir : Direction.values()) {
            if (dir.toString().equals(direction)) {
                return true;
            }
        }
        wrongCommandInput();
        return false;
    }

    private boolean isAvailableDirection(Direction direction) {
        return getAvailableDirections().contains(direction);
    }

    private List<Direction> getAvailableDirections() {
        return player.getLocation().getAvailableDirections();
    }

    private void wrongDirection() {
        System.out.println("Current location does not contain that direction");
    }

    private void wrongCommandInput() {
        System.out.println("No such command! Type /help to see the available commands");
    }

    private void argumentMistake() {
        System.out.println("Not enough arguments! \nType /help to get list of available commands");
    }

    private void notDungeonWarning() {
        System.out.println("You are not able to attack in peaceful territories");
    }

    private void noSuchEnemy(){
        System.out.println("No such enemy");
    }

}
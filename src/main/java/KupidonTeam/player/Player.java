package KupidonTeam.player;

import KupidonTeam.characters.classes.Entity;
import KupidonTeam.characters.classes.Warrior;
import KupidonTeam.commands.Commands;
import KupidonTeam.commands.CommandsListener;
import KupidonTeam.enums.Direction;
import KupidonTeam.items.Item;
import KupidonTeam.locations.Dungeon;
import KupidonTeam.locations.Room;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@Getter
@Setter
@AllArgsConstructor
public class Player extends Entity implements CommandsListener {
    private long id;
    private Entity playerClass;  // TODO Рационально заменить на класс Stats, т.к это х-р игрока которые должны изменяться
    private String name;
    private int raceId;
    private int equipmentId;
    private Inventory playerInventory;
    private int lvl;
    private Room location;
    private int experience;
    private Commands commandExecutor;

    private Scanner input = new Scanner(System.in);

    public Player() {
        playerClass = new Warrior();
        playerInventory = new Inventory();
        location = new Dungeon();
        commandExecutor = new Commands(this);
    }

    public Player(long id, Entity playerClass, String name, Room location) {
        this.id = id;
        this.playerClass = playerClass;
        this.name = name;
        this.location = location;
        commandExecutor = new Commands(this);
    }

    @Override
    public void userInput() {
        String string = input.next();

        // TODO
        // Создать интерфейс маркер для всех метолов которые может вып пользователь
        Map<String, CommandsListener> commandList = new HashMap<>();

        if (commandList.containsKey(input)) {
            commandList.get(input);
            String buffer = input.nextLine();

            System.out.println("buffer = " + buffer);

            // Если первый символ не '/' , то посылае сообщение в чат
            if (buffer.charAt(0) != '/') {
                chat(buffer);
            }
            // Иначе разбиваем и отсылаем команду в обработчик
            else {
                String[] command = new String[3];
                command = buffer.split(" ");
                commandExecutor.executeCommand(command);
            }
        }
    }

    @Override
    public void chat(String message) {

    }

    public void playerDefeat() {
        // TODO
    }

    @Override
    public String getStats() {
        return name + "\n" + playerClass.toString();
    }

    @Override
    public void move(Direction direction) {
        if (getAvailableDirections().contains(direction)) {
            // TODO
            //переместиться
        } else {
            System.out.println("Sorry, no such direction");
        }
    }

    public void playerTurn() {
        userInput();
    }

    public boolean isAlive() {
        return playerClass.getHp() > 0;
    }

    public void playerRewards(List<Item> rewards) {
        // TODO
    }

    @Override
    public List<Direction> getAvailableDirections() {
        return location.getAvailableDirections();
    }

    @Override
    public void noSuchCommand() {

    }
}

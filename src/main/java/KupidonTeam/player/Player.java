package KupidonTeam.player;

import KupidonTeam.characters.classes.Entity;
import KupidonTeam.characters.classes.Warrior;
import KupidonTeam.controllers.CommandsListener;
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
    private Entity playerClass;
    private String name;
    private int raceId;
    private int equipmentId;
    private int lvl;
    private Room location;
    private int experience;

    private Scanner input = new Scanner(System.in);

    public Player() {
        playerClass = new Warrior();
        playerInventory = new Inventory();
        location = new Dungeon();
    }

    public Player(long id, Entity playerClass, String name, Room location) {
        this.id = id;
        this.playerClass = playerClass;
        this.name = name;
        this.location = location;
    }

    @Override
    public void userInput() {
        String string = input.next();

        // TODO
        //создать интерфейс маркер для всех метолов которые может вып пользователь
        Map<String,CommandsListener> commandList = new HashMap<>();

        if(commandList.containsKey(input)){
            commandList.get(input);
        }
    }

    public void playerDefeat(){
        // TODO
    }

    @Override
    public void chat() {

    }

    @Override
    public String myStats() {
        return name + "\n" + playerClass.toString();
    }

    @Override
    public void move(Direction direction) {
        if(getAvailableDirections().contains(direction)){
            // TODO
            //переместиться
        }
        else{
            System.out.println("Sorry, no such direction");
        }
    }

    public void playerTurn() {
        userInput();
    }

    public boolean isAlive() {
        return playerClass.getHp() > 0 ? true : false;
    }

    public void playerRewards(List<Item> rewards){
        // TODO
    }

    @Override
    public List<Direction> getAvailableDirections() {
        return location.getAvailableDirections();
    }
}

package KupidonTeam.commands;

import KupidonTeam.enums.Direction;

import java.util.List;

public interface BaseCommands {
    void chat(String message);

    String getStats();

    void move(Direction direction);

    List<Direction> getAvailableDirections();

    default void noSuchCommand(){
        System.out.println("No such command!");
    }
}

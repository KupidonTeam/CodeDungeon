package KupidonTeam.controllers;

import KupidonTeam.locations.Direction;

import java.util.List;

// Интерфейс вводимых комманд

public interface CommandsListener extends BaseCommands  {
    void userInput();

    void move(Direction direction);

    List<Direction> getAvailableDirections();
}

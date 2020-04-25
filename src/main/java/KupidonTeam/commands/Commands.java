package KupidonTeam.commands;

import KupidonTeam.enums.Direction;
import KupidonTeam.player.Player;

import java.util.Optional;

// TODO
// Класс-обертка для команд, не доделан, не трогай :)
public class Commands {
    private Player player;
    private String[] currentCommand;

    public Commands(Player player) {
        this.player = player;
    }

    public void executeCommand(String... command) {
        Optional<String> argument1 = Optional.ofNullable(command[1]);
        Optional<String> argument2 = Optional.ofNullable(command[2]);

        currentCommand = command;

        switch (currentCommand[0]) {
            case "/stats":
                player.getStats();

                break;
            case "/move":
                if (argument1.isPresent() && isDirectionValue(argument1.toString())) {
                    isDirectionValue(argument1.toString());
                    player.move(Direction.valueOf(currentCommand[1]));
                } else {
                    argumentMistake();
                }

                break;
            default:
                System.out.println("No such command");
        }

    }

    private boolean isDirectionValue(String val) {
        System.out.println("Checking dirValue");

        for (Direction direction : Direction.values()) {
            if (direction.toString().equals(val.toUpperCase())) {
                return true;
            }
        }

        return false;
    }

    private void argumentMistake() {
        System.out.println("Not enough arguments! \nType /help to get list of available commands");
    }
}

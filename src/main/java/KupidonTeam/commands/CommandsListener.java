package KupidonTeam.commands;

// Интерфейс вводимых комманд
public interface CommandsListener extends BaseCommands {
    void userInput();

    @Override
    default void chat(String message) {
        System.out.println("Your simple message : "+message);
    }
}

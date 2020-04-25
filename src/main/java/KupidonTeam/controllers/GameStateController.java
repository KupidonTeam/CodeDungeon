package KupidonTeam.controllers;

import KupidonTeam.enums.Battlestate;
import KupidonTeam.enums.Gamestate;
import KupidonTeam.player.Player;

public class GameStateController {
    private Gamestate currentState;
    private Battlestate battlestate;
    private Player mainPlayer;

    public GameStateController() {
        mainPlayer = new Player();
        currentState = Gamestate.FREE;
        battlestate = Battlestate.FREE;
    }

    public void stateSwitcher() {
        while (true) {
            switch (currentState) {
                case FREE:
                    mainPlayer.userInput();
                    break;
                case BATTLE:
                    battlestate = Battlestate.START;
                    new BattleController(mainPlayer);
                    currentState = Gamestate.FREE;
                    break;
            }
        }
    }
}

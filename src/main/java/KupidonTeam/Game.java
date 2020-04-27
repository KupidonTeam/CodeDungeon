package KupidonTeam;

import KupidonTeam.characters.classes.enemies.Enemy;
import KupidonTeam.characters.classes.enemies.Orc;
import KupidonTeam.controllers.GameStateController;
import KupidonTeam.enums.Direction;
import KupidonTeam.player.Player;
import KupidonTeam.server.Connection;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    public static void main(String[] args) throws IOException {

        Scanner in = new Scanner(System.in);
        //new GameStateController().stateSwitcher();
        Connection connection = new Connection();


//        Player player = new Player();
//        player.setId(100);
//        player.setName("Player");
//
//        List<Direction> directions = new ArrayList<>();
//        directions.add(Direction.E);
//        directions.add(Direction.S);
//        directions.add(Direction.W);
//
//        List<Enemy> enemies = new ArrayList<>();
//        enemies.add(new Orc());
    }
}

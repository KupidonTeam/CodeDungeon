package KupidonTeam;

import KupidonTeam.enums.Direction;
import KupidonTeam.characters.classes.enemies.Enemy;
import KupidonTeam.characters.classes.enemies.Orc;
import KupidonTeam.locations.Dungeon;
import KupidonTeam.locations.Room;
import KupidonTeam.player.Player;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main( String[] args )
    {
        Player player = new Player();
        player.setId(100);
        player.setName("Player");

        List<Direction> directions = new ArrayList<>();
        directions.add(Direction.E);
        directions.add(Direction.S);
        directions.add(Direction.W);

        List<Enemy> enemies = new ArrayList<>();
        enemies.add(new Orc());

        Room room = new Dungeon(1, directions, "some descr", enemies);
    }
}

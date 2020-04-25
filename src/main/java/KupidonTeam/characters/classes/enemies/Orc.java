package KupidonTeam.characters.classes.enemies;

import KupidonTeam.characters.classes.Warrior;
import KupidonTeam.enums.Direction;

public class Orc extends Enemy {
    private Warrior mobClass;
    public Orc(){
        mobClass = new Warrior();
    }

    @Override
    public void move(Direction direction) {
        // TODO
    }
}

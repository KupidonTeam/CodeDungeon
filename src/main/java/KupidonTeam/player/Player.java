package KupidonTeam.player;

import KupidonTeam.characters.classes.Entity;
import KupidonTeam.characters.classes.Warrior;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Player extends Entity {
    private long id;
    private Entity playerClass;
    private String name;
    private int lvl;
    private int experience;
    private int raceId;
    private int equipmentId;

    public Player(){
        playerClass = new Warrior();
    }

    public Player(Entity playerClass){
        this.playerClass = playerClass;
    }
}

package KupidonTeam.characters.classes;

import KupidonTeam.controllers.Action;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class Entity implements Action {
    private int hp;
    private int armorClass;
    private double speed;
    private int strength;
    private int dexterity;
    private int constitution;
    private int intelligence;
    private int wisdom;
    private int charisma;
    private int chance;

    @Override
    public String toString() {
        return "Entity{" +
                "hp=" + hp +
                ", armorClass=" + armorClass +
                ", speed=" + speed +
                ", strength=" + strength +
                ", dexterity=" + dexterity +
                ", constitution=" + constitution +
                ", intelligence=" + intelligence +
                ", wisdom=" + wisdom +
                ", charisma=" + charisma +
                ", chance=" + chance +
                '}';
    }
}

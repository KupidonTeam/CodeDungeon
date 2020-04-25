package KupidonTeam.characters.classes;

import KupidonTeam.enums.Direction;

public class Warrior extends Entity {
    public Warrior(){
        setHp(100);
        setStrength(10);
        setArmorClass(5);
        setChance(6);
        setCharisma(2);
        setConstitution(3);
        setDexterity(6);
        setIntelligence(11);
        setSpeed(11);
        setWisdom(10);
    }

    public Warrior(int hp,int strength, int armorClass, int chance, int charisma, int constitution,  int dexterity, int intelligence, int speed, int wisdom){
        setHp(hp);
        setStrength(strength);
        setArmorClass(armorClass);
        setChance(chance);
        setCharisma(charisma);
        setConstitution(constitution);
        setDexterity(dexterity);
        setIntelligence(intelligence);
        setSpeed(speed);
        setWisdom(wisdom);
    }

    @Override
    public void move(Direction direction) {
        // TODO
    }
}

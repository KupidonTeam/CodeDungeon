package KupidonTeam.characters.classes;

import KupidonTeam.characters.classes.skills.behavior.Skill;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;


public class Warrior extends Entity {
    private List<Skill> skills;

    public Warrior() {
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
        skills = new LinkedList<>();
    }

    public Warrior(int hp, int strength, int armorClass, int chance, int charisma, int constitution, int dexterity,
                   int intelligence, int speed, int wisdom, List<Skill> skills) {
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
        setSkills(skills);
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public List<Skill> getSkills() {
        return skills;
    }
}

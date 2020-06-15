package KupidonTeam.characters.classes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Stats {

    private String race;
    private int armorClass;
    private int hits;
    private double speed;
    private int strength;
    private int dexterity;
    private int intelligence;
    private int wisdom;
    private int chance;
    private int constitution;


    @Override
    public String toString() {
        return
                "Hits = " + hits +
                        "\nArmor class=" + armorClass +
                        "\nSpeed =" + speed +
                        "\nStrength =" + strength +
                        "\nDexterity =" + dexterity +
                        "\nConstitution =" + constitution +
                        "\nIntelligence =" + intelligence +
                        "\nWisdom =" + wisdom +
                        "\nChance =" + chance
                ;
    }
}

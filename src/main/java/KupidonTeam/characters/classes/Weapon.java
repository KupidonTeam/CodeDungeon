package KupidonTeam.characters.classes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Weapon {
    private long id;
    private String name;
    private double price;
    private int damageMin;
    private double weight;
    private int damageMax;
}

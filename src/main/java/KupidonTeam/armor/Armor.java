package KupidonTeam.armor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Armor {
    private long id;
    private String name;
    private double armorPrice;
    private double armorWeight;
}

package KupidonTeam.model.items;

import KupidonTeam.model.exceptions.LessThanZeroException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Weapon extends Item {
    private int damageMin;
    private int damageMax;

    public Weapon(int id, String name, double price, int damageMin, int damageMax, double weight) {
        setId(id);
        setName(name);
        setDamageMax(damageMax);
        setDamageMin(damageMin);

        try {
            setPrice(price);
            setWeight(weight);
        } catch (LessThanZeroException ex) {
            ex.printStackTrace();
        }
    }
}

package KupidonTeam.items;

import KupidonTeam.exceptions.LessThanZeroException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Armor extends Item {
    public Armor(int id, String name, double weight, double price) {
        setId(id);
        setName(name);
        try {
            setWeight(weight);
            setPrice(price);
        } catch (LessThanZeroException ex) {
            ex.printStackTrace();
        }

    }
}

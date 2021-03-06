package KupidonTeam.model.items;

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

    @Override
    public String toString() {
        return String.format("Armor{" +
                "name = %s\n" +
                "weight = %f\n" +
                "price = %f\n" +
                "id = %d\n" +
                "lvl = null\n" +
                '}', getName(), getWeight(), getPrice(), getId());
    }
}

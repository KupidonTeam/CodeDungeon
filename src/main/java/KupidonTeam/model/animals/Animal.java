package KupidonTeam.model.animals;

import KupidonTeam.exceptions.LessThanZeroException;
import KupidonTeam.model.items.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Animal extends Item {
    private double speed;
    private long capacity;

    public Animal(int id, String name, double price, double speed, int capacity) {
        setId(id);
        setName(name);
        setSpeed(speed);
        setCapacity(capacity);

        try {
            setPrice(price);
        } catch (LessThanZeroException ex) {
            ex.printStackTrace();
        }
    }
}

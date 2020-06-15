package KupidonTeam.animals;

import KupidonTeam.exceptions.LessThanZeroException;
import KupidonTeam.items.Item;
import javafx.animation.Animation;
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

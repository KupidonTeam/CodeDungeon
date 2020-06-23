package KupidonTeam.model.items;

import KupidonTeam.model.exceptions.LessThanZeroException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Food extends Item {
    public Food(int id, String foodName, double foodPrice) {
        setId(id);
        setName(foodName);

        try {
            setPrice(foodPrice);
            setWeight(0);
        } catch (LessThanZeroException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

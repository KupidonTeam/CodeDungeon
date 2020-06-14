package KupidonTeam.items;

// Класс-родитель для всех предметов в игре

import KupidonTeam.exceptions.LessThanZeroException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Item {
    private int id;
    private String name;
    private double weight;
    private double price;

    protected Item() {
        weight = 0;
        price = 0;
    }

    protected Item(String name, int weight, double price) {
        this.name = name;
        this.weight = weight;
        this.price = price;
    }

    protected Item(int weight, double price) throws LessThanZeroException {
        setWeight(weight);
        setPrice(price);
    }

    protected void setWeight(double weight) throws LessThanZeroException {
        if (weight < 0) {
            throw new LessThanZeroException(weight);
        }

        this.weight = weight;
    }

    protected void setPrice(double price) throws LessThanZeroException {
        if (price < 0) {
            throw new LessThanZeroException(price);
        }

        this.price = price;
    }

    @Override
    public String toString() {
        return
                "\nname='" + name +
                        "\nweight=" + weight +
                        "\nprice=" + price;
    }
}

package KupidonTeam.player;

import KupidonTeam.items.Item;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Inventory {
    private long capacity;
    private List<Item> items;

    public Inventory() {
        capacity = 10;
    }

//    public Inventory(Player player) {
//        capacity = player.getStrength() * 15;
//    }

    public void addItem(Item item) {
        items.add(item);
    }
}

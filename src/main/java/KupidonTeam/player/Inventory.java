package KupidonTeam.player;

import KupidonTeam.items.Item;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Inventory {
    private long maxWheight;
    private List<Item> items;


    public Inventory(Player player) {
        maxWheight = player.getStats().getStrength() * 15;
    }

    public void addItem(Item item) {
        items.add(item);
    }
}

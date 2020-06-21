package KupidonTeam.player;

import KupidonTeam.items.Item;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
public class Inventory {
    private long maxWheight;
    private List<Item> items;


    public Inventory(Player player) {
        items = new LinkedList<>();
        maxWheight = player.getStats().getStrength() * 15;
    }

    public void add(Item item) {
        items.add(item);
    }

    public void addAll(List<? extends Item> itemList) {
        items.addAll(itemList);
    }
}

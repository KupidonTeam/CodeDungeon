package KupidonTeam.model.characters.player;

import KupidonTeam.model.items.Item;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
public class Inventory {
    private long maxWeight;
    private List<Item> items;

    public Inventory(Player player) {
        items = new LinkedList<>();
        maxWeight = player.getStats().getStrength() * 15;
    }

    public void add(Item item) {
        items.add(item);
    }

    public void addAll(List<? extends Item> itemList) {
        items.addAll(itemList);
    }
}

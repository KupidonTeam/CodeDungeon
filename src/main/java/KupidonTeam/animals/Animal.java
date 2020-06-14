package KupidonTeam.animals;

import KupidonTeam.items.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Animal extends Item {
    private double speed;
    private long capacity;
}

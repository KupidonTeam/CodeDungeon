package KupidonTeam.player;

import KupidonTeam.items.Weapon;
import KupidonTeam.items.Armor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Equipment {
    // TODO better
    private long id;
    private Armor armor;
    private Weapon weapon;
}
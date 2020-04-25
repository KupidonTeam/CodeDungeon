package KupidonTeam.items;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Weapon extends Item {
    private int damageMin;
    private int damageMax;
}

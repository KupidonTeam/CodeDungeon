package KupidonTeam.food;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Food {
    private long id;
    private String name;
    private double price;
}

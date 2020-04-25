package KupidonTeam.animals;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Animal {
    private long id;
    private String name;
    private double price;
    private double speed;
    private long capacity;
}

package KupidonTeam.view;

import KupidonTeam.controllers.BattleController;
import javafx.scene.shape.Circle;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Dot {
    private int id;
    private int x;
    private int y;
    private Circle dot;
    private BattleController battleController;

    public Dot(int id, int x, int y) {
        setId(id);
        setX(x);
        setY(y);

    }

    public void setDot(Circle circle) {
        if (this.dot == null) {
            this.dot = circle;
        } else {
            dot.setFill(circle.getFill());
        }
        dot.setOnMouseClicked(event -> {
            System.out.println("Clicked on room = " + id);
            battleController = BattleController.getInstance();
            battleController.changeRoom(id);
        });
    }
}

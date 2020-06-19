package KupidonTeam.gui;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import lombok.AllArgsConstructor;
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

    public Dot(int id, int x, int y) {
        setId(id);
        setX(x);
        setY(y);
    }

    public void setDot(Circle circle) {
        this.dot = circle;
        dot.setOnMouseClicked(event -> System.out.println("Clicked on room = " + id));
    }


}

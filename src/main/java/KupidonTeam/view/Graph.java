package KupidonTeam.view;

import javafx.animation.ScaleTransition;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;

public class Graph extends AnchorPane {
    private static Stage currentStage;
    private ArrayList<Dot> dots = new ArrayList<>();

    public Graph() {
        dots.add(new Dot(0, 20, 140));
        dots.add(new Dot(1, 70, 30));
        dots.add(new Dot(2, 70, 250));
        dots.add(new Dot(3, 210, 30));
        dots.add(new Dot(4, 210, 250));
        dots.add(new Dot(5, 260, 140));
        dots.add(new Dot(6, 110, 110));
        dots.add(new Dot(7, 110, 170));
        dots.add(new Dot(8, 170, 110));
        dots.add(new Dot(9, 170, 170));
    }

    private int getAnotherDot(int[] route, int dot) {
        for (int c : route) {
            if (c != dot) {
                return c;
            }
        }

        return dot;
    }

    private boolean contains(Integer[] rooms, int room) {
        for (int i : rooms) {
            if (i == room) {
                return true;
            }
        }

        return false;
    }

    private void paintRooms(AnchorPane pane, Integer[] rooms, Integer[] visited, int currentRoom) {
        for (int i = 0; i < rooms.length; i++) {
            if (i != currentRoom && !contains(visited, i)) {
                if (pane.getChildren().contains(dots.get(i).getDot())) {
                    pane.getChildren().remove(dots.get(i).getDot());
                }
                dots.get(i).setDot(new Circle(dots.get(i).getX(), dots.get(i).getY(), 8, Color.rgb(129, 109, 100)));
                DropShadow shadow = new DropShadow();
                shadow.setColor(Color.rgb(129, 109, 100));
                dots.get(i).getDot().setEffect(shadow);
                pane.getChildren().add(dots.get(i).getDot());

            }
        }
    }

    private void paintCurrentRoom(AnchorPane pane, int room) {
        if (pane.getChildren().contains(dots.get(room).getDot())) {
            pane.getChildren().remove(dots.get(room).getDot());
        }
        dots.get(room).setDot(new Circle(dots.get(room).getX(), dots.get(room).getY(), 10, Color.RED));
        dots.get(room).getDot().setEffect(new GaussianBlur());

        ScaleTransition scaleTransition = new ScaleTransition();

        //Setting the duration for the transition
        scaleTransition.setDuration(Duration.millis(60 * 1000));

        //Setting the node for the transition
        scaleTransition.setNode(dots.get(room).getDot());

        //Setting the dimensions for scaling
        scaleTransition.setByY(0.3);
        scaleTransition.setByX(0.3);

        //Setting the cycle count for the translation
        scaleTransition.setCycleCount(300);

        //Setting auto reverse value to true
        scaleTransition.setAutoReverse(false);

        //Playing the animation
        scaleTransition.play();
        pane.getChildren().add(dots.get(room).getDot());
    }

    private void paintVisitedRooms(AnchorPane pane, Integer[] rooms) {
        for (int i = 0; i < rooms.length; i++) {
            if (pane.getChildren().contains(dots.get(i).getDot())) {
                pane.getChildren().remove(dots.get(i).getDot());
            }
            dots.get(i).setDot(new Circle(dots.get(i).getX(), dots.get(i).getY(), 10, Color.rgb(129, 109, 100)));
            dots.get(i).getDot().setEffect(new GaussianBlur());

            pane.getChildren().add(dots.get(i).getDot());
        }
    }

    private void paintRoutes(AnchorPane pane, int[][] routes) {
        for (int[] route : routes) {
            Line line = new Line(dots.get(route[0]).getX(), dots.get(route[0]).getY(),
                    dots.get(route[1]).getX(), dots.get(route[1]).getY());
            line.setStroke(Color.rgb(129, 109, 100));
            line.setStrokeWidth(2);
            pane.getChildren().add(line);
        }
    }


    public void updateDungeon(Integer[] rooms, int[][] routes, Integer[] visitedRooms, int currentRoom) {
        paintRoutes(this, routes);
        paintRooms(this, rooms, visitedRooms, currentRoom);
        paintCurrentRoom(this, currentRoom);
        paintVisitedRooms(this, visitedRooms);

        //вход всегда синий
        dots.get(0).getDot().setFill(Color.YELLOW);
    }

    public AnchorPane getPane() {
        return this;
    }
}

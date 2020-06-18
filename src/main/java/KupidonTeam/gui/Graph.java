package KupidonTeam.gui;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.effect.*;
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


    public Graph(int rooms[], int[][] routes) {

        dots.add(new Dot(1, 20, 140));
        dots.add(new Dot(2, 70, 30));
        dots.add(new Dot(3, 70, 250));
        dots.add(new Dot(4, 210, 30));
        dots.add(new Dot(5, 210, 250));
        dots.add(new Dot(6, 260, 140));
        dots.add(new Dot(7, 110, 110));
        dots.add(new Dot(8, 110, 170));
        dots.add(new Dot(9, 170, 110));
        dots.add(new Dot(10, 170, 170));
    }

    private int getAnotherDot(int[] route, int dot) {
        for (int c : route) {
            if (c != dot) {
                return c;
            }
        }

        return dot;
    }

    private boolean contains(int[] rooms, int room) {
        for (int i : rooms) {
            if (i == room) {
                return true;
            }
        }

        return false;
    }

    private void paintRooms(AnchorPane pane, int[] rooms, int[] visited, int currentRoom) {
        for (int i = 0; i < rooms.length; i++) {
            if (i != currentRoom && !contains(visited, i)) {
                Circle circle = new Circle(dots.get(i).getX(), dots.get(i).getY(), 8, Color.rgb(129, 109, 100));
                DropShadow shadow = new DropShadow();
                shadow.setColor(Color.rgb(129, 109, 100));
                circle.setEffect(shadow);
                pane.getChildren().add(circle);
            }
        }
    }

    private void paintCurrentRoom(AnchorPane pane, int room) {
        Circle circle = new Circle(dots.get(room).getX(), dots.get(room).getY(), 10, Color.rgb(142, 124, 116));
        circle.setEffect(new GaussianBlur());

        ScaleTransition scaleTransition = new ScaleTransition();

        //Setting the duration for the transition
        scaleTransition.setDuration(Duration.millis(60 * 1000));

        //Setting the node for the transition
        scaleTransition.setNode(circle);

        //Setting the dimensions for scaling
        scaleTransition.setByY(0.3);
        scaleTransition.setByX(0.3);

        //Setting the cycle count for the translation
        scaleTransition.setCycleCount(300);

        //Setting auto reverse value to true
        scaleTransition.setAutoReverse(false);

        //Playing the animation
        scaleTransition.play();

        pane.getChildren().add(circle);
    }

    private void paintVisitedRooms(AnchorPane pane, int[] rooms) {
        for (int i = 0; i < rooms.length; i++) {
            Circle circle = new Circle(dots.get(i).getX(), dots.get(i).getY(), 10, Color.rgb(129, 109, 100));
            circle.setEffect(new GaussianBlur());
            pane.getChildren().add(circle);
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


    public void updateDungeon() {

        int[] rooms = {0, 1, 2, 3, 4, 5, 6};
        int[][] routes = {{0, 1}, {0, 4}, {1, 2}, {1, 5}, {2, 3}, {2, 5}, {2, 6}, {3, 5}, {5, 6}};

        paintRoutes(this, routes);
        paintRooms(this, rooms, new int[]{0, 1, 2}, 3);
        paintCurrentRoom(this, 3);
        paintVisitedRooms(this, new int[]{0, 1, 2});
    }

    public AnchorPane getPane() {
        return this;
    }
}

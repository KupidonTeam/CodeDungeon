package KupidonTeam.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Graph extends Application {
    private static Stage currentStage;
    private ArrayList<Dot> dots = new ArrayList<>();

    public Graph() {
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

    private void paintRooms(AnchorPane pane, int[] rooms) {
        for (int i = 0; i < rooms.length; i++) {
            pane.getChildren().add(new Circle(dots.get(i).getX(), dots.get(i).getY(), 10, Color.rgb(129, 109, 100)));
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

    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane pane = new AnchorPane();

        int[] rooms = {0, 1, 2, 3, 4, 5, 6};
        int[][] routes = {{0, 1}, {0, 4}, {1, 2}, {1, 5}, {2, 3}, {2, 5}, {2, 6}, {3, 5}, {5, 6}};

        paintRooms(pane, rooms);
        paintRoutes(pane, routes);

        currentStage = primaryStage;
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Graph");
        primaryStage.show();
    }
}

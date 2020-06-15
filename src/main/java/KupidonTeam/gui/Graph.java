package KupidonTeam.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Graph extends Application {
    private static Stage currentStage;

    private int getAnotherDot(int[] route, int dot) {
        for (int c : route) {
            if (c != dot) {
                return c;
            }
        }

        return dot;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane pane = new AnchorPane();

        int[] rooms = {0, 1, 2, 3, 4, 5, 6};
        int[][] routes = {{0, 1}, {0, 4}, {1, 2}, {1, 5}, {2, 3}, {2, 5}, {2, 6}, {3, 5}, {5, 6}};

        int x = 30;
        int y = 30;
        int i = 0;

        for (int room : rooms) {
            pane.getChildren().add(new Circle(x +  i / 3 * 30, y + i % 3 * 30, 5, Color.BLACK));

            for (int[] route : routes) {
                for (int dot : route) {

                    if (i == dot) {
                        int indexOfAnotherDot = getAnotherDot(route, dot);
                        pane.getChildren().add(new Line(x +  i / 3 * 30,
                                y + i % 3 * 30, x +  indexOfAnotherDot / 3 * 30, y + indexOfAnotherDot % 3 * 30));
                    }
                }
            }

            i++;
        }

        currentStage = primaryStage;
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Graph");
        primaryStage.show();
    }
}


package KupidonTeam.controllers;

import KupidonTeam.utils.SoundPlayer;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class ChooseMenuController {
    private MainController mainController;
    private Pane pane;

    public ChooseMenuController(MainController mainController, Pane pane) {
        this.pane = pane;
        this.mainController = mainController;
    }

    public void choosePaneMenu() {
        SoundPlayer soundPlayer = new SoundPlayer();
        ImageView shopImg = new ImageView(new Image(getClass().getResourceAsStream("/assets/weapons/Coin.png")));
        ImageView dungeonImg = new ImageView(new Image(getClass().getResourceAsStream("/assets/weapons/sword.png")));
        ImageView pvpImg = new ImageView(new Image(getClass().getResourceAsStream("/assets/weapons/Short Bow.png")));

        FlowPane shopPane = new FlowPane();
        shopPane.setAlignment(Pos.CENTER);
        Label shopLabel = new Label("Shop");
        shopLabel.setStyle("-fx-text-fill: #816d64");
        shopLabel.setFont(new Font("Century", 18));

        VBox shopVBox = new VBox();
        shopVBox.setAlignment(Pos.CENTER);
        shopVBox.setSpacing(30);
        shopVBox.getChildren().addAll(shopLabel, shopImg);
        shopPane.getChildren().add(shopVBox);
        shopPane.setStyle("" +
                "-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #201b1b, #39100f, #201b1b);" +
                "-fx-border-color :  #8e7c74;" +
                "-fx-border-width : 2 1 2 2;");

        FlowPane dungeonPane = new FlowPane();
        dungeonPane.setAlignment(Pos.CENTER);
        Label dungeonLabel = new Label("Dungeon");
        dungeonLabel.setStyle("-fx-text-fill: #816d64;");
        dungeonLabel.setFont(new Font("Century", 18));

        VBox dungeonVBox = new VBox();
        dungeonVBox.setAlignment(Pos.CENTER);
        dungeonVBox.setSpacing(30);
        dungeonVBox.getChildren().addAll(dungeonLabel, dungeonImg);
        dungeonPane.getChildren().add(dungeonVBox);
        dungeonPane.setStyle("" +
                "-fx-background-color:  linear-gradient(from 25% 25% to 100% 100%, #201b1b, #39100f, #201b1b);" +
                "-fx-border-color :  #8e7c74;" +
                "-fx-border-width : 2 1 2 1;");


        FlowPane pvpPane = new FlowPane();
        pvpPane.setAlignment(Pos.CENTER);
        Label pvpLabel = new Label("PvP");
        pvpLabel.setStyle("-fx-text-fill: #816d64;");
        pvpLabel.setFont(new Font("Century", 18));

        VBox pvpVBox = new VBox();
        pvpVBox.setAlignment(Pos.CENTER);
        pvpVBox.setSpacing(30);
        pvpVBox.getChildren().addAll(pvpLabel, pvpImg);
        pvpPane.getChildren().add(pvpVBox);
        pvpPane.setStyle("" +
                "-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #201b1b, #39100f, #201b1b);" +
                "-fx-border-color :  #8e7c74;" +
                "-fx-border-width : 2 2 2 1;");

        // On Mouse Entered Effects
        shopPane.setCursor(Cursor.HAND);
        shopPane.setOnMouseClicked(event -> mainController.openShop());
        shopPane.setOnMouseEntered(event -> {
            soundPlayer.shop();
            shopPane.setStyle(
                    "-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #201b1b, yellow, #201b1b);" +
                            "-fx-border-color :  #8e7c74;" +
                            "-fx-border-width : 2 1 2 1;");
            shopLabel.setStyle("-fx-text-fill: #201b1b;");
        });
        shopPane.setOnMouseExited(event -> {
            shopPane.setStyle(
                    "-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #201b1b, #39100f, #201b1b);" +
                            "-fx-border-color :  #8e7c74;" +
                            "-fx-border-width : 2 1 2 1;");
            shopLabel.setStyle("-fx-text-fill: #816d64;");
        });

        dungeonPane.setCursor(Cursor.HAND);
        dungeonPane.setOnMouseClicked(event -> mainController.getDungeonSkeleton());
        dungeonPane.setOnMouseEntered(event -> {
            soundPlayer.dungeon();
            dungeonPane.setStyle(
                    "-fx-background-color:  linear-gradient(from 25% 25% to 100% 100%, #201b1b, #816d64, #201b1b);" +
                            "-fx-border-color :  #8e7c74;" +
                            "-fx-border-width : 2 1 2 1;");
            dungeonLabel.setStyle("-fx-text-fill: #201b1b;");
        });
        dungeonPane.setOnMouseExited(event -> {
            dungeonPane.setStyle(
                    "-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #201b1b, #39100f, #201b1b);" +
                            "-fx-border-color :  #8e7c74;" +
                            "-fx-border-width : 2 1 2 1;");
            dungeonLabel.setStyle("-fx-text-fill: #816d64;");
        });

        pvpPane.setCursor(Cursor.HAND);
        pvpPane.setOnMouseEntered(event -> {
            soundPlayer.pvp();
            pvpPane.setStyle(
                    "-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #201b1b, darkred, #201b1b);" +
                            "-fx-border-color :  #8e7c74;" +
                            "-fx-border-width : 2 2 2 1;");
        });
        pvpPane.setOnMouseExited(event -> pvpPane.setStyle(
                "-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #201b1b, #39100f, #201b1b);" +
                        "-fx-border-color :  #8e7c74;" +
                        "-fx-border-width : 2 1 2 1;"));

        shopPane.setPrefWidth(220);
        shopPane.setPrefHeight(430);

        dungeonPane.setPrefWidth(220);
        dungeonPane.setPrefHeight(430);

        pvpPane.setPrefWidth(220);
        pvpPane.setPrefHeight(430);

        HBox hBox = new HBox();
        hBox.getChildren().addAll(shopPane, dungeonPane, pvpPane);

        pane.getChildren().add(hBox);
    }
}

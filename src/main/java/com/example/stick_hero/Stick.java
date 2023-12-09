package com.example.stick_hero;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Stick extends Rectangle {

    public Stick(double initialWidth, double initialHeight){

    }

    public static Rectangle makeStick(){
        Rectangle rectangleStick = new Rectangle();
        rectangleStick.setArcHeight(5.0);
        rectangleStick.setArcWidth(5.0);
        rectangleStick.setHeight(5.0);
        rectangleStick.setWidth(5.0);
        rectangleStick.setX(262.0);
        rectangleStick.setY(298.0);
        rectangleStick.setStroke(Color.BLACK);
        rectangleStick.setStrokeType(javafx.scene.shape.StrokeType.INSIDE);
        AnchorPane anchor = (AnchorPane) HelloApplication.game_scene.lookup("#anchor_pane_game");
        rectangleStick.setId("rectangle_stick");
        anchor.getChildren().add(rectangleStick);
        System.out.println("Stick added to scene.");

        return rectangleStick;
    }

}

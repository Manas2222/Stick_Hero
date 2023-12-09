package com.example.stick_hero;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Stick extends Rectangle {

    public Stick(double initialWidth, double initialHeight){

    }

    public static Rectangle makeStick(){
        Rectangle rectangleStick = new Rectangle();
        rectangleStick.setArcHeight(10.0);
        rectangleStick.setArcWidth(10.0);
        rectangleStick.setHeight(10.0);
        rectangleStick.setLayoutX(265.0);
        rectangleStick.setLayoutY(292.0);
        rectangleStick.setStroke(Color.BLACK);
        rectangleStick.setStrokeType(javafx.scene.shape.StrokeType.INSIDE);
        rectangleStick.setWidth(5.0);

        return rectangleStick;
    }

}

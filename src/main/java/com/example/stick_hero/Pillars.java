package com.example.stick_hero;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class Pillars {
    private double xBegin;
    private double xEnd;
    static double height = 196.0;
    private double width;

    public double getxBegin() {
        return xBegin;
    }

    public void setxBegin(double xBegin) {
        this.xBegin = xBegin;
    }

    public double getxEnd() {
        return xEnd;
    }

    public void setxEnd(double xEnd) {
        this.xEnd = xEnd;
    }

    public static double getHeight() {
        return height;
    }

    public static void setHeight(double height) {
        Pillars.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }




    //get a check on that pillar StrokeType thing
    public static Rectangle makePillar(){
        double width1 = Pillars.randWidthOfPillar();
        double x1 = Pillars.randInitialX();
        Rectangle rectangle = new Rectangle(width1,196.0);
        rectangle.setX(x1);
        rectangle.setY(297.0);
        rectangle.setArcWidth(5.0);
        rectangle.setArcHeight(5.0);
        rectangle.setStroke(Color.BLACK);
        //rectangle.setStrokeType(Rectangle.StrokeType.INSIDE);
        return rectangle;
    }

    //this method gets us the width of the new pillar to be
    private static double randWidthOfPillar(){
        Random random = new Random();
        double minWidth = 40;
        double maxWidthPillarOffset = 110;
        double pillarWidth = minWidth+ random.nextDouble()*maxWidthPillarOffset;

        return  pillarWidth;
    }

    public double getDistBwPillars(){
        return this.xBegin - 265.0;
    }
    private static double randInitialX(){
        Random random1 = new Random();
        double minXcoord = 300;
        double possibleXcoord = 350;
        double xstart = random1.nextDouble()*possibleXcoord;
        double initialX = minXcoord + xstart;

        return initialX;
    }

    private double calcFinalX(double initX, double width){
        return initX+width;
    }

    public Pillars(){
        this.width= this.randWidthOfPillar();
        this.xBegin = this.randInitialX();
        this.xEnd = this.calcFinalX(this.xBegin, this.width);
    }

    public Rectangle generatePillars(Pillars p){
        Rectangle ansRect = new Rectangle(p.getxBegin(), 297.0, p.getWidth(), Pillars.getHeight());
        return ansRect;
    }
}

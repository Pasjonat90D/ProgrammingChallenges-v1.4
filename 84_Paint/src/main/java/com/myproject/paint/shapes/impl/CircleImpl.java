package com.myproject.paint.shapes.impl;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class CircleImpl extends BasicPolygonImpl {


    public void draw(GraphicsContext graphicsContext, Color firstColor, Color secondColor) {
        graphicsContext.setStroke(firstColor);
        graphicsContext.setFill(secondColor);
        graphicsContext.fillOval(startingPosition.getX() , startingPosition.getY(), endPosition.getX(),endPosition.getY());
        graphicsContext.strokeOval(startingPosition.getX() , startingPosition.getY(), endPosition.getX(),endPosition.getY());
    }


}

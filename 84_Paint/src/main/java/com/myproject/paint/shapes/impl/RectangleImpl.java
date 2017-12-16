package com.myproject.paint.shapes.impl;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class RectangleImpl extends BasicPolygonImpl {
    public void draw(GraphicsContext graphicsContext, Color firstColor, Color secondColor) {
        graphicsContext.setFill(firstColor);
        graphicsContext.setStroke(secondColor);
        graphicsContext.fillRect(startingPosition.getX() , startingPosition.getY(), endPosition.getX(),endPosition.getY());
        graphicsContext.strokeRect(startingPosition.getX() , startingPosition.getY(), endPosition.getX(),endPosition.getY());
    }
}

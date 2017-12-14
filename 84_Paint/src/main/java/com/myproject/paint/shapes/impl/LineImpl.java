package com.myproject.paint.shapes.impl;

import com.myproject.paint.shapes.Point;
import com.myproject.paint.shapes.Shape;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class LineImpl implements Shape {

    private Point startingPosition;
    private Point endPosition;

    public void draw(GraphicsContext graphicsContext, Color firstColor, Color secondColor) {
        graphicsContext.setStroke(firstColor);
        graphicsContext.setFill(secondColor);
        graphicsContext.strokeLine(startingPosition.getX(),startingPosition.getY(),
                endPosition.getX(),endPosition.getY());
    }

    public void pressedKey(double x, double y) {
        startingPosition = new Point(x,y);
    }

    public void releasedKey(double x, double y) {
        endPosition = new Point(x,y);
    }
}

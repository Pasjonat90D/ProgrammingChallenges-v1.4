package com.myproject.paint.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public interface Shape {
    void draw(GraphicsContext graphicsContext,Color firstColor, Color secondColor);
    void pressedKey(double x,double y);
    void releasedKey(double x, double y);
}

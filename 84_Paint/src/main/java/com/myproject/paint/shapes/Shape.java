package com.myproject.paint.shapes;

import javafx.scene.canvas.GraphicsContext;

public interface Shape {
    void draw(GraphicsContext graphicsContext);
    void pressedKey(double x,double y);
    void releasedKey(double x, double y);
}

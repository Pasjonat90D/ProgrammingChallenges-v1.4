package com.myproject.paint.shapes.impl;

import com.myproject.paint.shapes.Point;
import com.myproject.paint.shapes.Shape;


abstract public class BasicPolygonImpl implements Shape {

    protected Point startingPosition;
    protected Point endPosition;


    public void pressedKey(double x, double y) {
        startingPosition = new Point(x,y);
    }

    public void releasedKey(double x, double y) {
        endPosition = new Point(x-startingPosition.getX(),y-startingPosition.getY());
    }
}

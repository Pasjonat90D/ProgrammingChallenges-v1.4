package com.myproject.paint;

import com.myproject.paint.shapes.Shape;
import com.myproject.paint.shapes.impl.LineImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{
        @FXML
    public Canvas canvas;

    private Shape shape;
    private GraphicsContext graphicsContext;


    public void initialize(URL location, ResourceBundle resources) {
        graphicsContext = canvas.getGraphicsContext2D();
    }

    public void select(ActionEvent actionEvent) {
            MenuItem menuItem = (MenuItem) actionEvent.getSource();
            if(menuItem.getText().equals("Line")){
                shape = new LineImpl();
            }
    }

    public void canvasClicked(MouseEvent mouseEvent) {
        shape.pressedKey(mouseEvent.getX(),mouseEvent.getY());
    }


    public void canvasReleased(MouseEvent mouseEvent) {
        shape.releasedKey(mouseEvent.getX(),mouseEvent.getY());
        shape.draw(graphicsContext);
    }


}

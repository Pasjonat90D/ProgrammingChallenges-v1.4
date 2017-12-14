package com.myproject.paint;

import com.myproject.paint.shapes.Shape;
import com.myproject.paint.shapes.impl.LineImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.MenuButton;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{
    @FXML
    public Canvas canvas;
    @FXML
    public MenuButton menuButton;
    @FXML
    ColorPicker firstColor;
    @FXML
    public ColorPicker secondColor;

    private Shape shape;
    private GraphicsContext graphicsContext;


    public void initialize(URL location, ResourceBundle resources) {
        graphicsContext = canvas.getGraphicsContext2D();
    }


    public void canvasClicked(MouseEvent mouseEvent) {
        if(shape != null)
        shape.pressedKey(mouseEvent.getX(),mouseEvent.getY());
    }


    public void canvasReleased(MouseEvent mouseEvent) {
        if(shape != null) {
            shape.releasedKey(mouseEvent.getX(), mouseEvent.getY());
            shape.draw(graphicsContext, firstColor.getValue(), secondColor.getValue());
        }
    }


    public void exit(ActionEvent actionEvent) {

    }

    public void selectLine() {
        shape = new LineImpl();
        menuButton.setText("Line");
    }

}

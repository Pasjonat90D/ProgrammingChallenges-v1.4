package com.myproject.paint;

import com.myproject.paint.shapes.Shape;
import com.myproject.paint.shapes.impl.CircleImpl;
import com.myproject.paint.shapes.impl.LineImpl;
import com.myproject.paint.shapes.impl.RectangleImpl;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.MenuButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
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
        Platform.exit();
    }

    public void selectLine() {
        shape = new LineImpl();
        menuButton.setText("Line");
    }

    public void selectCircle() {
        shape = new CircleImpl();
        menuButton.setText("Circle");
    }

    public void selectRectangle() {
        shape = new RectangleImpl();
        menuButton.setText("Rectangle");
    }

    public void selectNew(ActionEvent actionEvent) {
       
    }
}

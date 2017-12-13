package com.myproject.paint;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller {


    @FXML
    private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private Label messageLabel;


    public void sayHello() {

        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        messageLabel.setText("Hello "+firstName+" "+lastName);
    }

}

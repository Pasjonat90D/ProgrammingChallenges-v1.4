package com.namegrnerator;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Controller {

    @FXML
    public Label name;
    @FXML
    public Label birthday;
    @FXML
    public Button button;


    public void click(ActionEvent actionEvent) {
        Person person = Generator.generatorPeron();

        name.setText(person.getName());
        birthday.setText(person.getBirthday().toString());

    }
}

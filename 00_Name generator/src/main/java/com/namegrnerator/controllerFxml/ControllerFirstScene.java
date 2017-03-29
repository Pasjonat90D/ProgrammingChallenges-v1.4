package com.namegrnerator.controllerFxml;


import com.namegrnerator.controller.Generator;
import com.namegrnerator.model.Person;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javax.swing.*;
import java.awt.*;

public class ControllerFirstScene {

    @FXML
    public Label name;
    @FXML
    public Label surname;
    @FXML
    public Label numberPhone;
    @FXML
    public Label birthday;
    @FXML
    public Label address;

    private String option;


    public void click(ActionEvent actionEvent) {
        Person person = Generator.generatorPeron();
        name.setText(person.getName());
        surname.setText(person.getSurname());
        numberPhone.setText(person.getNumberPhone()+"");
        address.setText(person.getAddress());
        birthday.setText(person.getBirthday().toString());

    }

    public void option(ActionEvent actionEvent) {

        OptionsPanel optionsPanel = new OptionsPanel();


    }




}

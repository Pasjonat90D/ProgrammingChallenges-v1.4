package com.namegrnerator.controllerFxml;


import com.namegrnerator.controller.Generator;
import com.namegrnerator.model.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerFirstScene implements Initializable {

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
    @FXML
    public ComboBox genderBox;
    @FXML
    public ComboBox countryBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<String> genderList = new ArrayList<>();
        genderList.add("Random");
        genderList.add("Male");
        genderList.add("Female");
        ObservableList obList = FXCollections.observableList(genderList);
        this.genderBox.getItems().clear();
        this.genderBox.setItems(obList);



    }



    public void click(ActionEvent actionEvent) {
        System.out.println(genderBox.getValue()+"");
        Person person = Generator.generatorPeron();
        name.setText(person.getName());
        surname.setText(person.getSurname());
        numberPhone.setText(person.getNumberPhone()+"");
        address.setText(person.getAddress());
        birthday.setText(person.getBirthday().toString());

    }



}

package controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;

import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.web.HTMLEditor;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;


public class Controller implements Initializable {


    @FXML
    private HTMLEditor textArea ;


    String resoult;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        textArea.setHtmlText("Add text");
    }

    public void createNewFile(ActionEvent actionEvent) {

        Alert alertWantToSave = new Alert(Alert.AlertType.CONFIRMATION);
        alertWantToSave.setTitle("Editor Message");
        alertWantToSave.setHeaderText("Do you want to save changes");

        ButtonType yesSaveButton = new ButtonType("Yes");
        ButtonType noSaveButton = new ButtonType("No");

        ButtonType cancelSaveButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        alertWantToSave.getButtonTypes().setAll(yesSaveButton, noSaveButton, cancelSaveButton);

        Optional<ButtonType> resultAlertSave = alertWantToSave.showAndWait();
        if (resultAlertSave.get() == yesSaveButton){
            resoult = textArea.getHtmlText();
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");
            Stage stage  = (Stage) textArea.getScene().getWindow();
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("All Images", "*.*"),
                    new FileChooser.ExtensionFilter(".txt", "*.txt")
            );

            File file = fileChooser.showSaveDialog(stage);
            if (file != null) {

            }
            System.out.println(file.toString());
            textArea.setHtmlText("");
        } else if (resultAlertSave.get() == noSaveButton) {
            textArea.setHtmlText("");
        }

    }

    public void openFile(ActionEvent actionEvent) {

    }

    public void saveFile(ActionEvent actionEvent) {

    }

    public void saveAsFile(ActionEvent actionEvent) {

    }

    public void exitWindow(ActionEvent actionEvent) {

    }
}

package controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;

import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.util.*;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicLong;

import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.web.HTMLEditor;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import javax.swing.*;

import static java.awt.SystemColor.menu;
import static java.awt.SystemColor.text;


public class Controller implements Initializable {


    @FXML
    private HTMLEditor textArea ;

    @FXML
    private Label lines;
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


        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        Stage stage  = (Stage) textArea.getScene().getWindow();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter(".txt", "*.txt")
        );

        File file = fileChooser.showOpenDialog(stage);
        final String[] textToTextArea = {""};
        if (file != null) {
            Task<Void> task = new Task<Void>() {
                @Override
                public Void call() throws Exception {

                    int lineNumber = 0;
                    boolean count = true;
                    while (count) {

                        try (Scanner in = new Scanner(new FileInputStream(file))) {
                            while (in.hasNextLine()) {
                                String line = in.nextLine();
                                lineNumber++;
                                textToTextArea[0] = textToTextArea[0] + line;
                                textToTextArea[0] = textToTextArea[0] + "\n";
                                updateMessage("Line: "+lineNumber);

                            }

                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        count = false;


                    }return null;
                };
            };
            task.messageProperty().addListener((obs, oldMessage, newMessage) -> lines.setText(newMessage));
            new Thread(task).start();
            textArea.setHtmlText(textToTextArea[0]);

        }}



    public void saveFile(ActionEvent actionEvent) {

    }

    public void saveAsFile(ActionEvent actionEvent) {

    }

    public void exitWindow(ActionEvent actionEvent) {

    }
}

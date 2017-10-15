package controller;

import fileHandling.DocumentImpl;
import fileHandling.FileType;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.web.HTMLEditor;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Controller implements Initializable {

    private FileType fileType;
    private DocumentImpl document;
    private String textToTextArea = "";

    @FXML
    private HTMLEditor textArea;

    @FXML
    private Label lines;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        textArea.setHtmlText("Add text");
        lines.setVisible(false);
    }

    public void newFile() {
        ButtonType resultSaveChanges = alertSaveChanges();
        if(resultSaveChanges != ButtonType.CANCEL) {
            if (resultSaveChanges == ButtonType.YES) {
                saveFile();
            }
            textArea.setHtmlText("");
        }
    }


    public void saveFile() {
        textToTextArea = textArea.getHtmlText();
        if (document != null) {
            if (fileType==FileType.TXT)
                parserToText();
            document.save(textToTextArea);
        } else {
            saveAsFile();
        }
    }

    public void saveAsFile() {

        Alert alertWantToSave = new Alert(Alert.AlertType.CONFIRMATION);
        alertWantToSave.setTitle("Choose");
        alertWantToSave.setHeaderText("Choose format:");

        ButtonType textFormat = new ButtonType("Text");
        ButtonType htmlFormat = new ButtonType("HTML");

        alertWantToSave.getButtonTypes().setAll(textFormat, htmlFormat, ButtonType.CANCEL);

        Optional<ButtonType> resultAlertSave = alertWantToSave.showAndWait();

        if (resultAlertSave.get() != ButtonType.CANCEL) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save File");
            Stage stage = (Stage) textArea.getScene().getWindow();
            if(resultAlertSave.get().equals(textFormat)){
                fileChooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter(".txt", "*.txt"),
                        new FileChooser.ExtensionFilter(".html", "*.html")
                );
            } else {
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter(".html", "*.html"),
                    new FileChooser.ExtensionFilter(".txt", "*.txt")
            );}
            fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

            File fileSelected = fileChooser.showSaveDialog(stage);

            {

                if (fileSelected != null) {
                    if (resultAlertSave.get() == textFormat) {
                        fileType = FileType.TXT;
                        parserToText();
                    }
                    String ext1 = FilenameUtils.getExtension(fileSelected.toString());
                    document = new DocumentImpl(fileSelected);
                    document.save(textToTextArea);
                }
            }
        }
    }

    public void openFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        Stage stage = (Stage) textArea.getScene().getWindow();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All", "*.*"),
                new FileChooser.ExtensionFilter(".txt", "*.txt"),
                new FileChooser.ExtensionFilter(".html", "*.html")
        );
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        File fileSelected = fileChooser.showOpenDialog(stage);


        if (document != null) {

            document = new DocumentImpl(fileSelected);
            Task<Void> task = new Task<Void>() {

                @Override
                public Void call() throws Exception {
                    lines.setFont(new Font("Arial", 23));
                    lines.setTextFill(Color.RED);
                    updateMessage("Loading...");
                    lines.setVisible(true);
                    textToTextArea = document.open();
                    Thread.sleep(1000);
                    return null;
                }

                ;
            };
            task.messageProperty().addListener((obs, oldMessage, newMessage) -> lines.setText(newMessage));
            task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
                @Override
                public void handle(WorkerStateEvent t) {

                    lines.setVisible(false);
                    textArea.setHtmlText(textToTextArea);
                }
            });
            new Thread(task).start();
        }
    }

    public void exitWindow() {
        ButtonType resultSaveChanges = alertSaveChanges();
        if (resultSaveChanges == ButtonType.YES) {
            saveFile();
        } else if(resultSaveChanges == ButtonType.NO) {
            Platform.exit();
        }
    }


    private ButtonType alertSaveChanges(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Save changes");
        alert.setHeaderText("Do you want to save changes?");

        alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);

        Optional<ButtonType> resultAlertSave = alert.showAndWait();
        return resultAlertSave.get();
    }



    private void parserToText() {
        Pattern pattern = Pattern.compile("<[^>]*>");
        Matcher matcher = pattern.matcher(textToTextArea);
        final StringBuffer sb = new StringBuffer(textToTextArea.length());
        while (matcher.find()) {
            matcher.appendReplacement(sb, " ");
        }
        matcher.appendTail(sb);
        textToTextArea = sb.toString().trim();
    }

    public void ClearAll(ActionEvent actionEvent) {
        textArea.setHtmlText("");
    }
}

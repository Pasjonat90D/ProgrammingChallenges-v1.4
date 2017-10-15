package controller;


import fileHandling.DocumentImpl;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

import java.io.File;
import java.util.concurrent.TimeoutException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import static javafx.scene.input.KeyCode.ENTER;
import static javafx.scene.input.KeyCode.W;
import static org.junit.Assert.assertEquals;

/**
 * Created by Andrzej on 2017-10-11.
 */
public class ControllerTest extends ApplicationTest {

    private File file = new File(System.getProperty("user.home") + "\\W.txt");
    private String htmlDefaultsCode = "<html dir=\"ltr\"><head></head>" +
            "<body contenteditable=\"true\"></body></html>";

    private HTMLEditor htmlEditorDefaults;
    private String htmlCodeWithAddText;


    @After
    public void after() {
        file.delete();
    }

    @Before
    public void before() {
        htmlCodeWithAddText = htmlEditorDefaults.getHtmlText();
    }

    @Test
    public void newFile_NoSave_TextAreaIsCleared() throws TimeoutException {
        rightClickOn("File").moveTo("New").clickOn("New").clickOn("No");
        assertEquals(htmlDefaultsCode, htmlEditorDefaults.getHtmlText());
    }

    @Test
    public void newFile_CancelSave_TextAreaIsCleared() throws TimeoutException {
        Stage primaryStage = FxToolkit.registerPrimaryStage();
        HTMLEditor htmlEditorBeforeClicks = (HTMLEditor) primaryStage.getScene().lookup("#textArea");
        rightClickOn("File").moveTo("New").clickOn("New").clickOn("Cancel");
        assertEquals(htmlEditorBeforeClicks.getHtmlText(), htmlEditorDefaults.getHtmlText());
    }

    @Test
    public void newFile_YesSaveTxtFile_FileSaved() throws TimeoutException {
        String returnHtmlFromHtmlEditor = htmlEditorDefaults.getHtmlText();
        rightClickOn("File").moveTo("New").clickOn("New").clickOn("Yes").clickOn("Text");
        push(W);
        push(KeyCode.TAB).push(KeyCode.TAB).push(KeyCode.TAB).push(ENTER);
        file = new File(System.getProperty("user.home") + "\\W.txt");
        DocumentImpl document = new DocumentImpl(file);
        String resultOpenerFile = document.open();
        assertEquals(resultOpenerFile, parserToText(returnHtmlFromHtmlEditor));
    }

    @Test
    public void newFile_YesSaveTxtFile_TextAreaCleared() throws TimeoutException {
        sleep(500);
        rightClickOn("File").moveTo("New").clickOn("New").clickOn("Yes").clickOn("Text");
        push(W);
        push(KeyCode.TAB).push(KeyCode.TAB).push(KeyCode.TAB);
        push(ENTER);
        assertEquals(htmlDefaultsCode, htmlEditorDefaults.getHtmlText());
    }

    @Test
    public void newFile_YesSaveHTMLFile_FileSaved() throws TimeoutException {
        rightClickOn("File").moveTo("New").clickOn("New").clickOn("Yes").clickOn("HTML");
        push(W);
        push(W);
        push(KeyCode.TAB).push(KeyCode.TAB).push(KeyCode.TAB).push(ENTER);
        File fileHTML = new File(System.getProperty("user.home") + "\\ww.html");
        DocumentImpl document = new DocumentImpl(fileHTML);
        String resultOpenerFile = document.open();
        String textAddText = "<html dir=\"ltr\"><head></head><body contenteditable=\"true\">" + htmlCodeWithAddText + "</body></html>";
        assertEquals(resultOpenerFile, textAddText);
        fileHTML.delete();
    }



    private String parserToText(String textHtml) {
        Pattern pattern = Pattern.compile("<[^>]*>");
        Matcher matcher = pattern.matcher(textHtml);
        final StringBuffer sb = new StringBuffer(textHtml.length());
        while (matcher.find()) {
            matcher.appendReplacement(sb, " ");
        }
        matcher.appendTail(sb);
        return sb.toString().trim();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/main.fxml"));
        stage.setTitle("Editor");
        stage.setScene(new Scene(root, 600, 422));
        stage.setMaxHeight(465);
        stage.setMaxWidth(600);
        stage.show();
        htmlEditorDefaults = (HTMLEditor) stage.getScene().lookup("#textArea");
    }

}
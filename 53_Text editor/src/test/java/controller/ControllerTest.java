package controller;


import fileHandling.Document;
import fileHandling.DocumentImpl;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import static javafx.scene.input.KeyCode.*;
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
    public void newFile_NoSave_TextAreaCleaned() throws TimeoutException {
        rightClickOn("File").moveTo("New").clickOn("New").clickOn("No");
        assertEquals(htmlDefaultsCode, htmlEditorDefaults.getHtmlText());
    }

    @Test
    public void newFile_CancelSave_TextAreaCleaned() throws TimeoutException {
        Stage primaryStage = FxToolkit.registerPrimaryStage();
        HTMLEditor htmlEditorBeforeClicks = (HTMLEditor) primaryStage.getScene().lookup("#textArea");
        rightClickOn("File").moveTo("New").clickOn("New").clickOn("Cancel");
        assertEquals(htmlEditorBeforeClicks.getHtmlText(), htmlEditorDefaults.getHtmlText());
    }

    @Test
    public void newFile_YesSaveTxtFile_FileSaved() throws TimeoutException, FileNotFoundException {
        String returnHtmlFromHtmlEditor = htmlEditorDefaults.getHtmlText();
        rightClickOn("File").moveTo("New").clickOn("New").clickOn("Yes").clickOn("Text");
        push(W);
        push(KeyCode.TAB).push(KeyCode.TAB).push(KeyCode.TAB).push(ENTER);
        Document document = new DocumentImpl(file);
        String resultOpenerFile = document.open();
        assertEquals(resultOpenerFile, parserToText(returnHtmlFromHtmlEditor));
    }

    @Test
    public void newFile_YesSaveTxtFile_TextAreaCleaned() throws TimeoutException {
        sleep(500);
        rightClickOn("File").moveTo("New").clickOn("New").clickOn("Yes").clickOn("Text");
        push(W);
        push(KeyCode.TAB).push(KeyCode.TAB).push(KeyCode.TAB);
        push(ENTER);
        assertEquals(htmlDefaultsCode, htmlEditorDefaults.getHtmlText());
    }

    @Test
    public void newFile_YesSaveHTMLFile_FileSaved() throws TimeoutException, FileNotFoundException {
        rightClickOn("File").moveTo("New").clickOn("New").clickOn("Yes").clickOn("HTML");
        push(W);
        push(W);
        push(KeyCode.TAB).push(KeyCode.TAB).push(KeyCode.TAB).push(ENTER);
        File fileHTML = new File(System.getProperty("user.home") + "\\ww.html");
        Document document = new DocumentImpl(fileHTML);
        String resultOpenerFile = document.open();
        String textAddText = "<html dir=\"ltr\"><head></head><body contenteditable=\"true\">" + htmlCodeWithAddText + "</body></html>";
        assertEquals(resultOpenerFile, textAddText);
        fileHTML.delete();
    }

    @Test
    public void cleanAll_CleanTextArea_Cleaned() throws TimeoutException {
        clickOn("Edit").clickOn("Clean All");
        Stage primaryStage = FxToolkit.registerPrimaryStage();
        HTMLEditor htmlEditor = (HTMLEditor) primaryStage.getScene().lookup("#textArea");
        assertEquals(htmlDefaultsCode, htmlEditor.getHtmlText());
    }
    @Test
    public void open_FileNotExist_HtmlEditorDidNotChange() throws TimeoutException {
        clickOn("File").clickOn("Open...").push(W).push(W).push(PERIOD);
        Stage primaryStage = FxToolkit.registerPrimaryStage();
        HTMLEditor htmlEditorAfterOpen = (HTMLEditor) primaryStage.getScene().lookup("#textArea");
        String textAddText = "<html dir=\"ltr\"><head></head><body contenteditable=\"true\">" + htmlCodeWithAddText + "</body></html>";
        push(ESCAPE).push(ESCAPE);
        assertEquals(textAddText, htmlEditorAfterOpen.getHtmlText());
    }

    @Test
    public void open_FileExist_Opened() throws IOException, TimeoutException {
        Document document  = new DocumentImpl(file);
        document.save("EXP");
        file.createNewFile();
        String textFromFile = document.open();
        clickOn("File").clickOn("Open...").push(W).push(PERIOD).push(T).push(X).push(T).push(ENTER);
        sleep(1000);
        Stage primaryStage = FxToolkit.registerPrimaryStage();
        HTMLEditor htmlEditorAfterOpen = (HTMLEditor) primaryStage.getScene().lookup("#textArea");
        String htmlCodeFromHtmlEditor = "<html dir=\"ltr\"><head>" +
                "</head><body contenteditable=\"true\">"+textFromFile+"</body></html>";
        assertEquals(htmlCodeFromHtmlEditor , htmlEditorAfterOpen.getHtmlText());
    }
    @Test
    public void save_FileIsSave_Saved() throws IOException, TimeoutException {
        Document document  = new DocumentImpl(file);
        document.save("EXP");
        file.createNewFile();
        clickOn("File").clickOn("Open...").push(W).push(PERIOD).push(T).push(X).push(T).push(ENTER);
        sleep(2000);
        Stage primaryStage = FxToolkit.registerPrimaryStage();
        Scene scene = primaryStage.getScene();
        clickOnHtmlEditor(scene);
        push(K);
        clickOn("File").clickOn("Save");
        String textFromFile = document.open();
        HTMLEditor htmlEditorAfterOpen = (HTMLEditor) primaryStage.getScene().lookup("#textArea");
        assertEquals(textFromFile , htmlEditorAfterOpen.getHtmlText());
    }
    @Test
    public void save_FileNotSave_Saved() throws IOException, TimeoutException {
        clickOn("File").clickOn("Save").clickOn("Text").push(W).push(ENTER);
        Stage primaryStage = FxToolkit.registerPrimaryStage();
        Scene scene = primaryStage.getScene();
        clickOnHtmlEditor(scene);
        push(K);
        clickOn("File").clickOn("Save");
        Document document  = new DocumentImpl(file);
        String fileContent = document.open();
        HTMLEditor htmlEditorCode = (HTMLEditor) primaryStage.getScene().lookup("#textArea");
        String htmlCodeFromHtmlEditor = "<html dir=\"ltr\"><head>" +
                "</head><body contenteditable=\"true\">"+fileContent+"</body></html>";
        assertEquals(htmlCodeFromHtmlEditor , htmlEditorCode.getHtmlText());
    }
    @Test
    public void saveAs_SaveFile_Saved() throws TimeoutException, FileNotFoundException {
        Stage primaryStage = FxToolkit.registerPrimaryStage();
        HTMLEditor htmlEditorCode = (HTMLEditor) primaryStage.getScene().lookup("#textArea");
        clickOn("File").clickOn("Save As...").clickOn("Text").push(W);
        push(ENTER);
        Document document  = new DocumentImpl(file);
        String fileContent = document.open();
        String htmlCodeFromHtmlEditor = "<html dir=\"ltr\"><head>" +
                "</head><body contenteditable=\"true\">"+fileContent+"</body></html>";
        assertEquals(htmlCodeFromHtmlEditor , htmlEditorCode.getHtmlText());
    }

    private void clickOnHtmlEditor(Scene scene){
        Point2D windowCoord = new Point2D(scene.getWindow().getX(), scene.getWindow().getY());
        double clickX = Math.round(windowCoord.getX() + 200);
        double clickY = Math.round(windowCoord.getY() + 200);
        clickOn(new Point2D(clickX,clickY));
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
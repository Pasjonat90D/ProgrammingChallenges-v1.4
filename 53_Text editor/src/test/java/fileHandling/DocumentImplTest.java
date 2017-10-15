package fileHandling;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import static org.junit.Assert.*;

/**
 * Created by Andrzej on 2017-10-04.
 */
public class DocumentImplTest {

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    File tempFile;

    @Before
    public void createFile() throws IOException {
        tempFile = tempFolder.newFile("tempFile.txt");
    }


    @Test(expected = NullPointerException.class)
    public void open_FileNoExist_Exception() {
        DocumentImpl document = new DocumentImpl(null);
        document.open();
    }


    @Test
    public void open_OpenEmptyFile_Opened() throws IOException {
        DocumentImpl document = new DocumentImpl(tempFile);
        String result = document.open();
        assertEquals(result, "");
    }


    @Test(expected = NullPointerException.class)
    public void save_FileNoExist_ExceptionThrow() {
        DocumentImpl document = new DocumentImpl(null);
        document.save("Test Text");
    }
    @Test
    public void save_FileExist_Saved(){
        File file = new File(System.getProperty("user.home") + "\\w.txt");
        DocumentImpl document = new DocumentImpl(file);
        document.save("Save");
        StringBuffer sb = new StringBuffer("");
        try (Scanner in = new Scanner(new FileInputStream(file))) {
            while (in.hasNextLine()) {
                sb.append(in.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assertEquals("Save", sb.toString());
        file.delete();
    }

}
package fileHandling;

import java.io.*;
import java.util.Scanner;

/**
 * Created by Andrzej on 2017-09-19.
 */
public class DocumentImpl implements Document {

    private File file;
    private String textToTextArea;


    public DocumentImpl(File file) {
        this.file = file;
    }


    public String open()  {
        StringBuffer sb = new StringBuffer("");
        try (Scanner in = new Scanner(new FileInputStream(file))) {
            while (in.hasNextLine()) {
                sb.append(in.nextLine());

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        }

        return sb.toString();
    }

    public void save(String textToTextArea) {
        try (BufferedWriter br = new BufferedWriter(new FileWriter(file))) {
            br.write(textToTextArea);
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

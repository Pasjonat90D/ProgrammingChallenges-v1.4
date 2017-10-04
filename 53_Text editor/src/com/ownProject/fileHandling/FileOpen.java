package fileHandling;

import org.apache.commons.io.FilenameUtils;

import java.io.*;
import java.util.Scanner;

/**
 * Created by Andrzej on 2017-09-19.
 */
public class FileOpen implements FileOpenInter {


    private FileType fileType;
    private File file;
    private String textToTextArea;


    public FileOpen(File file, FileType fileType){
        this.file = file;
        this.fileType = fileType;
    }


    public String open(){
        textToTextArea = "";
        try (Scanner in = new Scanner(new FileInputStream(file))) {
                        while (in.hasNextLine()) {
                            String line = in.nextLine();
                            textToTextArea = textToTextArea + line;
                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

       return textToTextArea;
    }

    public void save(String textToTextArea) {
        try(BufferedWriter br = new BufferedWriter(new FileWriter(file))){
            br.write(textToTextArea);
            br.close();
        } catch (Exception e){
           System.err.println("File not saved");
           e.getStackTrace();
        }
    }

    public boolean isTxt(){
        return (fileType == FileType.TXT)?true:false;
    }

}

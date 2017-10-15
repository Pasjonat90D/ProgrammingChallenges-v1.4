package fileHandling;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by Andrzej on 2017-09-20.
 */
public interface Document {


    String open() throws FileNotFoundException;
    void save(String text);


}

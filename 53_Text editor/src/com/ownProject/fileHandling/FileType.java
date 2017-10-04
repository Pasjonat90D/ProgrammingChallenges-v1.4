package fileHandling;

/**
 * Created by Andrzej on 2017-09-20.
 */
public enum FileType {

    TXT("txt"),
    HTML("html");

    private String type;

    FileType(String type){
        this.type = type;
    }

    public String getValue(){
        return this.type;
    }

}

package utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class ValueCRUD {
    private String valURL = "/keepVal.txt";
    private Path pathWork = null;
    private String valResult = "0";
    private Stream<String> linesInput = null;

    public ValueCRUD(String valURL) {
        super();
        this.valURL = valURL;
        this.definePath();
    }

    public ValueCRUD() {
        super();
        this.definePath();
    }

    private void definePath(){
        pathWork = Paths.get(valURL);
    }

    public String valueReader(){
        try{
            linesInput = Files.lines(pathWork);
            if(linesInput.count() == 1){
                valResult = (String) linesInput.toArray()[0];
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return valResult;
    }

    public void valuerWritter(String value){
        try (BufferedWriter writer = Files.newBufferedWriter(pathWork))
        {
            writer.write(value);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

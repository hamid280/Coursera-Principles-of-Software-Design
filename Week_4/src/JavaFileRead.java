import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Hamit on 8/16/2016.
 */
public class JavaFileRead {
    public void readAndPrint() throws IOException {

        //url rescourse
//        URL source = new URL("http://....");
//        BufferedReader reader = new BufferedReader(new InputStreamReader(source.openStream()));
//        while (true){
//            String line = reader.readLine();
//            rest of following code similar
//        }
        Path p = Paths.get("C:\\Users\\Hamit\\Desktop\\sample.txt");
        BufferedReader reader = Files.newBufferedReader(p);
        while (true) {
            String line = reader.readLine();
            if (line == null)
                break;
            System.out.println(line);
        }
    }
}

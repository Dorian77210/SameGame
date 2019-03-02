package reader;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GameReader {

    public static final String readGameFile(File file) {
        StringBuffer buffer = new StringBuffer();
        String line = "";
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader(file));

            try {
                while((line = reader.readLine()) != null) {
                    buffer.append(line);
                }
            } catch(IOException readenException) {
                System.err.println("Error when reading the file " + file);
                System.exit(1);
            }
        } catch(IOException readerCreationException) {
            System.err.println("Error when creating reader for the file " + file);
            System.exit(1);
        }

        return buffer.toString();
    }
}
package utils.io;

import java.io.*;

public class FileWriter implements Writer<String> {
    @Override
    public void write(final String path, final String line) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(path, "UTF-8");
            writer.println(line + "\n");
            writer.close();
        } catch (FileNotFoundException e) {


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}

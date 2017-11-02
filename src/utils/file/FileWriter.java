package utils.file;

import io.reactivex.Observable;

import java.io.BufferedWriter;
import java.io.IOException;

public class FileWriter implements Writer<String> {
    @Override
    public void write(final String path, final String line) {
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new java.io.FileWriter(path));
            bufferedWriter.write(line);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

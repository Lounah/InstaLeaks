package utils.io;


import utils.Constants;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileWriter implements Writer<String> {
    @Override
    public void write(final String path, final String line) {
        try(java.io.FileWriter fw = new java.io.FileWriter(path, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw)) {
            out.println(line);
        } catch (IOException e) {

        }
    }
}

package agh.ics.oop.model;

import java.io.*;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class FileParser {
    public static void saveConfig(String filename, String line) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        writer.write(line);
        writer.close();
    }
    public static void readConfig(String filePath) throws FileNotFoundException {
        try {
            Paths.get(filePath);
            Scanner scanner = new Scanner(new File(filePath));
            scanner.useDelimiter(";");
            ArrayList<String> variables = new ArrayList<String>();
            while (scanner.hasNext()) {
                variables.add(scanner.next());
            }
            System.out.println(variables);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}

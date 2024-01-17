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
    public static String[] readConfig(String filePath) throws FileNotFoundException {
        try {
            Paths.get(filePath);
            Scanner reader = new Scanner(new File(filePath));
            String text = reader.nextLine();
            String[] config = text.split(";");
            return config;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
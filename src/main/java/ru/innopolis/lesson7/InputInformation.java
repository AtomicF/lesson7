package ru.innopolis.lesson7;

import java.io.*;

public class InputInformation {

    private int countOfLine(String fileName) {
        int lines = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            while (reader.readLine() != null) {
                lines++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    private int countOfColumns(String fileName) {
        int count = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String str = reader.readLine();
            count = str.length();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }

    public char[][] fieldReader(String fileName) {
        char[][] cellContents = new char[countOfLine(fileName)][countOfColumns(fileName)];
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            for (int i = 0; i < cellContents.length; i++) {
                String line = bufferedReader.readLine();
                char[] charsInLine = line.toCharArray();
                for (int j = 0; j < cellContents[i].length; j++) {
                    cellContents[i][j] = charsInLine[j];
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cellContents;
    }
}

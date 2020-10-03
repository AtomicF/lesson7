package ru.innopolis.lesson7;

import java.io.*;

/**
 * Класс создает двумерный массив char, данные читаются с файла переданного аргументу args[0]
 */

public class InputInformation {

    /**
     * Метод читает количество строк в файле
     * @param fileName - имя файла с которого читаются данные
     * @return - возвращает количество линий(столбцов в двумерном массиве
     */
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

    /**
     * Метод читает количество символов в первой строке
     * @param fileName - имя файла с которого читаются данные
     * @return - возвращает количество символов в первой ствроке файла
     */
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

    /**
     * Меотод читает поле с файла и записывает в двумерный массив
     * @param fileName - имя файла с которого читаются данные
     * @return - возвращает двумерный массив всех символов файла
     */
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

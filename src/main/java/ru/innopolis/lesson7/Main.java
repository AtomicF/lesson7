package ru.innopolis.lesson7;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

/**
 * Программа на вход принимает 3 аргумента
 * 1. args[0] - Имя файла с которого считывается игровое поле
 * 2. args[1] - Имя файла в который производится запись конечного результата
 * 3. args[2] - количество шагов которое должна выполнить программа
 */

public class Main {
    public static void main(String[] args) {
        int countOfSteps = Integer.parseInt(args[2]);
        CellLifeCycle cellLifeCycle = new CellLifeCycle(args[0],countOfSteps);

        char[][] cells = cellLifeCycle.followingSteps();
        try (PrintStream fileWriter = new PrintStream(new File(args[1]))) {
            for (int i = 0; i < cellLifeCycle.getWidth(); i++) {
                for (int j = 0; j < cellLifeCycle.getHeight(); j++) {
                    fileWriter.print(cells[i][j]);
                }
                fileWriter.print("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
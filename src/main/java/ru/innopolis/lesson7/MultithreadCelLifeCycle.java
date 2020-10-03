package ru.innopolis.lesson7;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class MultithreadCelLifeCycle extends CellLifeCycle {

    ExecutorService executor;

    private final String fileName;
    private final int countOfSteps;


    public MultithreadCelLifeCycle(String fileName, int countOfSteps) {
        super(fileName, countOfSteps);
        this.fileName = fileName;
        this.countOfSteps = countOfSteps;
    }

    @Override
    protected char[][] changeStateCells(char[][] cellOld) {
        executor = Executors.newCachedThreadPool();
        char[][] newArray = new char[getWidth()][getHeight()];
        List<Future<char[]>> threadArray = new ArrayList<>();
        for (int i = 0; i < getWidth(); i++) {
            ColumnStateChanger columnStateChanger = new ColumnStateChanger(cellOld, i);
            Future<char[]> task = executor.submit(columnStateChanger);;
            threadArray.add(task);
        }

        for (int i = 0; i < getWidth(); i++) {
            try {
                newArray[i] = threadArray.get(i).get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }


        executor.shutdown();
        return newArray;
    }



    class ColumnStateChanger implements Callable<char[]> {
        private final char[][] cellOld;
        private final int lineNumber;

        public ColumnStateChanger(char[][] cellOld, int lineNumber) {
            this.cellOld = cellOld;
            this.lineNumber = lineNumber;
        }

        @Override
        public char[] call() {
            char[] cell = new char[getHeight()];
            for (int j = 0; j < getHeight(); j++) {
                cell[j] = cellOld[lineNumber][j];
                if (countAliveNeighbour(cellOld, lineNumber, j) == 3 && cellOld[lineNumber][j] == '_') {
                    cell[j] = '*';
                }
                if ((countAliveNeighbour(cellOld, lineNumber, j) == 0 || countAliveNeighbour(cellOld, lineNumber, j) == 1) && cellOld[lineNumber][j] == '*') {
                    cell[j] = '_';
                }
                if (countAliveNeighbour(cellOld, lineNumber, j) >= 4 && cellOld[lineNumber][j] == '*') {
                    cell[j] = '_';
                }
            }
            return cell;
        }
    }
}
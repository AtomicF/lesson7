package ru.innopolis.lesson7;

import java.util.concurrent.*;

public class MultithreadCelLifeCycle extends CellLifeCycle {

    ExecutorService executor;

    public MultithreadCelLifeCycle(String fileName, int countOfSteps) {
        super(fileName, countOfSteps);
        executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    }

    @Override
    protected char[][] changeStateCells(char[][] cellOld) {
        for (int i = 0; i < getWidth(); i++) {
            ColumnStateChanger columnStateChanger = new ColumnStateChanger(cellOld, i);
            FutureTask<char[]> task = new FutureTask<>(columnStateChanger);
            try {
                task.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        return super.changeStateCells(cellOld);
    }

    class ColumnStateChanger implements Callable<char[]> {
        private final char[][] cellOld;
        private final int lineNumber;

        public ColumnStateChanger(char[][] cellOld, int lineNumber) {
            this.cellOld = cellOld;
            this.lineNumber = lineNumber;
        }

        @Override
        public char[] call() throws Exception {
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
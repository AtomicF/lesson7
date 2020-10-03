package ru.innopolis.lesson7;

/**
 * Класс выполняет изменения в клетках указанное количество шагов в однопоточном режиме
 */
public class CellLifeCycle {
    private int width;
    private int height;

    private final String fileName;
    private final int countOfSteps;

    InputInformation inputInformation = new InputInformation();

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public CellLifeCycle(String fileName, int countOfSteps) {
        this.fileName = fileName;
        this.countOfSteps = countOfSteps;
    }

    /**
     * Метод выполняет изменения в массиве countOfSteps количество раз
     * @return возвращает двумерный массив после выполнеиня всех шагов
     */
    public char[][] followingSteps() {
        char[][] cell = inputInformation.fieldReader(fileName);
        width = cell.length;
        height = cell[0].length;
        for (int i = 0; i < countOfSteps; i++) {
            cell = changeStateCells(cell);
        }
        return cell;
    }

    /**
     * Метод выполняет преобразование каждой клетки согласно игровым правилам
     * @return возвращает двумерный массив символов после выполнения преобразования согласно правилам
     */
    protected char[][] changeStateCells(char[][] cellOld) {
        char[][] cell = new char[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                cell[i][j] = cellOld[i][j];
                if (countAliveNeighbour(cellOld, i, j) == 3 && cellOld[i][j] == '_') {
                    cell[i][j] = '*';
                }
                if ((countAliveNeighbour(cellOld, i, j) == 0 || countAliveNeighbour(cellOld, i, j) == 1) && cellOld[i][j] == '*') {
                    cell[i][j] = '_';
                }
                if (countAliveNeighbour(cellOld, i, j) >= 4 && cellOld[i][j] == '*') {
                    cell[i][j] = '_';
                }
            }
        }
        return cell;
    }

    /**
     * Метод выполняет поиск живых клеток вокруг текущей с учетом динамичности поля
     * @param cell двумерный массив символов с обозначением живых и мертвых клеток
     * @param line количество строк в двумерном массиве
     * @param column количество столбцов в двумерном массиве
     * @return возвращает количество живых клеток вокруг текущей клетки
     */
    protected int countAliveNeighbour(char[][] cell, int line, int column) {
        int count = 0;
        for (int i = line - 1; i <= line + 1; i++) {
            for (int j = column - 1; j <= column + 1; j++) {
                if (i == line && j == column) {
                    continue;
                }
                if (cell[getXNeighbour(i)][getYNeighbour(j)] == '*') {
                    count++;
                }
            }
        }
        return count;
    }

    private int getYNeighbour(int y) {
        int res = y;
        if (y < 0) {
            res = height + y;
        }
        if (y >= height) {
            res = y - height;
        }
        return res;
    }

    private int getXNeighbour(int x) {
        int res = x;
        if (x < 0) {
            res = width + x;
        }
        if (x >= width) {
            res = x - width;
        }
        return res;
    }
}
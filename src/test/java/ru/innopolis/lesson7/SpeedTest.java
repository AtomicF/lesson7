package ru.innopolis.lesson7;

import org.junit.Test;

import static org.junit.Assert.*;

public class SpeedTest {
    private static final int COUNT_OF_STEPS = 100;
    private final String fileName = "field.txt";

    @Test
    public void speedTest() {
        long singleTime = 0;
        for (int i = 0; i < 10; i++) {
            CellLifeCycle cellLifeCycle = new CellLifeCycle(fileName, COUNT_OF_STEPS);
            long startSingle = System.currentTimeMillis();
            cellLifeCycle.followingSteps();
            long finshSingle = System.currentTimeMillis();
            finshSingle = finshSingle - startSingle;
            singleTime += finshSingle;
        }
        double avg = (singleTime / 10.0) / 1000.0;


        long multithreadTime = 0;
        for (int i = 0; i < 10; i++) {
            MultithreadCelLifeCycle multithreadCelLifeCycle = new MultithreadCelLifeCycle(fileName, COUNT_OF_STEPS);
            long startSingle = System.currentTimeMillis();
            multithreadCelLifeCycle.followingSteps();
            long finshSingle = System.currentTimeMillis();
            finshSingle = finshSingle - startSingle;
            multithreadTime += finshSingle;
        }

        double avgM = (multithreadTime / 10.) / 1000;

        System.out.println("Время выполнения в однопоточном режиме: " + avg);
        System.out.println("Время выполнения в многопоточном режиме: " + avgM);

    }

}
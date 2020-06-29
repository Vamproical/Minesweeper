package minesweeper;

import java.util.Random;
import java.util.Scanner;

public class Minesweeper {
    private final Scanner scanner = new Scanner(System.in);
    private final Random random = new Random();
    private final char[][] array = new char[9][9];

    private void filledArray(int count) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                array[i][j] = '.';
            }
        }
        while (count != 0) {
            int i = random.nextInt(9);
            int j = random.nextInt(9);
            if (array[i][j] != 'X') {
                array[i][j] = 'X';
                --count;
            }
        }
    }

    private void printArray() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(array[i][j]);
            }
            System.out.println();
        }
    }

    private boolean isNeighborMine(int height, int width) {
        if (height < 0 || width < 0 || height >= 9 || width >= 9) {
            return false;
        }
        return array[height][width] == 'X';
    }

    private void checkMines() {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                int mineCount = 0;
                mineCount += isNeighborMine(i - 1, j - 1) ? 1 : 0;
                mineCount += isNeighborMine(i - 1, j) ? 1 : 0;
                mineCount += isNeighborMine(i - 1, j + 1) ? 1 : 0;
                mineCount += isNeighborMine(i, j - 1) ? 1 : 0;
                mineCount += isNeighborMine(i, j + 1) ? 1 : 0;
                mineCount += isNeighborMine(i + 1, j - 1) ? 1 : 0;
                mineCount += isNeighborMine(i + 1, j) ? 1 : 0;
                mineCount += isNeighborMine(i + 1, j + 1) ? 1 : 0;
                if (mineCount != 0) {
                    if (array[i][j] != 'X') {
                        array[i][j] = (char) (mineCount + '0');
                    }
                }
            }
        }
    }

    public void init() {
        System.out.print("How many mines do you want on the field? ");
        int count = Integer.parseInt(scanner.nextLine());
        filledArray(count);
        checkMines();
        printArray();
    }
}
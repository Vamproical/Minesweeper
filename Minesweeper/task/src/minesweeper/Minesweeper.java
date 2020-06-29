package minesweeper;

import java.util.Random;
import java.util.Scanner;

public class Minesweeper {
    private final Scanner scanner = new Scanner(System.in);
    private final Random random = new Random();
    private final Cells[][] array = new Cells[9][9];
    private int counterMine;

    private void filledArray(int count) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                array[i][j] = new Cells('.');
            }
        }
        while (count != 0) {
            int i = random.nextInt(9);
            int j = random.nextInt(9);
            if (!array[i][j].isMine()) {
                array[i][j].setMine();
                --count;
            }
        }
    }

    private void printArray() {
        System.out.println(" │123456789│");
        System.out.println("—│—————————│");
        for (int i = 0; i < 9; i++) {
            System.out.print((i + 1) + "|");
            for (int j = 0; j < 9; j++) {
                System.out.print(array[i][j].getChars());
            }
            System.out.print("|");
            System.out.println();
        }
        System.out.println("—│—————————│");
    }

    private boolean isNeighborMine(int height, int width) {
        if (height < 0 || width < 0 || height >= 9 || width >= 9) {
            return false;
        }
        return array[height][width].isMine();
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
                    if (!array[i][j].isMine()) {
                        array[i][j].setChars((char) (mineCount + '0'));
                    }
                }
            }
        }
    }

    private int checkMine() {
        int counter = 0;
        for (Cells[] cells : array) {
            for (int j = 0; j < array[0].length; j++) {
                if (cells[j].getChars() == '*' && cells[j].isMine()) {
                    ++counter;
                }
            }
        }
        return counter;
    }

    private void startGame() {
        while (true) {
            printArray();
            System.out.print("Set/delete mines marks (x and y coordinates): ");
            int x = Integer.parseInt(scanner.next()) - 1;
            int y = Integer.parseInt(scanner.next()) - 1;
            while (Character.isDigit(array[y][x].getChars())) {
                System.out.println("There is a number here!");
                System.out.print("Set/delete mines marks (x and y coordinates): ");
                x = Integer.parseInt(scanner.next()) - 1;
                y = Integer.parseInt(scanner.next()) - 1;
            }
            if (array[y][x].getChars() == '*') {
                array[y][x].setChars('.');
            } else {
                array[y][x].setChars('*');
            }
            if (counterMine == checkMine()) {
                break;
            }
        }
        System.out.println("Congratulations! You found all mines!");
    }

    public void init() {
        System.out.print("How many mines do you want on the field? ");
        counterMine = Integer.parseInt(scanner.nextLine());
        filledArray(counterMine);
        checkMines();
        startGame();
    }
}
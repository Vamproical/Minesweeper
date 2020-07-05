package minesweeper;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int width = 9;
        final int height = 9;
        System.out.print("How many mines do you want on the field? ");
        int counterMine = Integer.parseInt(scanner.nextLine());
        Minesweeper minesweeper = new Minesweeper(counterMine, width,height);
        minesweeper.printArray();
        while (!minesweeper.isGameWon()) {
            minesweeper.init();
        }
    }
}

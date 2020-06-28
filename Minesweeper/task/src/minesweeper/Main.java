package minesweeper;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final Random random = new Random();
        System.out.print("How many mines do you want on the field? ");
        int count = Integer.parseInt(scanner.nextLine());
        char[][] array = new char[9][9];
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
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(array[i][j]);
            }
            System.out.println();
        }
    }
}

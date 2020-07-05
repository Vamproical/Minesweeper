package minesweeper;

import java.util.*;

public class Minesweeper {
    private final Scanner scanner = new Scanner(System.in);
    private final Random random = new Random();
    private final Cells[][] array;
    private final int counterMine;
    private boolean isWin;
    private String action;

    private int correctMineCount;
    private int incorrectMineCount;
    private int freeCount;
    private boolean isGameLost;

    public Minesweeper(int counterMine, int width, int height) {
        this.counterMine = counterMine;
        array = new Cells[width][height];
        filledArray();
        printArray();
    }

    private void filledArray() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                array[i][j] = new Cells(i, j);
            }
        }
        correctMineCount = 0;
        incorrectMineCount = 0;
        freeCount = 0;
        isGameLost = false;
    }

    private void setMines(int count, int x, int y) {
        while (count != 0) {
            int i = random.nextInt(9);
            int j = random.nextInt(9);
            if (!array[i][j].isMine() && x != i && y != j) {
                array[i][j].setMine();
                --count;
            }
        }
    }

    public void printArray() {
        System.out.println(" │123456789│");
        System.out.println("—│—————————│");
        for (int i = 0; i < 9; i++) {
            System.out.print((i + 1) + "|");
            for (int j = 0; j < 9; j++) {
                System.out.print(array[i][j].toString());
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
                        array[i][j].setNeighbourMine(mineCount);
                    }
                }
            }
        }

    }

    private int[] getCoordinate() {
        System.out.print("Set/unset mines marks or claim a cell as free: ");
        String[] parseCommand = scanner.nextLine().split(" ");
        int x = Integer.parseInt(parseCommand[1]);
        int y = Integer.parseInt(parseCommand[0]);
        action = parseCommand[2];
        return new int[]{x, y};
    }

    public boolean isGameWon() {
        return isGameLost || isWin;
    }

    private int updateMineState(Cells cells, int count) {
        if (cells.getGuessState().equals(Cells.GuessState.FLAGGED)) {
            cells.setNotGuessed();
            count--;
        } else {
            cells.setFlagged();
            count++;
        }
        return count;
    }

    private Cells getMineFromField(int height, int width) {
        return array[height][width];
    }

    private void startGame() {
        int[] coordinate = getCoordinate();
        Cells spot = array[coordinate[0]][coordinate[1]];
        if (action.equals("free")) {
            switch (spot.getMineState()) {
                case MINE:
                    isGameLost = true;
                    break;
                case NUMBER:
                    spot.setFree();
                    break;
                case BLANK:
                    spot.setFree();
                    freeCount++;
                    break;
            }
        } else {
            switch (spot.getMineState()) {
                case MINE:
                    correctMineCount = updateMineState(spot, correctMineCount);
                case NUMBER:
                case BLANK:
                    incorrectMineCount = updateMineState(spot, incorrectMineCount);
                    break;
            }
        }
    }

    public void init() {
        while (true) {
            int[] coordinate = getCoordinate();

            setMines(counterMine, coordinate[0], coordinate[1]);
            checkMines();
            printArray();
        }
    }
}
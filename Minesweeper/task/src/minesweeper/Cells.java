package minesweeper;

public class Cells {
    public enum MineState {
        BLANK,
        MINE,
        NUMBER
    }

    public enum GuessState {
        NOT_GUESSED,
        FLAGGED,
        FREE
    }

    private final int height;
    private final int width;

    private MineState mineState;
    private GuessState guessState;
    private int numberNearbyMines;

    public Cells(int height, int width) {
        this.height = height;
        this.width = width;
        mineState = MineState.BLANK;
        guessState = GuessState.NOT_GUESSED;
        numberNearbyMines = 0;
    }

    public void setNeighbourMine(int neighbourMine) {
        if (mineState != MineState.MINE && numberNearbyMines > 0 && numberNearbyMines < 9) {
            this.numberNearbyMines = neighbourMine;
            mineState = MineState.NUMBER;
        }
    }

    public void setMine() {
        mineState = MineState.MINE;
    }

    public boolean isMine() {
        return MineState.MINE == mineState;
    }

    public MineState getMineState() {
        return mineState;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public GuessState getGuessState() {
        return guessState;
    }

    public int getNumberNearbyMines() {
        return numberNearbyMines;
    }

    public void setNotGuessed() {
        guessState = GuessState.NOT_GUESSED;
    }

    public void setFlagged() {
        guessState = GuessState.FLAGGED;
    }

    public void setFree() {
        guessState = GuessState.FREE;
    }

    @Override
    public String toString() {
        switch (guessState) {
            case FLAGGED:
                return "*";
            case NOT_GUESSED:
                return ".";
            case FREE:
                switch (mineState) {
                    case BLANK:
                        return "/";
                    case NUMBER:
                        return "" + numberNearbyMines;
                }
        }
        return null;
    }
}

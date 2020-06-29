package minesweeper;

public class Cells {
    private boolean isMine;
    private char chars;
    public Cells(char chars) {
        this.chars = chars;
        isMine = false;
    }

    public void setChars(char chars) {
        this.chars = chars;
    }

    public void setMine() {
        isMine = true;
    }

    public boolean isMine() {
        return isMine;
    }

    public char getChars() {
        return chars;
    }
}

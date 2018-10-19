package game.board.field.figure;

import game.Colors;

public class Figure {

    private final Colors color;
    private boolean isKing = false;

    public Figure(Colors color) {
        this.color = color;
    }

    public Colors getColor() {
        return color;
    }

    public boolean isKing() {
        return isKing;
    }

    public void setKing(boolean king) {
        isKing = king;
    }
}

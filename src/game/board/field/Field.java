package game.board.field;

import game.Colors;
import game.board.field.figure.Figure;

import java.util.Objects;

public class Field {

    private final String id;
    private final Colors color;
    private Figure currentFigure;

    public Field(char coordinateX, int coordinateY, Colors color) {
        this.color = color;
        this.id = "" + coordinateX + coordinateY;
        this.currentFigure = null;
    }

    public Colors getColor() {
        return color;
    }

    public String getId() {
        return id;
    }

    public Figure getCurrentFigure() {
        return currentFigure;
    }

    public void setCurrentFigure(Figure currentFigure) {
        this.currentFigure = currentFigure;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Field)) return false;
        Field field = (Field) o;
        return Objects.equals(getId(), field.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}

package game.board;

import game.Colors;
import game.board.field.Field;
import game.board.field.figure.Figure;

import java.util.HashMap;

public class Board {

    private final HashMap<String, Field> fields;

    public Board() {
        this.fields = generateFields();
    }

    public HashMap<String, Field> getFields() {
        return fields;
    }

    private HashMap<String, Field> generateFields() {

        HashMap<String, Field> fields = new HashMap<>();
        for (char i = 'A'; i <= 'H'; i++) {
            for (int j = 1; j <= 8; j++) {
                if (i % 2 == 1) {
                    if (j % 2 == 1) fields.put("" + i + j, createWhiteField(i, j));
                    else fields.put("" + i + j, createBlackField(i, j));
                }
                if (i % 2 == 0) {
                    if (j % 2 == 1) fields.put("" + i + j, createBlackField(i, j));
                    else fields.put("" + i + j, createWhiteField(i, j));
                }
            }
        }

        return fields;
    }

    public void initializeStartFigures() {
        // Only black fields are to be played with
        // White always starts from bottom

        // Black stones
        this.fields
                .keySet()
                .stream()
                .filter(key -> key.matches("[A-C]."))
                .filter(key -> this.fields.get(key).getColor() == Colors.BLACK)
                .forEach(key -> this.fields.get(key).setCurrentFigure(new Figure(Colors.BLACK)));

        // White stones
        this.fields
                .keySet()
                .stream()
                .filter(key -> key.matches("[F-H]."))
                .filter(key -> this.fields.get(key).getColor() == Colors.BLACK)
                .forEach(key -> this.fields.get(key).setCurrentFigure(new Figure(Colors.WHITE)));
    }

    public void setSingleFigureOnBoard(char row, int column, Colors color) {
        this.fields.get("" + row + column).setCurrentFigure(new Figure(color));
    }

    String printBoard() {
        StringBuilder sb = new StringBuilder();

        for (char i = 'A'; i <= 'H'; i++) {
            for (int j = 1; j <= 8; j++) {

                String id = "" + i + j;

                if (null != fields.get(id).getCurrentFigure() && fields.get(id).getCurrentFigure().getColor() == Colors.BLACK) {
                    if (j == 8) sb.append("●\n");
                    else sb.append("●\t\t");
                }
                if (null != fields.get(id).getCurrentFigure() && fields.get(id).getCurrentFigure().getColor() == Colors.WHITE) {
                    if (j == 8) sb.append("○\n");
                    else sb.append("○\t\t");
                }
                if (null == fields.get(id).getCurrentFigure() && fields.get(id).getColor() == Colors.BLACK) {
                    if (j == 8) sb.append("B\n");
                    else sb.append("B\t\t");
                }
                if (null == fields.get(id).getCurrentFigure() && fields.get(id).getColor() == Colors.WHITE) {
                    if (j == 8) sb.append("W\n");
                    else sb.append("W\t\t");
                }
            }
        }
        return sb.toString();
    }

    private Field createWhiteField(char i, int j) {
        return new Field(i, j, Colors.WHITE);
    }

    private Field createBlackField(char i, int j) {
        return new Field(i, j, Colors.BLACK);
    }
}

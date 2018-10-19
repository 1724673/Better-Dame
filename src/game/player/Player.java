package game.player;

import game.Colors;

public class Player {

    private final String name;
    private final Colors color;

    public Player(String name, Colors color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public Colors getColor() {
        return color;
    }
}

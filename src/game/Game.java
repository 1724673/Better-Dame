package game;

import game.board.Board;
import game.player.Player;

import java.util.ArrayList;
import java.util.List;

class Game {
    private final Board board;
    private List<Player> playerList = new ArrayList<>(2);
    private Player activePlayer = null;

    Game() {
        this.board = new Board();
    }

    List<Player> getPlayerList() {
        return playerList;
    }

    String getActivePlayer() {
        return activePlayer.getName();
    }

    void addPlayer(String name, String color) {
        // TODO Add proper rejection handling
        if (this.playerList.size() > 2) return;
        if (name != null && color.equals(Colors.WHITE.toString()))
            this.playerList.add(new Player(name, Colors.WHITE));
        if (name != null && color.equals(Colors.BLACK.toString()))
            this.playerList.add(new Player(name, Colors.BLACK));
    }

    void start() {
        // TODO Add proper rejection handling
        if (this.playerList.size() < 2) return;

        // Initialize player figures on board
        this.board.initializeStartFigures();

        // Set active player to the player who chose white
        if (this.playerList.get(0).getColor() == Colors.WHITE) this.activePlayer = this.playerList.get(0);
        else this.activePlayer = playerList.get(1);
    }
}

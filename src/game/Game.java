package game;

import game.board.Board;
import game.player.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Game {
    private final Board board;
    private List<Player> playerList = new ArrayList<>(2);
    private Player activePlayer = null;
    // Stores original field's ID as key and attaches all possible destinations' ids and updates upon each turn
    private Map<String, ArrayList<String>> currentPossibleMoves = new HashMap<>();

    Game() {
        this.board = new Board();
    }

    public Board getBoard() {
        return board;
    }

    List<Player> getPlayerList() {
        return playerList;
    }

    String getActivePlayer() {
        return activePlayer.getName();
    }

    public void setActivePlayer(Player activePlayer) {
        this.activePlayer = activePlayer;
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

    public String[] getPossibleMoves() {

        List<String> stringList = new ArrayList<>();

        for (String departureField : this.currentPossibleMoves.keySet()) {
            if (!this.currentPossibleMoves.get(departureField).isEmpty()) {
                for (String destinationField : this.currentPossibleMoves.get(departureField)) {
                    stringList.add("<" + departureField + "-" + destinationField + ">");
                }
            }
        }

        return stringList.toArray(new String[0]);

    }

    public void updateCurrentPossibleMoves() {

        if (this.activePlayer.getColor() == Colors.WHITE) {
            // Get all white men
            ArrayList<String> whiteMenFields =
                    this.board.getFields()
                            .keySet()
                            .stream()
                            .filter(key -> this.board.getFields().get(key).getCurrentFigure() != null)
                            .filter(key -> this.board.getFields().get(key).getCurrentFigure().getColor() == Colors.WHITE)
                            .filter(key -> !this.board.getFields().get(key).getCurrentFigure().isKing())
                            .collect(Collectors.toCollection(ArrayList::new));

            // Check possible moves for each white men individually and get all IDs
            whiteMenFields.forEach(
                    key -> {
                        // Initialize new ArrayList for possible move destinations
                        ArrayList<String> possibleDestinations = new ArrayList<>();

                        // Find all solutions based on starting key ID
                        possibleDestinations.addAll(findSolutionsForWhiteMen(key));

                        // Put result in class field HashMap
                        this.currentPossibleMoves.put(key, possibleDestinations);

                    }
            );
        }

        if (this.activePlayer.getColor() == Colors.BLACK) {

            // TODO Implement last break condition in recursive methods
            // Get all black men
            ArrayList<String> blackMenFields =
                    this.board.getFields()
                            .keySet()
                            .stream()
                            .filter(key -> this.board.getFields().get(key).getCurrentFigure() != null)
                            .filter(key -> this.board.getFields().get(key).getCurrentFigure().getColor() == Colors.BLACK)
                            .filter(key -> !this.board.getFields().get(key).getCurrentFigure().isKing())
                            .collect(Collectors.toCollection(ArrayList::new));

            // Check possible moves for each white men individually and get all IDs
            blackMenFields.forEach(
                    key -> {
                        // Initialize new ArrayList for possible move destinations
                        ArrayList<String> possibleDestinations = new ArrayList<>();

                        // Find all solutions based on starting key ID
                        possibleDestinations.addAll(findSolutionsForBlackMen(key));

                        // Put result in class field HashMap
                        this.currentPossibleMoves.put(key, possibleDestinations);

                    }
            );
        }

    }

    private ArrayList<String> findSolutionsForBlackMen(String key) {

        ArrayList<String> possibleDestinations = new ArrayList<>();

        char row = key.toCharArray()[0];
        char column = key.toCharArray()[1];

        String idForLeftMove = "" + (char) (row + 1) + (char) (column - 1);
        String idForRightMove = "" + (char) (row + 1) + (char) (column + 1);

        String idForLeftMove2 = "" + (char) (row + 2) + (char) (column - 2);
        String idForRightMove2 = "" + (char) (row + 2) + (char) (column + 2);

        if (null != board.getFields().get(idForLeftMove)
                && null == board.getFields().get(idForLeftMove).getCurrentFigure())
            possibleDestinations.add(idForLeftMove);

        if (null != board.getFields().get(idForRightMove)
                && null == board.getFields().get(idForRightMove).getCurrentFigure())
            possibleDestinations.add(idForRightMove);

        if (null != board.getFields().get(idForLeftMove)
                && null != board.getFields().get(idForLeftMove).getCurrentFigure()
                && board.getFields().get(idForLeftMove).getCurrentFigure().getColor() == Colors.WHITE) {

            if (null != board.getFields().get(idForLeftMove2)
                    && null == board.getFields().get(idForLeftMove2).getCurrentFigure()) {
                possibleDestinations.add(idForLeftMove2);
                possibleDestinations.addAll(findSolutionsForBlackMen(idForLeftMove2));
            }
        }

        if (null != board.getFields().get(idForRightMove)
                && null != board.getFields().get(idForRightMove).getCurrentFigure()
                && board.getFields().get(idForRightMove).getCurrentFigure().getColor() == Colors.WHITE) {

            if (null != board.getFields().get(idForRightMove2)
                    && null == board.getFields().get(idForRightMove2).getCurrentFigure()) {
                possibleDestinations.add(idForRightMove2);
                possibleDestinations.addAll(findSolutionsForBlackMen(idForRightMove2));
            }
        }
        return possibleDestinations;
    }

    private ArrayList<String> findSolutionsForWhiteMen(String key) {

        ArrayList<String> possibleDestinations = new ArrayList<>();

        char row = key.toCharArray()[0];
        char column = key.toCharArray()[1];

        String idForLeftMove = "" + (char) (row - 1) + (char) (column - 1);
        String idForRightMove = "" + (char) (row - 1) + (char) (column + 1);

        String idForLeftMove2 = "" + (char) (row - 2) + (char) (column - 2);
        String idForRightMove2 = "" + (char) (row - 2) + (char) (column + 2);

        if (null != board.getFields().get(idForLeftMove)
                && null == board.getFields().get(idForLeftMove).getCurrentFigure())
            possibleDestinations.add(idForLeftMove);

        if (null != board.getFields().get(idForRightMove)
                && null == board.getFields().get(idForRightMove).getCurrentFigure())
            possibleDestinations.add(idForRightMove);

        if (null != board.getFields().get(idForLeftMove)
                && null != board.getFields().get(idForLeftMove).getCurrentFigure()
                && board.getFields().get(idForLeftMove).getCurrentFigure().getColor() == Colors.BLACK) {

            if (null != board.getFields().get(idForLeftMove2)
                    && null == board.getFields().get(idForLeftMove2).getCurrentFigure()) {
                possibleDestinations.add(idForLeftMove2);
                possibleDestinations.addAll(findSolutionsForWhiteMen(idForLeftMove2));
            }
        }

        if (null != board.getFields().get(idForRightMove)
                && null != board.getFields().get(idForRightMove).getCurrentFigure()
                && board.getFields().get(idForRightMove).getCurrentFigure().getColor() == Colors.BLACK) {

            if (null != board.getFields().get(idForRightMove2)
                    && null == board.getFields().get(idForRightMove2).getCurrentFigure()) {
                possibleDestinations.add(idForRightMove2);
                possibleDestinations.addAll(findSolutionsForWhiteMen(idForRightMove2));
            }
        }
        return possibleDestinations;
    }
}

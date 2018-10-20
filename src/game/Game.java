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
                        possibleDestinations.addAll(findSolutionsForField(key));

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
                        possibleDestinations.addAll(findSolutionsForField(key));

                        // Put result in class field HashMap
                        this.currentPossibleMoves.put(key, possibleDestinations);

                    }
            );
        }

    }

    private List<String> findSolutionsForField(String key) {
        List<String> solutions = new ArrayList<>();

        // Determine direction of field advancement for men according to player's color
        int direction = (this.activePlayer.getColor() == Colors.WHITE ? -1 : 1);

        // Construct field ID for direct neighbors
        String idLeftMove = "" + (char) (key.toCharArray()[0] + direction) + (char) (key.toCharArray()[1] + 1);
        String idRightMove = "" + (char) (key.toCharArray()[0] + direction) + (char) (key.toCharArray()[1] - 1);

        // Construct field IDs for 2nd direct neighbors
        String idLeftMove2 = "" + (char) (key.toCharArray()[0] + 2 * direction) + (char) (key.toCharArray()[1] + 2);
        String idRightMove2 = "" + (char) (key.toCharArray()[0] + 2 * direction) + (char) (key.toCharArray()[1] - 2);

        if (board.getFields().get(key).getCurrentFigure() != null) {
            if (null != board.getFields().get(idLeftMove)
                    && null == board.getFields().get(idLeftMove).getCurrentFigure()) {
                solutions.add(idLeftMove);
            }
            if (null != board.getFields().get(idRightMove)
                    && null == board.getFields().get(idRightMove).getCurrentFigure()) {
                solutions.add(idRightMove);
            }
        }

        findJumpSolutions(solutions, idLeftMove, idLeftMove2);
        findJumpSolutions(solutions, idRightMove, idRightMove2);

        return solutions;
    }

    private void findJumpSolutions(List<String> solutions, String idJumpMove, String idJumpMove2) {
        if (null != board.getFields().get(idJumpMove2)
                && null != board.getFields().get(idJumpMove).getCurrentFigure()
                && !board.getFields().get(idJumpMove).getCurrentFigure().getColor().equals(activePlayer.getColor())
                && null == board.getFields().get(idJumpMove2).getCurrentFigure()) {
            List<String> jumpSolution = new ArrayList<>();
            jumpSolution = this.findSolutionsForField(idJumpMove2);
            if (jumpSolution.isEmpty()) solutions.add(idJumpMove2 + "!"); // '!' to signal jump duty
            else solutions.addAll(jumpSolution);
        }
    }
}

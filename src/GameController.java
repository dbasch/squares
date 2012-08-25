package squares;

/** This class implements a controller for the game of Squares.
  The objective of the game is to turn on all the squares (cells) on 
  a rectangular board. Clicking on a square turns on or off 
  certain squares according to a specified rule.
 */
public class GameController  {
    private Rule rule;
    private Board board;
    private GameUI gui;
    private int moves;
    private int levelNumber;

    /**
      Restarts a level. All the squares are turned off 
      and the number of moves is set to 0.
     */
    public void restartLevel() {
        checkGameState();
        board.reset();
        moves = 0;
        gui.refreshBoard(board, moves, levelNumber);

    }

    /**
      Applies the transformation rule to the current board when the player
      pressed cell (i, j).
     */

    public void cellPressed(int i, int j) {
        checkGameState();
        rule.transform(board, i, j);
        moves++;
        gui.refreshBoard(board, moves, levelNumber);
        if (board.isWinner()) {
            gui.allowNextLevel();

        }

    }

    /**
      Advances to the next level, if there is one.
     */
    public void increaseLevel() {
        checkGameState();
        levelNumber++;
        if (Level.getLevel(levelNumber) != null) {
            setLevel(levelNumber);
        }
        else {
            System.out.println("No more levels. Congratulations!");
            System.exit(0);
        }
    }

    /*
       Checks if the game is in the "playing" state.
     */
    private void checkGameState() {
        if (board == null || gui == null || rule == null) {
            throw new IllegalStateException("Game not initialized. Must set a level of play first.");
        }
    }

    /**
      Sets the game to the specified level. <BR>
precondition: a gui must be set before calling this method.
     */
    public void setLevel(int levelNumber) {
        if (gui == null) {
            throw new IllegalStateException("Must set a gui first");
        }
        this.levelNumber = levelNumber;
        Level level = Level.getLevel(levelNumber);
        if (level == null) {
            throw new IllegalArgumentException("No such level.");
        }
        rule = level.getRule();
        board = new Board(level.getRows(), level.getCols());
        board.reset();
        gui.setRowsCols(level.getRows(), level.getCols());
        gui.layOutBoard();
        restartLevel();
    }

    /**
      Sets the graphical user interface for the game.
     */
    public void setGui(GameUI gui) {
        this.gui = gui;
    }
}

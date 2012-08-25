package squares;

/**
  This interface defines the methods required by a graphical
  user interface for the game of squares.
 */
public interface GameUI {

    /**
      Updates the screen to show a given board, level and number of moves.
     */
    public void refreshBoard(Board board, int moves, int levelNumber);

    /**
      Enables a control that allows the user to move to the next level.
     */
    public void allowNextLevel();

    /**
      Sets the dimensions of the board to be displayed.
     */
    public void setRowsCols(int rows, int columns);

    /**
      Lays out the board components on the screen
     */
    public void layOutBoard();

    /**
      Initializes the gui
     */
    public void init();
}

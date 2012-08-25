package squares;

/**
  A level for the game of Squares.
 */
public class Level {

    private Rule rule;
    private int rows, cols;

    private Level(Rule rule, int rows, int cols) {
        this.rule = rule;
        this.rows = rows;
        this.cols = cols;
    }

    /**
      Gets the rule for this level
     */
    public Rule getRule() {
        return rule;
    }

    /**
      Gets the number of columns for a level's board
     */
    public int getCols() {
        return cols;
    }

    /**
      Gets the number of rows for a level's board
     */
    public int getRows() {
        return rows;
    }

    /**
      Factory method that returns an instance of the specified level.
     */
    public static Level getLevel(int levelNumber) {

        switch(levelNumber) {
            case 0:
                return new Level(new FlipRowColumnRule(), 2, 2);
            case 1:
                return new Level(new FlipCellAndNeighborsRule(), 4, 4);
            case 2:
                return new Level(new ChangeRightRule(), 3, 2);
            case 3:
                return new Level(new FlipRowColumnRule(), 5, 5);
            case 4:
                return new Level(new EvenOddRule(), 4, 4);
            case 5:
                return new Level(new FlipNeighborsRule(), 4, 4);
            case 6:
                return new Level(new ChangeRightRule(), 5, 4);
            case 7:
                return new Level(new ChangeRightRule(), 5, 3);
            default:
                return null;
        }


    }


}

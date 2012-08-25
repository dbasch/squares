package squares;

/** 
  This class represents a rectangular board for the game of Squares
 */
public class Board {

    protected int[][] cells;
    protected int rows, cols;

    /** Creates a new board with n rows and m columns */
    public Board(int n, int m) {
        rows = n;
        cols = m;
        cells = new int[n][m];
        reset();
    }

    /** Resets a board to the initial state */
    public void reset() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                cells[i][j] = 0;
            }
        }
    }

    /** Determines whether the game is in the winning state */
    public boolean isWinner() {
        if (rows == 0 || cols == 0) return false;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (0 == cells[i][j]) return false;
            }
        }

        return true;
    }


    /** Sets cell (i, j) to value k */
    public void setCell(int i, int j, int k) {
        if (k != 0 && k != 1) {
            throw new IllegalArgumentException("Cells must be set to 0 or 1");
        }
        cells[i][j] = k;
    }

    /** Gets the value of a given cell (i, j) */
    public int getCell(int i, int j) {
        return cells[i][j];
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }


    /** Maps an integer into a state using the following rule:
      The number is the weighted sum of all cells.
      Cell (i, j) is worth either 
      0 (if it's off) 
      2^((m * i) + j) if it's on

      Implementation note: if the number supplied is 
      greater than 2^(n * m) -1, the number modulo
      2^(n * m) is used.
     */
    public void setState(int numericState) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                cells[i][j] = numericState % 2;
                numericState /= 2;
            }
        }

    }

    /** Maps a state to an integer using the following rule:
      The number is the weighted sum of all cells.
      Cell (i, j) is worth either 
      0, if it's off 
      2^((m * i) + j), if it's on
     */
    public int getState() {
        int numericState = 0;
        int multiplier = 1;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                numericState += multiplier * cells[i][j];
                multiplier *= 2;
            }
        }
        return numericState;
    }


    public void flipCell(int row, int col) {
        cells[row][col] = 1 - cells[row][col];
    }

    public void flipRow(int row) {
        for (int j = 0; j < cols; j++) {
            flipCell(row, j);
        }
    }

    public void flipColumn(int col) {
        for (int i = 0; i < rows; i++) {
            flipCell(i, col);
        }
    }

    /** Flips a cell and all adjacent cells (sharing a side or a corner).
     */
    public void flipCellAndNeighbors(int row, int col) {
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (row + i > -1 && row + i < rows && col + j > -1 && col + j < cols) {
                    flipCell(row + i,  col + j);
                }
            }
        }
    }

    /** Flips a cell and all adjacent cells (sharing a side or a corner), 
      but it wraps around the board: clicking on a corner flips nine
      squares.

      Not valid in boards with less than 4 columns or rows.
     */
    public void flipCellAndNeighborsWrap(int row, int col) {
        if (rows < 4 || cols <4) {
            throw new UnsupportedOperationException("Must have at least 4 rows and columns");
        }
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                flipCell((rows + row + i) % rows, (cols + col + j) % cols);
            }
        }
    }


    /**
      Outputs a textual representation of the board to System.out
     */
    public void print() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(cells[i][j] + " ");
            }
            System.out.println();
        }
    }

}

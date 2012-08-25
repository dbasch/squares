package squares;

/** This rule performs a transformation on the board given by a mathematical 
  formula.
 */
public class ChangeRightRule implements Rule {

    public void transform(Board b, int i, int j) {
        int rows = b.getRows();
        int cols = b.getCols();

        b.flipCell(i, j);
        b.flipCell((i + 1) % rows , j);
        b.flipCell(i , (j + 1) % cols);
        if (i == 3 && j != 1) return;
        b.flipCell((i + j) % rows, j);
    }
}

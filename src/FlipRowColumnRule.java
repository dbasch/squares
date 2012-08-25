package squares;

/** This rule flips the column and the row containing the given cell.
  As an example, for cell (1, 1) the board<BR><BR>

  0 0 1 <BR>
  1 0 1 <BR>
  1 0 0 <BR>

  would become<BR><BR>

  0 1 1 <BR>
  0 1 0 <BR>
  1 1 0 <BR>

 */
public class FlipRowColumnRule implements Rule {

    public void transform(Board b, int i, int j) {
        b.flipRow(i);
        b.flipColumn(j);
        b.flipCell(i, j); //because it's been flipped twice
    }

}

package squares;

/** This rule flips cells according to the following rule:<BR>
  if the sum of the coordinates of the input cell is odd, it flips the row 
  and the column. If not, it flips the cell.
 */
public class EvenOddRule implements Rule {

    public void transform(Board b, int i, int j) {
        for (int ii = 0; ii < b.getRows(); ii++) {
            for (int jj = 0; jj < b.getCols(); jj++) {
                if (jj == j  && (i + j) % 2 == 1) {
                    b.flipCell(ii, jj);
                }
                if (ii == i  && (i + j) % 2 == 1) {
                    b.flipCell(ii, jj);
                }
                if (ii == i & jj == j) {
                    b.flipCell(ii, jj);
                }
            }
        }
    }
}

package squares;

/**
  This rule flips a cell and all its adjacent cells (sharing a side or a corner).
 */
public class FlipCellAndNeighborsRule implements Rule {
    public void transform(Board b, int i, int j) {
        b.flipCellAndNeighbors(i, j);
    }
}

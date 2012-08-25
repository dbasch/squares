package squares;

/**
  This rule flips the neighbors of a cell but not the cell itself.
 */
public class FlipNeighborsRule implements Rule {
    public void transform(Board b, int i, int j) {
        b.flipCellAndNeighbors(i, j);
        b.flipCell(i, j);
    }
}

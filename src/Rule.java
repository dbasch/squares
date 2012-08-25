package squares;

/** A rule is a function that takes as inputs
  a given state for a board and a chosen cell
  and outputs a new state
 */
public interface Rule {

    /** Applies a transformation to board b as
      dictated by the rule applied to the current 
      state of the board and cell (i, j)
     */
    public void transform(Board b, int i, int j);
}

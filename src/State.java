package squares;

/**
  A state in the space of possible positions for a game.
 */

public class State {

    public static final int INFINITE_DISTANCE = Integer.MAX_VALUE;
    private int stateNumber;
    private State predecessor;
    private int distance;

    /**
      Creates a space corresponding to the binary representation of 
      a number.
     */
    public State(int number) {
        stateNumber = number;
        distance = INFINITE_DISTANCE;
        predecessor = null;
    }

    public void setStateNumber(int s) {
        stateNumber = s;
    }

    public int getStateNumber() {
        return stateNumber;
    }

    public int hashCode() {
        return stateNumber;
    }

    public void  setPredecessor(State p) {
        predecessor = p;
    }

    /**
      Gets the predecessor of this state, coming from the initial state.
     */
    public State getPredecessor() {
        return predecessor;
    }

    /**
      Gets the distance from the initial state to this state.
     */
    public void  setDistance(int d) {
        distance = d;
    }

    /**
      Gets the distance from the initial state to this state.
     */
    public int getDistance() {
        return distance;
    }

    public boolean equals(Object o) {
        return equals((State)o);
    }

    public boolean equals(State s) {
        if (this.stateNumber == s.stateNumber) {
            return true;
        }
        else {
            return false;
        }
    }

}

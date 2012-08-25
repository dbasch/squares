package squares;

import java.util.*;

/** This class implements a search algorithm to solve a given game
  by brute force, analyzing the tree of state transitions.
 */
public class GameSolver {

    private int rows = 0;
    private int cols = 0;
    private Board b;
    private Rule r;
    private int depth = 0;
    private int nextStatesCount = 0;
    private int playSequence[];
    private int playSequenceCount[];
    private int numStatesReached = 0;

    public GameSolver(int rows, int cols, Rule rule) {
        this.rows = rows;
        this.cols = cols;
        int totalStates = intpower(2, rows*cols);
        b = new Board(rows, cols);
        r = rule;
    }


    private static int intpower(int base, int exponent) {
        int result = 1;
        for (int i = 0; i < exponent; i++) {
            result *= base;
        }
        return result;
    }


    /* Given a numeric state, get the next numeric state
       after performing the rule transformation
     */
    private int getNextState(int state, int i, int j) {
        b.setState(state);
        r.transform(b, i, j);
        return b.getState();
    }

    /* get all possible states one transition away from the current state */
    private void getNextStates(int currentState, int[] states) {

        nextStatesCount = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int ns = getNextState(currentState, i, j);
                states[nextStatesCount++] = ns;
            }
        }
    }


    /* Print out the sequence of steps that solve the game */
    private void outputWinner(State t) {
        System.out.println("found a winning state with distance " + t.getDistance());
        System.out.println("States reached: " + numStatesReached);
        State u = t;
        while(u != null) {
label_1:
            if (u.getPredecessor() != null) {
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < cols; j++) {
                        if (getNextState(u.getPredecessor().getStateNumber(), i, j) == u.getStateNumber()) {
                            System.out.println("(" + i + ", " + j + ")");
                            break label_1;
                        }
                    }
                }
            }
            u = u.getPredecessor(); 
        }
    }


    /** Solve the game starting from the given state
     */
    public void solve(int state) {

        Set statesReached = new HashSet();
        Set currentStage = new HashSet();
        Set nextStage = new HashSet();
        int states[] = new int[rows*cols];

        //initialize the set of reached states
        //to contain the starting state
        State s0 = new State(state);
        State tempState = new State(state);
        s0.setDistance(0);
        statesReached.add(s0);

        currentStage.add(s0);

        //go through all the state space by trying all state
        //after the initial space. Only quit when there are no
        //new states to visit.
        int i;
        for (i = 0; !currentStage.isEmpty() ;  i++ ) {
            System.out.println("Current depth: " + i);
            nextStage.clear();
            for (Iterator iter = currentStage.iterator(); iter.hasNext(); ) {
                State s = (State)iter.next();
                getNextStates(s.getStateNumber(), states);

                //find all the successors of this state
                for (int j = 0; j < nextStatesCount; j++) {

                    //ignore the state if it has been reached already
                    tempState.setStateNumber(states[j]);
                    if (statesReached.contains(tempState)) {
                        continue;
                    }

                    State t = new State(states[j]);
                    statesReached.add(t);
                    numStatesReached++;
                    t.setPredecessor(s);
                    t.setDistance(s.getDistance() + 1);
                    b.setState(states[j]);
                    if (b.isWinner()) {
                        outputWinner(t);
                        return;
                    }
                    nextStage.add(t);
                }

            }//currentStage

            //make the next level the current
            //and clear the next level
            currentStage.clear();
            Set temp = currentStage;
            currentStage = nextStage;
            nextStage = temp;
        }

        //we couldn't solve the game
        System.out.println("Nothing at depth " + i + ", game is unsolvable");    }


        /** Run the GameSolver program for a given level number
          (from the starting position)<BR>
usage: program <level number>
         */
        public static void main(String args[]) {

            if (args.length != 1) {
                System.out.println("usage: GameSolver <level>");
                System.exit(-1);
            }
            int levelNumber = Integer.valueOf(args[0]).intValue();
            Level level = Level.getLevel(levelNumber);
            if (level == null) {
                System.out.println("No such level");
                System.exit(-1);
            }

            Rule r = level.getRule();
            GameSolver g = new GameSolver(level.getRows(), level.getCols(), r);
            g.solve(0);
        }
}

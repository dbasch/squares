package squares;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.*;

/**
  This class implements the graphical user interface for 
  the game of squares
 */
public class Squares extends JApplet implements GameUI {

    //maximum rows and columns allowed for the game board
    private static final int MAX_ROWS = 5;
    private static final int MAX_COLS = 5;
    private static final Color OFF_COLOR = new Color(0,0,128);
    private static final Color ON_COLOR = new Color (192,0,0);

    //the dimensions of the board must be divisible by 2,3,4 and 5
    private static final int BOARD_WIDTH = 360;
    private static final int BOARD_HEIGHT = 360;

    private JPanel boardPane, gameControlPane;
    private JLabel levelLabel, movesLabel;
    private JButton restartLevelButton, nextLevelButton;

    private int rows, cols;
    private int startLevel = 0;

    private JButton[][] buttons;
    GameController gameController;

    public void init() {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        gameController = new GameController();
        gameController.setGui(this);
        buttons = new JButton[MAX_ROWS][MAX_COLS];
        for (int i = 0; i < MAX_ROWS; i++) {
            for (int j = 0; j < MAX_ROWS; j++) {
                buttons[i][j] = new JButton();
                final int ii = i;
                final int jj = j;
                buttons[i][j].addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                        gameController.cellPressed(ii, jj);
                        }
                        });
            }
        }


        boardPane = new JPanel();
        gameControlPane = new JPanel();
        gameControlPane.setLayout(new BoxLayout(gameControlPane, BoxLayout.PAGE_AXIS));
        levelLabel = new JLabel("Level: 0");
        levelLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        movesLabel = new JLabel("Moves: 0");
        movesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        restartLevelButton = new JButton("Restart level");
        restartLevelButton.setMinimumSize(new Dimension(120,40));
        restartLevelButton.setMaximumSize(new Dimension(120,40));
        restartLevelButton.setPreferredSize(new Dimension(120,40));
        restartLevelButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        restartLevelButton.addActionListener( new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                gameController.restartLevel();
                }
                });

        nextLevelButton = new JButton("Next level");
        nextLevelButton.setMinimumSize(new Dimension(120,40));
        nextLevelButton.setMaximumSize(new Dimension(120,40));
        nextLevelButton.setPreferredSize(new Dimension(120,40));
        nextLevelButton.setEnabled(false);
        nextLevelButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        nextLevelButton.addActionListener( new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                gameController.increaseLevel();
                nextLevelButton.setEnabled(false);
                }
                });

        rows = 0; 
        cols = 0;

        boardPane.setMinimumSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
        boardPane.setMaximumSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
        boardPane.setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
        gameControlPane.add(levelLabel);
        gameControlPane.add(movesLabel);
        gameControlPane.add(restartLevelButton);
        gameControlPane.add(nextLevelButton);

        //organize and display the frame
        getContentPane().setSize(600,400);
        GridBagLayout gridbag = new GridBagLayout();
        getContentPane().setLayout(gridbag);
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(0,0,0,20);
        c.weightx = 0.5;
        gridbag.setConstraints(boardPane, c);
        getContentPane().add(boardPane);
        gridbag.setConstraints(gameControlPane, c);
        getContentPane().add(gameControlPane);
    }

    public void setStartLevel(int startLevel) {
        this.startLevel = startLevel;
    }

    public void start() {
        setVisible(true);
        gameController.setLevel(startLevel);
    }

    public void refreshBoard(Board b, int moves, int levelNumber) {
        for (int i = 0; i < b.getRows(); i++) {
            for (int j = 0; j < b.getCols(); j++) {
                if (b.getCell(i, j) == 0) {
                    buttons[i][j].setBackground(OFF_COLOR);
                    buttons[i][j].setForeground(OFF_COLOR);
                }
                else {
                    buttons[i][j].setBackground(ON_COLOR);
                    buttons[i][j].setForeground(ON_COLOR);
                }

            }
        }

        levelLabel.setText("Level: " + levelNumber);
        movesLabel.setText("Moves: " + moves);
    }

    public void allowNextLevel() {
        nextLevelButton.setEnabled(true);
    }


    /**
      Sets the dimensions of the board
     */
    public void setRowsCols(int r, int c) {
        rows = r;
        cols = c;
    }

    /**
      Lays out the board
     */
    public void layOutBoard() {
        if (rows > 0 && cols > 0 && rows <= MAX_ROWS && cols <= MAX_COLS) {
            boardPane.removeAll();
            boardPane.setLayout(new GridLayout(rows, cols));
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    boardPane.add(buttons[i][j]);
                    (buttons[i][j]).setBackground(OFF_COLOR);
                }
            }
        }
    }

    /**
      Starts a game at the specified level (default = 0)
     */
    public static void main(String args[]) {
        int startLevel = 0;
        if (args.length == 1) {
            startLevel = Integer.valueOf(args[0]).intValue();
        }

        //set up a frame wrapper for the applet
        JFrame gameFrame = new JFrame("Ogeid's squares");
        Squares sq = new Squares();
        sq.setSize(530,400);

        GridBagLayout gridbag = new GridBagLayout();
        gameFrame.getContentPane().setLayout(gridbag);
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(0,0,0,0);
        gridbag.setConstraints(sq, c);
        gameFrame.getContentPane().add(sq);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setDefaultLookAndFeelDecorated(true);
        gameFrame.setSize(530,400);

        //start the game
        gameFrame.setVisible(true);
        sq.setStartLevel(startLevel);
        sq.init();
        sq.start();
        gameFrame.pack();
    }

}

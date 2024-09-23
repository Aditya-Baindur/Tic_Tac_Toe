/** Programmer:Aditya Baindur
 * Date: 23 May 2023
 * Teacher : Mr Bates
 * Program Name: Tic Tic Toe in Java
 * Program Description: This program runs the GUI for Tic Tac Toe
 */

import javax.swing.*;
import java.awt.event.*;

public class TicTacEvent implements ActionListener {
    private TicTac gui;
    private ImageIcon a = new ImageIcon("x.png");
    private ImageIcon b = new ImageIcon("o.png");
    private int clicks = 0;
    private int[][] check = new int[4][4];
    private ImageIcon[][] icons = new ImageIcon[4][4];
    private int xWins = 0;
    private int oWins = 0;

    public TicTacEvent(TicTac gui) {
        this.gui = gui;
        
        // Initialize the check and icons arrays
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                check[row][col] = 0;
                icons[row][col] = null;
            }
        }
    }

    public void actionPerformed(ActionEvent event) {
        JButton source = (JButton) event.getSource();

        int row = -1, col = -1;
        // Find the button that triggered the action event
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (source == gui.boxes[i][j]) {
                    row = i;
                    col = j;
                    break;
                }
            }
        }

        // Process the button click only if it is a valid move
        if (row != -1 && col != -1 && check[row][col] == 0) {
            clicks++;
            if ((clicks % 2) == 1) {
                check[row][col] = 1;
                source.setEnabled(false);
                source.setDisabledIcon(a);
                icons[row][col] = a;
            } else {
                check[row][col] = 2;
                source.setEnabled(false);
                source.setDisabledIcon(b);
                icons[row][col] = b;
            }
            winner();
        }
    }

    private void winner() {
        // Check rows
        for (int row = 0; row < 4; row++) {
            if (check[row][0] != 0 && check[row][0] == check[row][1] && check[row][0] == check[row][2]
                    && check[row][0] == check[row][3]) {
                showWinnerDialog(check[row][0]);
                return;
            }
        }

        // Check columns
        for (int col = 0; col < 4; col++) {
            if (check[0][col] != 0 && check[0][col] == check[1][col] && check[0][col] == check[2][col]
                    && check[0][col] == check[3][col]) {
                showWinnerDialog(check[0][col]);
                return;
            }
        }

        // Check diagonals
        if (check[0][0] != 0 && check[0][0] == check[1][1] && check[0][0] == check[2][2] && check[0][0] == check[3][3]) {
            showWinnerDialog(check[0][0]);
            return;
        }

        if (check[0][3] != 0 && check[0][3] == check[1][2] && check[0][3] == check[2][1] && check[0][3] == check[3][0]) {
            showWinnerDialog(check[0][3]);
            return;
        }

        // Check for a draw
        if (clicks == 16) {
            JOptionPane.showMessageDialog(null, "It's a draw!");
            resetGame();
        }
    }

    private void showWinnerDialog(int player) {
        String winner;
        if (player == 1) {
            winner = "X wins!";
            xWins++;
        } else {
            winner = "O wins!";
            oWins++;
        }
        JOptionPane.showMessageDialog(null, winner);
        JOptionPane.showMessageDialog(null, "Score:\nX: " + xWins + "\nO: " + oWins);
        resetGame();
    }

    private void resetGame() {
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                check[row][col] = 0;
                gui.boxes[row][col].setEnabled(true);
                gui.boxes[row][col].setIcon(gui.back);
            }
        }
        clicks = 0;
    }
}

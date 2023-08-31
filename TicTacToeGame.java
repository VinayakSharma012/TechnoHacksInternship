import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGame {
    private JFrame frame;
    private JButton[][] buttons;
    private JLabel statusLabel;

    private boolean xTurn = true;
    private boolean gameWon = false;

    public TicTacToeGame() {
        frame = new JFrame("Tic Tac Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setLayout(new BorderLayout());

        buttons = new JButton[3][3];
        statusLabel = new JLabel("X's Turn");

        JPanel gridPanel = new JPanel(new GridLayout(3, 3));
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new JButton("");
                buttons[row][col].setFont(new Font("Arial", Font.PLAIN, 48));
                buttons[row][col].addActionListener(new ButtonClickListener(row, col));
                gridPanel.add(buttons[row][col]);
            }
        }

        frame.add(gridPanel, BorderLayout.CENTER);
        frame.add(statusLabel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        private int row;
        private int col;

        public ButtonClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public void actionPerformed(ActionEvent e) {
            if (!gameWon && buttons[row][col].getText().equals("")) {
                if (xTurn) {
                    buttons[row][col].setText("X");
                    statusLabel.setText("O's Turn");
                } else {
                    buttons[row][col].setText("O");
                    statusLabel.setText("X's Turn");
                }

                xTurn = !xTurn;
                checkWin();
            }
        }
    }

    private void checkWin() {
        String winner = "";

        // Check rows, columns, and diagonals for a win
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().equals(buttons[i][1].getText())
                    && buttons[i][1].getText().equals(buttons[i][2].getText())
                    && !buttons[i][0].getText().equals("")) {
                winner = buttons[i][0].getText();
                break;
            }
            if (buttons[0][i].getText().equals(buttons[1][i].getText())
                    && buttons[1][i].getText().equals(buttons[2][i].getText())
                    && !buttons[0][i].getText().equals("")) {
                winner = buttons[0][i].getText();
                break;
            }
        }

        if (buttons[0][0].getText().equals(buttons[1][1].getText())
                && buttons[1][1].getText().equals(buttons[2][2].getText())
                && !buttons[0][0].getText().equals("")) {
            winner = buttons[0][0].getText();
        }
        if (buttons[0][2].getText().equals(buttons[1][1].getText())
                && buttons[1][1].getText().equals(buttons[2][0].getText())
                && !buttons[0][2].getText().equals("")) {
            winner = buttons[0][2].getText();
        }

        if (!winner.equals("")) {
            statusLabel.setText(winner + " Wins!");
            gameWon = true;
            showWinDialog(winner);
        }
    }

    private void showWinDialog(String winner) {
        String message = winner + " wins!\nDo you want to play again?";
        int choice = JOptionPane.showConfirmDialog(frame, message, "Game Over", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            resetGame();
        } else {
            System.exit(0);
        }
    }

    private void resetGame() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setText("");
            }
        }
        gameWon = false;
        xTurn = true;
        statusLabel.setText("X's Turn");
    }

    public static void main(String[] args) {
        new TicTacToeGame();
    }
}

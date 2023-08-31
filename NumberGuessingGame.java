import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGuessingGame {
    private JFrame frame;
    private JLabel titleLabel, hintLabel;
    private JTextField guessField;
    private JButton guessButton;

    private int targetNumber;
    private int attempts;

    public NumberGuessingGame() {
        frame = new JFrame("Number Guessing Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new GridLayout(4, 1));

        titleLabel = new JLabel("<html><div style='text-align: center;'>Guess a Number between 1 and 100</div></html>");
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 14));  // Small font size

        hintLabel = new JLabel(" ");
        hintLabel.setHorizontalAlignment(JLabel.CENTER);

        guessField = new JTextField();
        guessButton = new JButton("Guess");
        guessButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkGuess();
            }
        });

        frame.add(titleLabel);
        frame.add(hintLabel);
        frame.add(guessField);
        frame.add(guessButton);

        initGame();
        frame.setVisible(true);
    }

    private void initGame() {
        Random random = new Random();
        targetNumber = random.nextInt(100) + 1;
        attempts = 0;

        hintLabel.setText("");
        guessField.setText("");
        guessButton.setEnabled(true);
    }

    private void checkGuess() {
        int guessedNumber;
        try {
            guessedNumber = Integer.parseInt(guessField.getText());
        } catch (NumberFormatException ex) {
            hintLabel.setText("Invalid input. Please enter a valid number.");
            guessField.setText("");
            return;
        }

        attempts++;

        if (guessedNumber < targetNumber) {
            hintLabel.setText("Try a higher number.");
        } else if (guessedNumber > targetNumber) {
            hintLabel.setText("Try a lower number.");
        } else {
            String message = "Congratulations! You guessed it in " + attempts + " attempts.";
            JOptionPane.showMessageDialog(frame, message, "Congratulations!", JOptionPane.INFORMATION_MESSAGE);
            initGame();
        }

        guessField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new NumberGuessingGame();
            }
        });
    }
}

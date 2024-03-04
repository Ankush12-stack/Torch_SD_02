import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Guess extends JFrame {
    private int randomNumber;
    private int attemptsLeft;
    private JTextField guessField;
    private JButton guessButton;
    private JLabel resultLabel;
    private JLabel attemptsLabel;

    public Guess() {
        setTitle("Guessing Game");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Generate a random number between 1 and 100
        randomNumber = (int) (Math.random() * 100) + 1;
        attemptsLeft = 5;

        // Create components
        JLabel guessLabel = new JLabel("Enter your guess (1-100):");
        guessField = new JTextField(10);
        guessButton = new JButton("Guess");
        resultLabel = new JLabel("");
        attemptsLabel = new JLabel("Attempts left: " + attemptsLeft);

        // Set layout
        setLayout(new GridLayout(4, 1));

        // Add components to the frame
        add(guessLabel);
        add(guessField);
        add(guessButton);
        add(resultLabel);
        add(attemptsLabel);

        // Add action listener to the guess button
        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkGuess();
            }
        });
    }

    private void checkGuess() {
        String guessText = guessField.getText();
        int guess;

        try {
            guess = Integer.parseInt(guessText);
        } catch (NumberFormatException e) {
            resultLabel.setText("Invalid input!");
            return;
        }

        if (guess < 1 || guess > 100) {
            resultLabel.setText("Please enter a number between 1 and 100.");
            return;
        }

        attemptsLeft--;

        if (guess == randomNumber) {
            resultLabel.setText("Congratulations! You guessed the number!");
            guessButton.setEnabled(false);
        } else if (guess < randomNumber) {
            resultLabel.setText("Too low! Try again.");
        } else {
            resultLabel.setText("Too high! Try again.");
        }

        attemptsLabel.setText("Attempts left: " + attemptsLeft);

        if (attemptsLeft == 0) {
            resultLabel.setText("Sorry, you've run out of attempts. The number was " + randomNumber + ".");
            guessButton.setEnabled(false);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Guess().setVisible(true);
            }
        });
    }
}

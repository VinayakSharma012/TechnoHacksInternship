import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleCalculator {
    private JFrame frame;
    private JTextField textField;
    private JButton[] numberButtons;
    private JButton[] functionButtons;
    private JButton addButton, subButton, mulButton, divButton, eqButton, clrButton;
    private JPanel panel;

    private double num1, num2, result;
    private char operator;

    public SimpleCalculator() {
        frame = new JFrame("Simple Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);
        frame.setLayout(null);

        textField = new JTextField();
        textField.setBounds(30, 30, 340, 50);
        textField.setFont(new Font("Arial", Font.PLAIN, 24));
        textField.setEditable(false);

        numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].setFont(new Font("Arial", Font.PLAIN, 18));
        }

        functionButtons = new JButton[7];
        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        eqButton = new JButton("=");
        clrButton = new JButton("C");

        functionButtons[0] = addButton;
        functionButtons[1] = subButton;
        functionButtons[2] = mulButton;
        functionButtons[3] = divButton;
        functionButtons[4] = eqButton;
        functionButtons[5] = clrButton;

        for (int i = 0; i < 6; i++) {
            functionButtons[i].setFont(new Font("Arial", Font.PLAIN, 18));
        }

        panel = new JPanel();
        panel.setBounds(30, 100, 340, 340);
        panel.setLayout(new GridLayout(4, 4, 10, 10));

        for (int i = 1; i <= 9; i++) {
            panel.add(numberButtons[i]);
        }
        panel.add(functionButtons[0]);
        panel.add(numberButtons[0]);
        for (int i = 1; i < 4; i++) {
            panel.add(functionButtons[i]);
        }
        for (int i = 4; i < 6; i++) {
            panel.add(functionButtons[i]);
        }

        frame.add(textField);
        frame.add(panel);

        for (int i = 0; i < 10; i++) {
            final int num = i;
            numberButtons[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    textField.setText(textField.getText() + num);
                }
            });
        }

        for (int i = 0; i < 4; i++) {
            final char op = functionButtons[i].getText().charAt(0);
            functionButtons[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    num1 = Double.parseDouble(textField.getText());
                    operator = op;
                    textField.setText("");
                }
            });
        }

        eqButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                num2 = Double.parseDouble(textField.getText());
                switch (operator) {
                    case '+':
                        result = num1 + num2;
                        break;
                    case '-':
                        result = num1 - num2;
                        break;
                    case '*':
                        result = num1 * num2;
                        break;
                    case '/':
                        result = num1 / num2;
                        break;
                }
                textField.setText(String.valueOf(result));
            }
        });

        clrButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textField.setText("");
            }
        });

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new SimpleCalculator();
    }
}


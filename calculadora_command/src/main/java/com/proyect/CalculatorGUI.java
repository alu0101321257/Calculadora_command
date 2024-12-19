package main.java.com.proyect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorGUI {
    private Calculator calculator;
    private JFrame frame;
    private JTextField display;
    private double currentResult;
    private double currentValue;
    private String currentOperation;

    public CalculatorGUI() {
        calculator = new Calculator();
        frame = new JFrame("Calculadora");
        display = new JTextField();
        display.setEditable(false);
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        display.setFont(new Font("Arial", Font.BOLD, 24));
        display.setPreferredSize(new Dimension(300, 50));

        frame.setLayout(new BorderLayout(10, 10));
        frame.add(display, BorderLayout.NORTH);
        frame.add(createButtonsPanel(), BorderLayout.CENTER);

        frame.setSize(400, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        currentResult = 0;
        currentValue = 0;
        currentOperation = "";
    }

    private JPanel createButtonsPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 4, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] buttonLabels = {
                "7", "8", "9", "+",
                "4", "5", "6", "-",
                "1", "2", "3", "*",
                "0", "^", "√", "/",
                "CE", "<-", ".", "="
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setFont(new Font("Arial", Font.BOLD, 18));
            button.setFocusPainted(false);
            button.setBackground(Color.LIGHT_GRAY);
            button.setForeground(Color.BLACK);

            // Customize colors for specific buttons
            if (label.equals("CE")) {
                button.setBackground(Color.RED);
                button.setForeground(Color.WHITE);
            } else if (label.equals("=")) {
                button.setBackground(Color.BLUE);
                button.setForeground(Color.WHITE);
            }

            button.addActionListener(e -> handleButtonClick(e.getActionCommand()));
            panel.add(button);
        }

        return panel;
    }

    private void handleButtonClick(String command) {
        try {
            switch (command) {
                case "CE":
                    calculator.clear();
                    currentResult = 0;
                    currentValue = 0;
                    currentOperation = "";
                    display.setText("");
                    break;
                case "<-":
                    String text = display.getText();
                    if (!text.isEmpty()) {
                        display.setText(text.substring(0, text.length() - 1));
                    }
                    break;
                case "=":
                    if (!display.getText().trim().isEmpty() && !currentOperation.isEmpty()) {
                        currentValue = Double.parseDouble(display.getText().trim());
                        performPendingOperation();
                        display.setText(String.valueOf(currentResult));
                        currentOperation = "";
                    }
                    break;
                case "√":
                    if (!display.getText().isEmpty()) {
                        double value = Double.parseDouble(display.getText());
                        currentResult = Math.sqrt(value);
                        display.setText(String.valueOf(currentResult));
                    }
                    break;
                case "+":
                case "-":
                case "*":
                case "/":
                case "^":
                    if (!display.getText().trim().isEmpty()) {
                        currentValue = Double.parseDouble(display.getText().trim());
                        performPendingOperation();
                        currentOperation = command;
                        display.setText("");
                    }
                    break;
                default:
                    display.setText(display.getText() + command);
            }
        } catch (Exception ex) {
            display.setText("Error");
        }
    }

    private void performPendingOperation() {
        switch (currentOperation) {
            case "+":
                currentResult = calculator.add(currentResult, currentValue);
                break;
            case "-":
                currentResult = calculator.subtract(currentResult, currentValue);
                break;
            case "*":
                currentResult = calculator.multiply(currentResult, currentValue);
                break;
            case "/":
                currentResult = calculator.divide(currentResult, currentValue);
                break;
            case "^":
                currentResult = calculator.power(currentResult, currentValue);
                break;
            default:
                currentResult = currentValue;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CalculatorGUI::new);
    }
}

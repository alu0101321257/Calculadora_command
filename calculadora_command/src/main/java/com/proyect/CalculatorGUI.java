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
        display.setPreferredSize(new Dimension(400, 50));

        frame.setLayout(new BorderLayout());
        frame.add(display, BorderLayout.NORTH);
        frame.add(createButtonsPanel(), BorderLayout.CENTER);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        currentResult = 0;
        currentValue = 0;
        currentOperation = "";
    }

    private JPanel createButtonsPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 4));

        // Add number buttons and operations
        addButton(panel, "7");
        addButton(panel, "8");
        addButton(panel, "9");
        addOperationButton(panel, "/");

        addButton(panel, "4");
        addButton(panel, "5");
        addButton(panel, "6");
        addOperationButton(panel, "*");

        addButton(panel, "1");
        addButton(panel, "2");
        addButton(panel, "3");
        addOperationButton(panel, "-");

        addButton(panel, "0");
        addButton(panel, ".");
        addOperationButton(panel, "^");

        JButton deleteButton = new JButton("<-");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String currentText = display.getText();
                if (!currentText.isEmpty()) {
                    display.setText(currentText.substring(0, currentText.length() - 1));
                }
            }
        });

        JButton clearButton = new JButton("CE");
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculator.clear();
                currentResult = 0;
                currentValue = 0;
                currentOperation = "";
                display.setText("");
            }
        });

        panel.add(deleteButton);
        panel.add(clearButton);
        addOperationButton(panel, "+");
        addResultButton(panel);

        return panel;
    }

    private void addButton(JPanel panel, String text) {
        JButton button = new JButton(text);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String command = e.getActionCommand();
                display.setText(display.getText() + command);
            }
        });
        panel.add(button);
    }

    private void addOperationButton(JPanel panel, String operation) {
        JButton button = new JButton(operation);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!display.getText().trim().isEmpty()) {
                    try {
                        currentValue = Double.parseDouble(display.getText().trim());
                        performPendingOperation();
                        currentOperation = operation;
                        display.setText("");
                    } catch (NumberFormatException ex) {
                        display.setText("Error");
                    }
                }
            }
        });
        panel.add(button);
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

    private void addResultButton(JPanel panel) {
        JButton button = new JButton("=");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!display.getText().trim().isEmpty() && !currentOperation.isEmpty()) {
                    try {
                        currentValue = Double.parseDouble(display.getText().trim());
                        performPendingOperation();
                        display.setText(currentResult + "");
                        currentOperation = "";
                    } catch (NumberFormatException ex) {
                        display.setText("Error");
                    }
                }
            }
        });
        panel.add(button);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CalculatorGUI();
            }
        });
    }
}

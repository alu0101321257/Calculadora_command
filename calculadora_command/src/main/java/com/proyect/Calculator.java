package main.java.com.proyect;

public class Calculator {
    private double result;
    private double currentInput;
    private StringBuilder currentExpression;
    private Command pendingCommand;

    public Calculator() {
        this.result = 0;
        this.currentInput = 0;
        this.currentExpression = new StringBuilder();
        this.pendingCommand = null;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public double getCurrentInput() {
        return currentInput;
    }

    public void setCurrentInput(double currentInput) {
        this.currentInput = currentInput;
    }

    public Command getPendingCommand() {
        return pendingCommand;
    }

    public void setPendingCommand(Command pendingCommand) {
        this.pendingCommand = pendingCommand;
    }

    public String getCurrentExpression() {
        return currentExpression.toString();
    }

    public void appendToExpression(String text) {
        currentExpression.append(text);
    }

    public void clearExpression() {
        currentExpression.setLength(0);
    }

    public double add(double a, double b) {
        return a + b;
    }

    public double subtract(double a, double b) {
        return a - b;
    }

    public double multiply(double a, double b) {
        return a * b;
    }

    public double divide(double a, double b) {
        if (b == 0) {
            throw new IllegalArgumentException("Cannot divide by zero");
        }
        return a / b;
    }

    public double power(double a, double b) {
        return Math.pow(a, b);
    }

    public void clear() {
        result = 0;
        currentInput = 0;
        pendingCommand = null;
        clearExpression();
    }

    public void deleteLastDigit() {
        if (currentExpression.length() > 0) {
            currentExpression.setLength(currentExpression.length() - 1);
        }
    }
}

package main.java.com.proyect;

public class SubtractCommand implements Command {
    private Calculator calculator;
    private double a;
    private double b;

    public SubtractCommand(Calculator calculator, double a, double b) {
        this.calculator = calculator;
        this.a = a;
        this.b = b;
    }

    @Override
    public void execute() {
        calculator.setResult(calculator.subtract(a, b));
    }
}
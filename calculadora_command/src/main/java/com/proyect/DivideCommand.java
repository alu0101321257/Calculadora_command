package main.java.com.proyect;

public class DivideCommand implements Command {
    private Calculator calculator;
    private double a;
    private double b;

    public DivideCommand(Calculator calculator, double a, double b) {
        this.calculator = calculator;
        this.a = a;
        this.b = b;
    }

    @Override
    public void execute() {
        calculator.setResult(calculator.divide(a, b));
    }
}

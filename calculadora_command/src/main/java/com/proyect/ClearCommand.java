package main.java.com.proyect;

public class ClearCommand implements Command {
    private Calculator calculator;

    public ClearCommand(Calculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public void execute() {
        calculator.clear();
    }
}



package main.java.com.proyect;

public class ResultCommand implements Command {
    private Calculator calculator;

    public ResultCommand(Calculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public void execute() {
        Command pendingCommand = calculator.getPendingCommand();
        if (pendingCommand != null) {
            pendingCommand.execute();
            calculator.setPendingCommand(null);
        }
    }
}

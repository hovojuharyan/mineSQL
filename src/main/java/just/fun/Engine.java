package just.fun;

public class Engine {
    public void execute(CommandDto commandDto) {
        Operation operation = OperationResolver.resolve(commandDto);
        operation.operate();
    }
}

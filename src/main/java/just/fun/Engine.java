package just.fun;

import just.fun.operations.OperationResolver;

public class Engine {
    public void execute(CommandDto commandDto) {
        Operation operation = OperationResolver.resolve(commandDto);
        System.out.println(operation.operate());
    }
}

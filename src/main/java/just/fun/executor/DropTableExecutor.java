package just.fun.executor;

import just.fun.command.DropTableCommand;
import just.fun.domain.usecase.DropTable;

public final class DropTableExecutor implements CommandExecutor<DropTableCommand, DropTableCommand.Builder> {
    @Override
    public void execute(DropTableCommand.Builder builder) {
        DropTableCommand command = builder.build();
        new DropTable(command.tableName(), command.dropper()).run();
    }
}

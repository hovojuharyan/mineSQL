package just.fun.executor;

import just.fun.command.InsertCommand;
import just.fun.domain.usecase.Insert;

public final class InsertExecutor implements CommandExecutor<InsertCommand, InsertCommand.Builder> {
    @Override
    public void execute(InsertCommand.Builder builder) {
        InsertCommand command = builder.build();
        new Insert(command.row(), command.rowSerializer()).run();
    }
}

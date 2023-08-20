package just.fun.executor;

import just.fun.command.CreateTableCommand;
import just.fun.domain.usecase.CreateTable;

public final class CreateTableExecutor implements CommandExecutor<CreateTableCommand, CreateTableCommand.Builder> {

    @Override
    public void execute(CreateTableCommand.Builder builder) {
        CreateTableCommand command = builder.build();
        new CreateTable(command.metadata(), command.metadataSerializer()).run();
    }
}

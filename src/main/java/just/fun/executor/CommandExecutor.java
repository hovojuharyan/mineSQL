package just.fun.executor;

import just.fun.command.CommandBuilder;

public sealed interface CommandExecutor<CT, BT extends CommandBuilder<CT>>
        permits CreateTableExecutor, DropTableExecutor, InsertExecutor, SelectExecutor, UpdateExecutor {
    void execute(BT builder);

    CreateTableExecutor CREATE_TABLE_EXECUTOR = new CreateTableExecutor();
    DropTableExecutor DROP_TABLE_EXECUTOR = new DropTableExecutor();
    InsertExecutor INSERT_EXECUTOR = new InsertExecutor();
    SelectExecutor SELECT_EXECUTOR = new SelectExecutor();
    UpdateExecutor UPDATE_EXECUTOR = new UpdateExecutor();
}

package just.fun.command;

public sealed interface CommandBuilder<CT>
        permits CreateTableCommand.Builder, DropTableCommand.Builder, InsertCommand.Builder,
        SelectQuery.Builder, UpdateCommand.Builder {
    CT build();
}

package just.fun.executor;

import just.fun.command.SelectQuery;
import just.fun.domain.usecase.Select;

public final class SelectExecutor implements CommandExecutor<SelectQuery, SelectQuery.Builder> {
    @Override
    public void execute(SelectQuery.Builder builder) {
        SelectQuery command = builder.build();
        new Select(command.dataDeserializer(), command.queriedColumns(), command.conditions()).run();
    }
}

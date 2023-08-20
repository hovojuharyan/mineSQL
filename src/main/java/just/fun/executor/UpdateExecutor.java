package just.fun.executor;

import just.fun.command.UpdateCommand;
import just.fun.domain.usecase.Update;

public final class UpdateExecutor implements CommandExecutor<UpdateCommand, UpdateCommand.Builder> {
    @Override
    public void execute(UpdateCommand.Builder builder) {
        UpdateCommand command = builder.build();
        new Update(command.dataSerializer(), command.dataDeserializer(), command.conditions(), command.updates()).run();
    }
}

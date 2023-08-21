package just.fun.command;

import just.fun.domain.schema.Conditions;
import just.fun.domain.schema.Data;
import just.fun.domain.schema.Updates;
import just.fun.serialization.DataSerializer;
import just.fun.serialization.Serializer;

public class UpdateCommand {
    private DataSerializer dataSerializer;
    private Conditions conditions;
    private Updates updates;

    private UpdateCommand() {
    }

    public DataSerializer dataSerializer() {
        return dataSerializer;
    }

    public Conditions conditions() {
        return conditions;
    }

    public Updates updates() {
        return updates;
    }

    public static final class Builder implements CommandBuilder<UpdateCommand> {
        private final UpdateCommand updateCommand;

        private Builder() {
            this.updateCommand = new UpdateCommand();
        }

        public Builder dataSerializer(DataSerializer dataSerializer) {
            updateCommand.dataSerializer = dataSerializer;
            return this;
        }

        public Builder conditions(Conditions conditions) {
            updateCommand.conditions = conditions;
            return this;
        }

        public Builder updates(Updates updates) {
            updateCommand.updates = updates;
            return this;
        }

        public UpdateCommand build() {
            return updateCommand;
        }
    }

}

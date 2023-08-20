package just.fun.command;

import just.fun.domain.schema.Conditions;
import just.fun.domain.schema.Data;
import just.fun.domain.schema.Updates;
import just.fun.serialization.Deserializer;
import just.fun.serialization.Serializer;

public class UpdateCommand {
    private Serializer<Data> dataSerializer;
    private Deserializer<Data> dataDeserializer;
    private Conditions conditions;
    private Updates updates;

    private UpdateCommand() {
    }

    public Serializer<Data> dataSerializer() {
        return dataSerializer;
    }

    public Deserializer<Data> dataDeserializer() {
        return dataDeserializer;
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

        public Builder dataSerializer(Serializer<Data> dataSerializer) {
            updateCommand.dataSerializer = dataSerializer;
            return this;
        }

        public Builder dataDeserializer(Deserializer<Data> dataDeserializer) {
            updateCommand.dataDeserializer = dataDeserializer;
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

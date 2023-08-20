package just.fun.command;

import just.fun.domain.schema.Metadata;
import just.fun.serialization.Deserializer;
import just.fun.serialization.Serializer;

import java.util.Objects;

public final class CreateTableCommand {
    private Metadata metadata;
    private Serializer<Metadata> metadataSerializer;

    private CreateTableCommand() {
    }

    public Metadata metadata() {
        return metadata;
    }

    public Serializer<Metadata> metadataSerializer() {
        return metadataSerializer;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (CreateTableCommand) obj;
        return Objects.equals(this.metadata, that.metadata) &&
                Objects.equals(this.metadataSerializer, that.metadataSerializer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(metadata, metadataSerializer);
    }

    @Override
    public String toString() {
        return "CreateTableCommand[" +
                "metadata=" + metadata + ", " +
                "metadataSerializer=" + metadataSerializer + ']';
    }

    public Builder builder() {
        return new Builder();
    }

    public static final class Builder implements CommandBuilder<CreateTableCommand> {
        private final CreateTableCommand createTableCommand;

        private Builder() {
            this.createTableCommand = new CreateTableCommand();
        }

        public Builder metadataFroDeserializer(Deserializer<Metadata> metadataDeserializer) {
            createTableCommand.metadata = metadataDeserializer.deserialize();
            return this;
        }

        public Builder metadataSerializer(Serializer<Metadata> metadataSerializer) {
            createTableCommand.metadataSerializer = metadataSerializer;
            return this;
        }

        @Override
        public CreateTableCommand build() {
            return createTableCommand;
        }
    }
}

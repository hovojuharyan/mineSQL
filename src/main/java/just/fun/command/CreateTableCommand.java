package just.fun.command;

import just.fun.domain.schema.Metadata;
import just.fun.serialization.MetadataSerializer;

import java.util.Objects;

public final class CreateTableCommand {
    private Metadata metadata;
    private MetadataSerializer metadataSerializer;

    private CreateTableCommand() {
    }

    public Metadata metadata() {
        return metadata;
    }

    public MetadataSerializer metadataSerializer() {
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

        public Builder metadata(Metadata metadata) {
            createTableCommand.metadata = metadata;
            return this;
        }

        public Builder metadataSerializer(MetadataSerializer metadataSerializer) {
            createTableCommand.metadataSerializer = metadataSerializer;
            return this;
        }

        @Override
        public CreateTableCommand build() {
            return createTableCommand;
        }
    }
}

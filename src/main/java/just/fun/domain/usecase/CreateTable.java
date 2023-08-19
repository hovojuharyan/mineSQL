package just.fun.domain.usecase;

import just.fun.domain.schema.Metadata;
import just.fun.serialization.Serializer;

public class CreateTable {
    private final Metadata metadata;
    private final Serializer<Metadata> metadataSerializer;

    public CreateTable(Metadata metadata, Serializer<Metadata> metadataSerializer) {
        this.metadata = metadata;
        this.metadataSerializer = metadataSerializer;
    }

    public void run() {
        metadataSerializer.serialize(metadata);
    }
}

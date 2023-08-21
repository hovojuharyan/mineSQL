package just.fun.domain.usecase;

import just.fun.domain.schema.Metadata;
import just.fun.serialization.MetadataSerializer;

public class CreateTable {
    private final Metadata metadata;
    private final MetadataSerializer metadataSerializer;

    public CreateTable(Metadata metadata, MetadataSerializer metadataSerializer) {
        this.metadata = metadata;
        this.metadataSerializer = metadataSerializer;
    }

    public void run() {
        metadataSerializer.serialize(metadata);
    }
}

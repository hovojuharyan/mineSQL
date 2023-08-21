package just.fun.domain.usecase;

import just.fun.domain.response.Response;
import just.fun.domain.schema.Metadata;
import just.fun.serialization.MetadataSerializer;

public class CreateTable implements Command {
    private final String tableName;
    private final Metadata metadata;
    private final MetadataSerializer metadataSerializer;

    public CreateTable(String tableName, Metadata metadata, MetadataSerializer metadataSerializer) {
        this.tableName = tableName;
        this.metadata = metadata;
        this.metadataSerializer = metadataSerializer;
    }

    @Override
    public Response run() {
        metadataSerializer.serialize(tableName, metadata);
        return Response.ok("CREATED TABLE " + metadata.tableName());
    }
}

package just.fun.domain.usecase;

import just.fun.domain.response.Response;
import just.fun.domain.schema.Metadata;
import just.fun.domain.schema.unique.UniqueConstraint;
import just.fun.domain.schema.unique.UniqueConstraintsSet;
import just.fun.serialization.MetadataSerializer;
import just.fun.serialization.UniqueConstraintsSetSerializer;

import java.util.List;

public class CreateTable implements Command {
    private final String tableName;
    private final Metadata metadata;
    private final MetadataSerializer metadataSerializer;
    private final List<UniqueConstraint> uniqueConstraints;
    private final UniqueConstraintsSetSerializer uniqueConstraintsSetSerializer;

    public CreateTable(String tableName,
                       Metadata metadata,
                       MetadataSerializer metadataSerializer,
                       List<UniqueConstraint> uniqueConstraints,
                       UniqueConstraintsSetSerializer uniqueConstraintsSetSerializer) {
        this.tableName = tableName;
        this.metadata = metadata;
        this.metadataSerializer = metadataSerializer;
        this.uniqueConstraints = uniqueConstraints;
        this.uniqueConstraintsSetSerializer = uniqueConstraintsSetSerializer;
    }

    @Override
    public Response run() {
        metadataSerializer.serialize(tableName, metadata);
        uniqueConstraintsSetSerializer.serialize(tableName, UniqueConstraintsSet.bunch(uniqueConstraints));
        return Response.ok("CREATED TABLE " + metadata.tableName());
    }
}

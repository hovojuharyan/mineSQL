package just.fun.domain.usecase;

import just.fun.domain.schema.Metadata;
import just.fun.domain.schema.Row;
import just.fun.serialization.MetadataDeserializer;
import just.fun.serialization.Serializer;

public class Insert {
    Row row;
    MetadataDeserializer metadataDeserializer;
    Serializer<Row> serializer;

    public void run() {
        serializer.serialize(row);
    }

}

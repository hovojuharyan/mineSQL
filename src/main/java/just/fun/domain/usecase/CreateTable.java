package just.fun.domain.usecase;

import just.fun.domain.schema.Metadata;
import just.fun.serialization.Serializer;

public class CreateTable {
    Metadata metadata;
    Serializer<Metadata> serializer;

    public void run() {
        serializer.serialize(metadata);
    }
}

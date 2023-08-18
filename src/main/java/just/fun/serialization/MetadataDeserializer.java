package just.fun.serialization;

import just.fun.domain.schema.Metadata;

public interface MetadataDeserializer extends Deserializer<Metadata> {
    @Override
    Metadata deserialize();
}

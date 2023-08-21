package just.fun.serialization;

import just.fun.domain.schema.Data;

public interface DataSerializer extends Serializer<Data>, Deserializer<Data> {
}

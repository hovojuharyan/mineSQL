package just.fun.serialization;

import just.fun.domain.schema.Data;

public interface DataDeserializer extends Deserializer<Data> {
    @Override
    Data deserialize();
}

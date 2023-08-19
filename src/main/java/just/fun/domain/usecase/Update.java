package just.fun.domain.usecase;

import just.fun.domain.schema.Column;
import just.fun.domain.schema.Data;
import just.fun.serialization.Deserializer;
import just.fun.serialization.Serializer;

import java.util.Map;
import java.util.function.Predicate;

public class Update {
    Serializer<Data> dataSerializer;
    Deserializer<Data> dataDeserializer;
    Map<Column, Predicate<String>> conditions;
    Map<Column, String> updates;

    public void run() {
        Data data = dataDeserializer.deserialize();
        Data updated = data.filter(conditions)
                .updateRows(updates);
        dataSerializer.serialize(updated);
    }


}

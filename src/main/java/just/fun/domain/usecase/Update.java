package just.fun.domain.usecase;

import just.fun.domain.schema.Conditions;
import just.fun.domain.schema.Data;
import just.fun.domain.schema.Updates;
import just.fun.serialization.Deserializer;
import just.fun.serialization.Serializer;

public class Update {
    Serializer<Data> dataSerializer;
    Deserializer<Data> dataDeserializer;
    Conditions conditions;
    Updates updates;

    public Update(Serializer<Data> dataSerializer, Deserializer<Data> dataDeserializer, Conditions conditions, Updates updates) {
        this.dataSerializer = dataSerializer;
        this.dataDeserializer = dataDeserializer;
        this.conditions = conditions;
        this.updates = updates;
    }

    public void run() {
        Data data = dataDeserializer.deserialize();
        Data updated = data.filter(conditions)
                .updateRows(updates);
        dataSerializer.serialize(updated);
    }


}

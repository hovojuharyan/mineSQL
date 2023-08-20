package just.fun.domain.usecase;

import just.fun.domain.schema.Columns;
import just.fun.domain.schema.Conditions;
import just.fun.domain.schema.Data;
import just.fun.serialization.Deserializer;

public class Select {
    Deserializer<Data> dataDeserializer;
    Columns queriedColumns;
    Conditions conditions;

    public Select(Deserializer<Data> dataDeserializer, Columns queriedColumns, Conditions conditions) {
        this.dataDeserializer = dataDeserializer;
        this.queriedColumns = queriedColumns;
        this.conditions = conditions;
    }

    public Data run() {
        Data data = dataDeserializer.deserialize();
        return data.filter(conditions)
                .onlyColumns(queriedColumns);
    }

}

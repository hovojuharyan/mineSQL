package just.fun.domain.usecase;

import just.fun.domain.schema.Columns;
import just.fun.domain.schema.Conditions;
import just.fun.domain.schema.Data;
import just.fun.serialization.DataSerializer;

public class Select {
    DataSerializer dataSerializer;
    Columns queriedColumns;
    Conditions conditions;

    public Select(DataSerializer dataSerializer, Columns queriedColumns, Conditions conditions) {
        this.dataSerializer = dataSerializer;
        this.queriedColumns = queriedColumns;
        this.conditions = conditions;
    }

    public Data run() {
        Data data = dataSerializer.deserialize();
        return data.filter(conditions)
                .onlyColumns(queriedColumns);
    }

}

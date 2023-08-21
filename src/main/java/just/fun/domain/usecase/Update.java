package just.fun.domain.usecase;

import just.fun.domain.schema.Conditions;
import just.fun.domain.schema.Data;
import just.fun.domain.schema.Updates;
import just.fun.serialization.DataSerializer;

public class Update {
    DataSerializer dataSerializer;
    Conditions conditions;
    Updates updates;

    public Update(DataSerializer dataSerializer, Conditions conditions, Updates updates) {
        this.dataSerializer = dataSerializer;
        this.conditions = conditions;
        this.updates = updates;
    }

    public void run() {
        Data data = dataSerializer.deserialize();
        Data updated = data.filter(conditions)
                .updateRows(updates);
        dataSerializer.serialize(updated);
    }


}

package just.fun.domain.usecase;

import just.fun.domain.response.Response;
import just.fun.domain.schema.Conditions;
import just.fun.domain.schema.Data;
import just.fun.domain.schema.Updates;
import just.fun.serialization.DataSerializer;

public class Update implements Command {
    private final String tableName;
    private final DataSerializer dataSerializer;
    private final Conditions conditions;
    private final Updates updates;

    public Update(String tableName, DataSerializer dataSerializer, Conditions conditions, Updates updates) {
        this.tableName = tableName;
        this.dataSerializer = dataSerializer;
        this.conditions = conditions;
        this.updates = updates;
    }

    @Override
    public Response run() {
        Data data = dataSerializer.deserialize(tableName);
        Data updated = data.filter(conditions)
                .updateRows(updates);
        dataSerializer.serialize(tableName, updated);
        return Response.ok("UPDATED");
    }
}

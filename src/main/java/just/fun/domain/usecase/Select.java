package just.fun.domain.usecase;

import just.fun.domain.response.ResponseWithData;
import just.fun.domain.schema.Columns;
import just.fun.domain.schema.Conditions;
import just.fun.domain.schema.Data;
import just.fun.serialization.DataSerializer;

public class Select implements Command {
    private final String tableName;
    private final DataSerializer dataSerializer;
    private final Columns queriedColumns;
    private final Conditions conditions;

    public Select(String tableName, DataSerializer dataSerializer, Columns queriedColumns, Conditions conditions) {
        this.tableName = tableName;
        this.dataSerializer = dataSerializer;
        this.queriedColumns = queriedColumns;
        this.conditions = conditions;
    }

    public Select(String tableName, DataSerializer dataSerializer, Columns queriedColumns) {
        this.tableName = tableName;
        this.dataSerializer = dataSerializer;
        this.queriedColumns = queriedColumns;
        this.conditions = new Conditions();
    }

    @Override
    public ResponseWithData run() {
        Data data = dataSerializer.deserialize(tableName)
                .filter(conditions)
                .onlyColumns(queriedColumns);
        return ResponseWithData.fetched(data);
    }

}

package just.fun.domain.usecase;

import just.fun.domain.response.ResponseWithData;
import just.fun.domain.schema.Columns;
import just.fun.domain.schema.Data;
import just.fun.domain.schema.ordering.Ordering;
import just.fun.domain.schema.condition.Where;
import just.fun.serialization.DataSerializer;

public class Select implements Command {
    private final String tableName;
    private final DataSerializer dataSerializer;
    private final Columns queriedColumns;
    private final Where where;
    private final Ordering ordering;

    public Select(String tableName,
                  DataSerializer dataSerializer,
                  Columns queriedColumns,
                  Where where,
                  Ordering ordering) {
        this.tableName = tableName;
        this.dataSerializer = dataSerializer;
        this.queriedColumns = queriedColumns;
        this.where = where;
        this.ordering = ordering;
    }

    @Override
    public ResponseWithData run() {
        Data data = dataSerializer.deserialize(tableName)
                .filter(where)
                .orderBy(ordering)
                .onlyColumns(queriedColumns);
        return ResponseWithData.fetched(data);
    }
}
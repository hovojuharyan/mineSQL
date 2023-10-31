package just.fun.domain.usecase;

import just.fun.domain.error.IllegalLimitOffsetException;
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
    private final int limit;
    private final int offset;

    public Select(String tableName,
                  DataSerializer dataSerializer,
                  Columns queriedColumns,
                  Where where,
                  Ordering ordering,
                  int limit,
                  int offset) {
        this.tableName = tableName;
        this.dataSerializer = dataSerializer;
        this.queriedColumns = queriedColumns;
        this.where = where;
        this.ordering = ordering;
        this.limit = limit;
        this.offset = offset;
    }

    public Select(String tableName,
                  DataSerializer dataSerializer,
                  Columns queriedColumns,
                  Where where,
                  Ordering ordering) {
        this(tableName, dataSerializer, queriedColumns, where, ordering, 0, 0);
    }

    @Override
    public ResponseWithData run() {
        try {
            Data data = dataSerializer.deserialize(tableName)
                    .filter(where)
                    .orderBy(ordering)
                    .limit(limit, offset)
                    .onlyColumns(queriedColumns);
            return ResponseWithData.fetched(data);
        } catch (IllegalLimitOffsetException e) {
            return ResponseWithData.error(e);
        }
    }
}
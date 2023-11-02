package just.fun.domain.usecase;

import just.fun.domain.error.IllegalLimitOffsetException;
import just.fun.domain.response.ResponseWithData;
import just.fun.domain.schema.Columns;
import just.fun.domain.schema.Data;
import just.fun.domain.schema.condition.Where;
import just.fun.domain.schema.ordering.Ordering;
import just.fun.serialization.DataSerializer;

public class Select implements Command {
    private final Data from;
    private final Columns queriedColumns;
    private final Where where;
    private final Ordering ordering;
    private final int limit;
    private final int offset;
    private final Columns grouping;

    public Select(Data from,
                  Columns queriedColumns,
                  Where where,
                  Columns grouping,
                  Ordering ordering,
                  int limit,
                  int offset) {
        this.from = from;
        this.queriedColumns = queriedColumns;
        this.where = where;
        this.grouping = grouping;
        this.ordering = ordering;
        this.limit = limit;
        this.offset = offset;
    }

    public Select(String tableName,
                  DataSerializer dataSerializer,
                  Columns queriedColumns,
                  Where where,
                  Columns grouping,
                  Ordering ordering,
                  int limit,
                  int offset) {
        this(dataSerializer.deserialize(tableName), queriedColumns, where, grouping, ordering, limit, offset);
    }

    @Override
    public ResponseWithData run() {
        try {
            Data data = from
                    .filter(where)
                    .groupBy(grouping)
                    .orderBy(ordering)
                    .limit(limit, offset)
                    .onlyColumns(queriedColumns);
            return ResponseWithData.fetched(data);
        } catch (IllegalLimitOffsetException e) {
            return ResponseWithData.error(e);
        }
    }
}
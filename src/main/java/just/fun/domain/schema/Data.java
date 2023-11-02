package just.fun.domain.schema;

import just.fun.domain.error.IllegalLimitOffsetException;
import just.fun.domain.schema.condition.Where;
import just.fun.domain.schema.group.GroupRow;
import just.fun.domain.schema.ordering.Ordering;
import just.fun.serialization.SerialContent;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Data implements SerialContent {

    private final List<Row> rows;

    public Data(List<Row> rows) {
        this.rows = new ArrayList<>(rows);
    }

    public List<Row> getRows() {
        return rows;
    }

    public Data onlyColumns(Columns columnNames) {
        List<Row> rowList = rows.stream()
                .map(row -> Row.fullRow(columnNames, row.cells()))
                .toList();
        return new Data(rowList);
    }

    public Data filter(Where where) {
        List<Row> filtered = rows.stream()
                .filter(where::testForRow)
                .collect(Collectors.toList());
        return new Data(filtered);
    }

    public Data groupBy(Columns grouping) {
        if (grouping.all().isEmpty()) return this;
        Set<GroupRow> groupRows = new LinkedHashSet<>();
        for (Row row : rows) {
            groupRows.add(GroupRow.of(row, grouping));
        }
        List<Row> grouped = groupRows.stream()
                .map(GroupRow::fullRow)
                .toList();
        return new Data(grouped);
    }

    public Data orderBy(Ordering ordering) {
        List<Row> sorted = rows.stream()
                .sorted(ordering.comparator())
                .toList();
        return new Data(sorted);
    }

    public Data limit(int limit, int offset) {
        if (limit == 0 && offset == 0) return this;
        if (limit < 0 || offset < 0) throw new IllegalLimitOffsetException(limit, offset);
        List<Row> limited = rows.stream()
                .skip(offset)
                .limit(limit)
                .toList();
        return new Data(limited);
    }

    @Override
    public String asString() {
        return rows.stream().map(Row::toString)
                .collect(Collectors.joining("\n"));
    }
}
package just.fun.domain.schema;

import just.fun.domain.schema.condition.Where;
import just.fun.domain.schema.ordering.Ordering;
import just.fun.serialization.SerialContent;

import java.util.ArrayList;
import java.util.List;
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

    public Data orderBy(Ordering ordering) {
        List<Row> sorted = rows.stream()
                .sorted(ordering.comparator())
                .toList();
        return new Data(sorted);
    }

    @Override
    public String asString() {
        return rows.stream().map(Row::toString)
                .collect(Collectors.joining("\n"));
    }
}
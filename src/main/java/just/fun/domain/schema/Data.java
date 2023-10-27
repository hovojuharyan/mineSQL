package just.fun.domain.schema;

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

    public Data filter(Conditions conditions) {
        Data data = new Data(rows);
        for (var condition : conditions.all()) {
            data = data.filter(condition);
        }
        return data;
    }

    private <RT> Data filter(Condition<RT> condition) {
        Column<RT> column = condition.getColumn();
        List<Row> rowList = rows.stream()
                .filter(row -> condition.test(row.columnValue(column)))
                .toList();
        return new Data(rowList);
    }

    @Override
    public String asString() {
        return rows.stream().map(Row::toString)
                .collect(Collectors.joining("\n"));
    }
}
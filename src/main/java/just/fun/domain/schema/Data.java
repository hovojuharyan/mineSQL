package just.fun.domain.schema;

import just.fun.serialization.SerialContent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Data implements SerialContent<Data> {

    private final List<Row> rows;

    public Data(List<Row> rows) {
        this.rows = new ArrayList<>(rows);
    }

    public Data onlyColumns(List<Column> columnNames) {
        List<Row> rowList = rows.stream()
                .map(row -> Row.fullRow(columnNames, row.cells()))
                .toList();
        return new Data(rowList);
    }

    public Data filter(Map<Column, Predicate<String>> conditions) {
        Data data = new Data(rows);
        for (Map.Entry<Column, Predicate<String>> entry : conditions.entrySet()) {
            data = data.filter(entry.getKey(), entry.getValue());
        }
        return data;
    }

    public Data updateRows(Map<Column, String> updates) {
        Data data = new Data(rows);
        for (Map.Entry<Column, String> entry : updates.entrySet()) {
            data = data.updateRows(entry.getKey(), entry.getValue());
        }
        return data;
    }

    private Data updateRows(Column column, String newValue) {
        Data updated = new Data(rows);
        updated.rows.forEach(row -> row.updateCell(column, newValue));
        return updated;
    }

    private Data filter(Column column, Predicate<String> predicate) {
        List<Row> rowList = rows.stream()
                .filter(row -> row.tryCondition(column, predicate))
                .toList();
        return new Data(rowList);
    }

    @Override
    public String serialForm() {
        return rows.stream().map(Row::serialForm)
                .collect(Collectors.joining("\n"));
    }

}

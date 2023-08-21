package just.fun.domain.schema;

import just.fun.serialization.SerialContent;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

public class Metadata implements SerialContent {

    private final String tableName;
    private final Columns columns;
    private final LocalDateTime createdAt;

    public Metadata(String tableName, Columns columns) {
        this.tableName = tableName;
        this.columns = columns;
        this.createdAt = LocalDateTime.now();
    }

    public Metadata(String tableName, Columns columns, LocalDateTime createdAt) {
        this.tableName = tableName;
        this.columns = columns;
        this.createdAt = createdAt;
    }

    public <RT> void addColumn(Column<RT> column) {
        columns.add(column);
    }

    public String tableName() {
        return tableName;
    }

    public Columns columns() {
        return columns;
    }

    @Override
    public String toString() {
        return tableName + "\n" +
                columns.all().stream()
                        .map(column -> column.name() + "-" + column.type())
                        .collect(Collectors.joining("|")) + "\n" +
                createdAt;
    }

}

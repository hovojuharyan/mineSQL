package just.fun.domain.schema;

import just.fun.serialization.SerialContent;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Metadata implements SerialContent<Metadata> {

    String tableName;
    List<Column> columns;
    LocalDateTime createdAt;

    public Metadata(String tableName) {
        this.tableName = tableName;
        this.columns = new ArrayList<>();
        this.createdAt = LocalDateTime.now();
    }

    public void addColumn(Column column) {
        columns.add(column);
    }

    public String getTableName() {
        return tableName;
    }

    @Override
    public String serialForm() {
        return tableName + "\n" +
                columns.stream().map(Column::getName).collect(Collectors.joining("|")) + "\n" +
                createdAt;
    }

}

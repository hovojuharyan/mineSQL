package just.fun;

import java.util.ArrayList;
import java.util.List;

public class CommandDto {
    private final String operation;
    private final String tableName;
    private final List<Column> columns;
    private final List<String> values;

    public CommandDto(String operation, String tableName) {
        this.operation = operation;
        this.tableName = tableName;
        this.columns = new ArrayList<>();
        this.values = new ArrayList<>();
    }

    public String getOperation() {
        return operation;
    }

    public String getTableName() {
        return tableName;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public List<String> getValues() {
        return values;
    }

    public List<String> getColumnNames() {
        return columns.stream()
                .map(Column::name)
                .toList();

    }

    public static Builder builder(String operation, String tableName) {
        return new Builder(operation, tableName);
    }

    public static class Builder {
        private final CommandDto commandDto;

        Builder(String operation, String tableName) {
            this.commandDto = new CommandDto(operation, tableName);
        }

        public Builder aColumn(Column column) {
            commandDto.columns.add(column);
            return this;
        }

        public Builder aValue(String value) {
            commandDto.values.add(value);
            return this;
        }

        public CommandDto build() {
            return commandDto;
        }
    }
}

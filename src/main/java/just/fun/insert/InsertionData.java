package just.fun.insert;

import just.fun.Column;
import just.fun.ColumnType;
import just.fun.ColumnsDefinition;
import just.fun.serial.SerialContent;

import java.util.*;

public class InsertionData implements SerialContent {
    private final List<Column> columns;
    private final ColumnsDefinition columnsDefinition;
    private final List<String> values;

    public InsertionData(ColumnsDefinition columnsDefinition,
                         List<String> providedColumns, List<String> values) {
        this.columnsDefinition = columnsDefinition;
        this.values = values;
        columns = new ArrayList<>(providedColumns.size());
        Map<String, ColumnType> columnTypeMap = columnsDefinition.asMap();
        for (String providedColumn : providedColumns) {
            columns.add(new Column(providedColumn, columnTypeMap.get(providedColumn)));
        }
    }

    @Override
    public String getTextualContent() {
        List<String> definedColumnNames = columnsDefinition.names();
        Map<String, String> providedColumnValueMap = new HashMap<>();
        for (int i = 0; i < columns.size(); i++) {
            providedColumnValueMap.put(columns.get(i).name(), values.get(i));
        }
        Map<String, String> completeColumnValueMap = new LinkedHashMap<>();
        for (String definedColumnName : definedColumnNames) {
            completeColumnValueMap.put(definedColumnName, providedColumnValueMap.getOrDefault(definedColumnName, ""));
        }
        return String.join("|", completeColumnValueMap.values());
    }

    public List<Column> getColumns() {
        return columns;
    }

    public List<String> getValues() {
        return values;
    }
}

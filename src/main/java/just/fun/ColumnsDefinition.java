package just.fun;

import just.fun.serial.SerialContent;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ColumnsDefinition implements SerialContent {

    private final List<Column> columns;

    public ColumnsDefinition(List<Column> columns) {
        this.columns = columns;
    }

    public static ColumnsDefinition fromString(String columnsDefinitionString) {
        String[] columnsString = columnsDefinitionString.split("\\|");
        List<Column> columns = new ArrayList<>();
        for (String colStr : columnsString) {
            String[] colParts = colStr.split("-");
            columns.add(new Column(colParts[0], ColumnType.valueOf(colParts[1])));
        }
        return new ColumnsDefinition(columns);
    }

    @Override
    public String getTextualContent() {
        return columns.stream()
                .map(Column::toString)
                .collect(Collectors.joining("|"));
    }

    public List<String> names() {
        return columns.stream()
                .map(Column::name)
                .sorted().toList();
    }
}

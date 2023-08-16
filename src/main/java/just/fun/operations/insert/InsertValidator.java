package just.fun.operations.insert;

import just.fun.column.Column;
import just.fun.column.ColumnType;
import just.fun.column.ColumnsDefinition;

import java.util.List;

public class InsertValidator {
    public void validate(InsertionData insertionData, ColumnsDefinition columnsDefinition) {
        validateSizeCorrespondence(insertionData);
        validateColumnsPresent(columnsDefinition, insertionData.getColumns());
        validateTypeCorrespondence(insertionData);
    }

    private void validateSizeCorrespondence(InsertionData insertionData) {
        int colsSize = insertionData.getColumns().size();
        int valsSize = insertionData.getValues().size();
        if (colsSize != valsSize) {
            throw new IllegalArgumentException("Provided column count (" + colsSize + ") is not " +
                    "matching with provided values count (" + valsSize + ")");
        }
    }

    private void validateColumnsPresent(ColumnsDefinition columnsDefinition, List<Column> columns) {
        List<Column> definedColumnNames = columnsDefinition.getColumns();
        for (Column column : columns) {
            if (!definedColumnNames.contains(column)) {
                throw new IllegalArgumentException("Columns " + column.name() + " is not defined");
            }
        }
    }

    private void validateTypeCorrespondence(InsertionData insertionData) {
        List<Column> providedColumns = insertionData.getColumns();
        List<String> values = insertionData.getValues();
        for (int i = 0; i < providedColumns.size(); i++) {
            ColumnType type = providedColumns.get(i).type();
            String value = values.get(i);
            if (!type.isValidForValue(value)) {
                throw new IllegalArgumentException("Insertion column type and value type don't correspond, for type " +
                        type + " and value " + value);
            }
        }
    }
}

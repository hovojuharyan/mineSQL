package just.fun.operations.insert;

import just.fun.column.ColumnsDefinition;
import just.fun.Operation;
import just.fun.Result;
import just.fun.serial.ColumnsDefinitionDeserializer;
import just.fun.serial.Serializer;

import java.util.List;

public class InsertOperation implements Operation {

    private final String tableName;
    private final Serializer serializer;
    private final ColumnsDefinitionDeserializer deserializer;
    private final List<String> columns;
    private final List<String> values;
    private final InsertValidator insertValidator;

    public InsertOperation(String tableName, Serializer serializer, ColumnsDefinitionDeserializer deserializer,
                           List<String> columns, List<String> values, InsertValidator insertValidator) {
        this.tableName = tableName;
        this.serializer = serializer;
        this.deserializer = deserializer;
        this.columns = columns;
        this.values = values;
        this.insertValidator = insertValidator;
    }

    @Override
    public Result operate() {
        try {
            ColumnsDefinition columnsDefinition = deserializer.deserialize();
            InsertionData insertionData = new InsertionData(columnsDefinition, columns, values);
            insertValidator.validate(insertionData, columnsDefinition);
            serializer.serialize(insertionData);
            return Result.ok("INSERTED INTO TABLE " + tableName);
        } catch (Exception e) {
            return Result.error("FAILED TO INSERT INTO TABLE " + tableName, e);
        }
    }

    @Override
    public String getTableName() {
        return tableName;
    }
}

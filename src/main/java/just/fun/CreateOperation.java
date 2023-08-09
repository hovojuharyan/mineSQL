package just.fun;

import just.fun.serial.Serializer;

public class CreateOperation implements Operation{

    private final Serializer serializer;
    private final String tableName;
    private final ColumnsDefinition columnsDefinition;

    public CreateOperation(Serializer serializer, String tableName, ColumnsDefinition columnsDefinition) {
        this.serializer = serializer;
        this.tableName = tableName;
        this.columnsDefinition = columnsDefinition;
    }

    @Override
    public Result operate() {
        try {
            serializer.serialize(columnsDefinition);
            return Result.ok("CREATED TABLE " + tableName);
        } catch (Exception ignored) {
            return Result.error("FAILED TO CREATE TABLE " + tableName);
        }
    }

    @Override
    public String getTableName() {
        return tableName;
    }
}

package just.fun.operations;

import just.fun.CommandDto;
import just.fun.Operation;
import just.fun.column.ColumnsDefinition;
import just.fun.operations.insert.InsertOperation;
import just.fun.operations.insert.InsertValidator;
import just.fun.serial.ColumnsDefinitionFileDeserializer;
import just.fun.serial.CreateFileSerializer;
import just.fun.serial.InsertFileSerializer;
import just.fun.serial.SimpleFileDropper;

public class OperationResolver {
    private static final String DROP_TABLE_OPNAME = "DROP_TABLE";
    private static final String CREATE_TABLE_OPNAME = "CREATE_TABLE";
    private static final String INSERT_OPNAME = "INSERT_INTO";

    public static Operation resolve(CommandDto commandDto) {
        String operation = commandDto.getOperation();
        String tableName = commandDto.getTableName();
        ColumnsDefinition columnsDefinition = new ColumnsDefinition(commandDto.getColumns());
        return switch (operation) {
            case DROP_TABLE_OPNAME -> new DropOperation(
                    new SimpleFileDropper(tableName),
                    tableName);
            case CREATE_TABLE_OPNAME -> new CreateOperation(
                    new CreateFileSerializer(tableName),
                    tableName,
                    columnsDefinition);
            case INSERT_OPNAME -> new InsertOperation(
                    tableName,
                    new InsertFileSerializer(tableName),
                    new ColumnsDefinitionFileDeserializer(tableName),
                    commandDto.getColumnNames(),
                    commandDto.getValues(),
                    new InsertValidator());
            default -> throw new UnsupportedOperationException(operation);
        };
    }
}

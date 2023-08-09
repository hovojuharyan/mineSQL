package just.fun;

import just.fun.serial.SimpleFileDropper;
import just.fun.serial.CreateFileSerializer;

public class OperationResolver {
    private static final String DROP_TABLE_OPNAME = "DROP_TABLE";
    private static final String CREATE_TABLE_OPNAME = "CREATE_TABLE";

    public static Operation resolve(CommandDto commandDto) {
        String operation = commandDto.getOperation();
        String tableName = commandDto.getTableName();
        return switch (operation) {
            case DROP_TABLE_OPNAME -> new DropOperation(new SimpleFileDropper(tableName), tableName);
            case CREATE_TABLE_OPNAME -> {
                ColumnsDefinition columnsDefinition = new ColumnsDefinition(commandDto.getColumns());
                yield new CreateOperation(new CreateFileSerializer(tableName), tableName, columnsDefinition);
            }
            default -> throw new UnsupportedOperationException(operation);
        };
    }
}

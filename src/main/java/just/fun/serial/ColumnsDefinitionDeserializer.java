package just.fun.serial;

import just.fun.column.ColumnsDefinition;

public interface ColumnsDefinitionDeserializer extends Deserializer {
    @Override
    ColumnsDefinition deserialize();
}

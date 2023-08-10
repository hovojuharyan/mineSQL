package just.fun.serial;

import just.fun.ColumnsDefinition;

public interface ColumnsDefinitionDeserializer extends Deserializer {
    @Override
    ColumnsDefinition deserialize();
}

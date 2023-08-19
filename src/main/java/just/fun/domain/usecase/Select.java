package just.fun.domain.usecase;

import just.fun.domain.schema.Column;
import just.fun.domain.schema.Data;
import just.fun.serialization.Deserializer;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class Select {
    Deserializer<Data> dataDeserializer;
    List<Column> queriedColumns;
    Map<Column, Predicate<String>> conditions;

    public Data run() {
        Data data = dataDeserializer.deserialize();
        return data.filter(conditions)
                .onlyColumns(queriedColumns);
    }

}

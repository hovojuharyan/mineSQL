package just.fun.domain.usecase;

import just.fun.domain.schema.Column;
import just.fun.domain.schema.Data;
import just.fun.domain.schema.Metadata;
import just.fun.serialization.DataDeserializer;
import just.fun.serialization.Deserializer;
import just.fun.serialization.MetadataDeserializer;
import just.fun.serialization.SerialContent;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class Select {
    MetadataDeserializer metadataDeserializer;
    DataDeserializer dataDeserializer;
    List<Column> queriedColumns;
    Map<Column, Predicate<String>> conditions;

    public Data run() {
        Data data = dataDeserializer.deserialize();
        // todo filter by conditions
        return data;
    }

}

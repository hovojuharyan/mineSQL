package just.fun.domain.usecase;

import just.fun.domain.schema.Column;
import just.fun.domain.schema.Data;
import just.fun.domain.schema.Metadata;
import just.fun.serialization.DataDeserializer;
import just.fun.serialization.MetadataDeserializer;
import just.fun.serialization.Serializer;

import java.util.Map;
import java.util.function.Predicate;

public class Update {
    Serializer<Data> dataSerializer;
    MetadataDeserializer metadataDeserializer;
    DataDeserializer dataDeserializer;
    Map<Column, Predicate<String>> conditions;
    Map<Column, String> newValues;
    UpdateQueryValidator updateQueryValidator;

    public void run() {
        updateQueryValidator.validate();
        Data data = dataDeserializer.deserialize();
        //todo modify on conditions
        dataSerializer.serialize(data);
    }

    private static class UpdateQueryValidator {
        public UpdateQueryValidator(Map<Column, String> queried, Metadata metadata, Map<Column, Predicate<String>> conditions) {

        }

        public void validate() {

        }
    }

}

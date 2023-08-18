package just.fun.domain.usecase;

import just.fun.domain.schema.Metadata;
import just.fun.domain.schema.Row;
import just.fun.serialization.MetadataDeserializer;
import just.fun.serialization.Serializer;

public class Insert {
    Row row;
    MetadataDeserializer metadataDeserializer;
    Serializer<Row> serializer;
    InsertDataValidator insertDataValidator;

    public void run() {
        insertDataValidator.validate();
        serializer.serialize(row);
    }

    private static class InsertDataValidator {
        public InsertDataValidator(Row row, Metadata metadata) {

        }

        public void validate() {

        }
    }
}

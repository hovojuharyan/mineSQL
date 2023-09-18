package just.fun.domain.mocks;

import just.fun.domain.schema.Metadata;
import just.fun.serialization.MetadataSerializer;

import static just.fun.domain.mocks.MockDatabase.MOCK_DATABASE;

public class MetadataSerializerMock implements MetadataSerializer {

    @Override
    public Metadata deserialize(String tableName) {
        return MOCK_DATABASE.getMetadata(tableName);
    }

    @Override
    public void serialize(String tableName, Metadata metadata) {
        MOCK_DATABASE.storeMetadata(tableName, metadata);
    }
}

package just.fun.domain.mocks;

import just.fun.domain.schema.Data;
import just.fun.serialization.DataSerializer;

import static just.fun.domain.mocks.MockDatabase.MOCK_DATABASE;

public class DataSerializerMock implements DataSerializer {
    @Override
    public Data deserialize(String tableName) {
        return MOCK_DATABASE.getData(tableName);
    }

    @Override
    public void serialize(String tableName, Data data) {
        MOCK_DATABASE.storeData(tableName, data);
    }
}

package just.fun.domain.mocks;

import just.fun.domain.schema.Data;
import just.fun.domain.schema.Row;
import just.fun.serialization.RowSerializer;

import java.util.List;

import static just.fun.domain.mocks.MockDatabase.MOCK_DATABASE;

public class RowSerializerMock implements RowSerializer {
    @Override
    public void serialize(String tableName, Row row) {
        if (MOCK_DATABASE.containsData(tableName)) {
            Data data = MOCK_DATABASE.getData(tableName);
            data.getRows().add(row);
            MOCK_DATABASE.storeData(tableName, data);
        } else {
            MOCK_DATABASE.storeData(tableName, new Data(List.of(row)));
        }
    }
}

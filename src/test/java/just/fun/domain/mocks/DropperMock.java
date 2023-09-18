package just.fun.domain.mocks;

import just.fun.serialization.Dropper;

import static just.fun.domain.mocks.MockDatabase.MOCK_DATABASE;

public class DropperMock implements Dropper {
    @Override
    public void drop(String tableName) {
        MOCK_DATABASE.deleteMetadata(tableName);
        MOCK_DATABASE.deleteData(tableName);
    }
}

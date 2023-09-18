package just.fun.domain.mocks;

import just.fun.domain.schema.Data;
import just.fun.domain.schema.Metadata;
import just.fun.domain.schema.Row;

import java.util.HashMap;
import java.util.Map;

public class MockDatabase {

    public static final MockDatabase MOCK_DATABASE = new MockDatabase();

    private final Map<String, Metadata> metadataStorage;
    private final Map<String, Data> dataStorage;

    private MockDatabase() {
        this.metadataStorage = new HashMap<>();
        this.dataStorage = new HashMap<>();
    }

    public void storeMetadata(String tableName, Metadata metadata) {
        metadataStorage.put(tableName, metadata);
    }

    public void storeData(String tableName, Data data) {
        dataStorage.put(tableName, data);
    }

    public Metadata getMetadata(String tableName) {
        return metadataStorage.get(tableName);
    }

    public Data getData(String tableName) {
        return dataStorage.get(tableName);
    }

    public void deleteMetadata(String tableName) {
        metadataStorage.remove(tableName);
    }

    public void deleteData(String tableName) {
        dataStorage.remove(tableName);
    }

    public boolean containsMetadata(String tableName) {
        return metadataStorage.containsKey(tableName);
    }

    public boolean containsData(String tableName) {
        return dataStorage.containsKey(tableName);
    }

    public boolean containsRow(String tableName, Row row) {
        Data data = dataStorage.get(tableName);
        return data != null && data.getRows().contains(row);
    }
}

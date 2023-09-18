package just.fun.domain.usecase;

import just.fun.domain.mocks.MetadataSerializerMock;
import just.fun.domain.schema.Metadata;
import just.fun.domain.testdata.PersonData;
import just.fun.serialization.MetadataSerializer;

public class CreateTableTest {
    private final CreateTable createTable;
    private final String tableName;
    private final Metadata metadata;
    private final MetadataSerializer metadataSerializer;

    public CreateTableTest(String tableName, Metadata metadata, MetadataSerializerMock metadataSerializer) {
        this.tableName = tableName;
        this.metadata = metadata;
        this.metadataSerializer = metadataSerializer;
        this.createTable = new CreateTable(tableName, metadata, metadataSerializer);
    }

    public void testCreateTable() {
        createTable.run();
        Metadata storedMetadata = metadataSerializer.deserialize(tableName);
        assert storedMetadata.tableName().equals(tableName)
                : "invalid table name: expected " + tableName + "\n actual " + storedMetadata.tableName();
        assert storedMetadata.columns().equals(metadata.columns())
                : "invalid columns: expected " + metadata.columns() + "\n actual " + storedMetadata.columns();
    }

    public static void main(String[] args) {
        Metadata personMetadata = PersonData.personMetadata();
        CreateTableTest createTableTest =
                new CreateTableTest(personMetadata.tableName(), personMetadata, new MetadataSerializerMock());
        createTableTest.testCreateTable();
    }
}

package just.fun.domain.usecase;

import just.fun.domain.mocks.MetadataSerializerMock;
import just.fun.domain.mocks.RowSerializerMock;
import just.fun.domain.schema.Columns;
import just.fun.domain.schema.Metadata;
import just.fun.domain.schema.Row;
import just.fun.domain.testdata.PersonData;
import just.fun.serialization.RowSerializer;

import java.util.List;
import java.util.Map;

import static just.fun.domain.mocks.MockDatabase.MOCK_DATABASE;

public class InsertTest {

    private final Insert insert;
    private final String tableName;
    private final Row row;
    private final RowSerializer rowSerializer;

    public InsertTest(String tableName, Row row, RowSerializer rowSerializer) {
        this.tableName = tableName;
        this.row = row;
        this.rowSerializer = rowSerializer;
        this.insert = new Insert(tableName, row, rowSerializer);
    }

    private void init() {
        new CreateTable("person", PersonData.personMetadata(), new MetadataSerializerMock()).run();
    }

    public void testInsertIntoExisting() {
        init();
        assert MOCK_DATABASE.containsMetadata(tableName);
        assert !MOCK_DATABASE.containsData(tableName);
        insert.run();
        assert MOCK_DATABASE.containsData(tableName)
                : "Row wasn't properly inserted - " + row;
        assert MOCK_DATABASE.containsRow(tableName, row)
                : "Row wasn't properly inserted - " + row;
    }

    public static void main(String[] args) {
        String tableName = "person";
        Metadata personMetadata = PersonData.personMetadata();
        Columns columns = personMetadata.columns();
        Row row = Row.initRow(columns, List.of("poxos", "poxosyan", "25", "FALSE"));
        new InsertTest(tableName, row, new RowSerializerMock()).testInsertIntoExisting();
    }
}

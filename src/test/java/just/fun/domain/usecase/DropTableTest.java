package just.fun.domain.usecase;

import just.fun.domain.mocks.DropperMock;
import just.fun.domain.mocks.MetadataSerializerMock;
import just.fun.domain.testdata.PersonData;
import just.fun.serialization.Dropper;

import static just.fun.domain.mocks.MockDatabase.MOCK_DATABASE;

public class DropTableTest {

    private final DropTable dropTable;
    private final String tableName;

    public DropTableTest(String tableName, Dropper dropper) {
        this.tableName = tableName;
        dropTable = new DropTable(tableName, dropper);
    }

    public void init() {
        new CreateTable(tableName, PersonData.personMetadata(), new MetadataSerializerMock()).run();
    }

    public void testDropExisting() {
        init();
        assert MOCK_DATABASE.containsMetadata(tableName);
        assert MOCK_DATABASE.containsData(tableName);
        dropTable.run();
        assert !MOCK_DATABASE.containsMetadata(tableName)
                : "Table metadata wasn't properly dropped - " + tableName;
        assert !MOCK_DATABASE.containsData(tableName)
                : "Table metadata wasn't properly dropped - " + tableName;
    }

    public static void main(String[] args) {
        String tableName = "person";
        DropTableTest dropTableTest = new DropTableTest(tableName, new DropperMock());
        dropTableTest.testDropExisting();
    }
}

package just.fun;

import just.fun.domain.schema.*;
import just.fun.domain.usecase.*;
import just.fun.serialization.DataSerializer;
import just.fun.serialization.Dropper;
import just.fun.serialization.MetadataSerializer;
import just.fun.serialization.RowSerializer;

public class TestUtils {

    private final MetadataSerializer metadataSerializer;
    private final Dropper dropper;
    private final RowSerializer rowSerializer;
    private final DataSerializer dataSerializer;

    public TestUtils(MetadataSerializer metadataSerializer, Dropper dropper, RowSerializer rowSerializer, DataSerializer dataSerializer) {
        this.metadataSerializer = metadataSerializer;
        this.dropper = dropper;
        this.rowSerializer = rowSerializer;
        this.dataSerializer = dataSerializer;
    }

    void create(String tableName, Metadata metadata) {
        CreateTable createTable = new CreateTable(tableName, metadata,  metadataSerializer);
        System.out.println(createTable.run());
    }

    void drop(String tableName) {
        DropTable dropTable = new DropTable(tableName, dropper);
        System.out.println(dropTable.run());
    }

    void insert(String tableName, Row row) {
        Insert insert = new Insert(tableName, row, rowSerializer);
        System.out.println(insert.run());
    }

    void update(String tableName, Conditions conditions, Updates updates) {
        Update update = new Update(tableName, dataSerializer, conditions, updates);
        System.out.println(update.run());
    }

    void select(String tableName, Columns columns, Conditions conditions) {
        Select select = new Select(tableName, dataSerializer, columns, conditions);
        System.out.println(select.run());
    }
}

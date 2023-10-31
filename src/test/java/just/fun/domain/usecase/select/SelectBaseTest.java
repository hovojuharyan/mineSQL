package just.fun.domain.usecase.select;

import just.fun.domain.schema.Data;
import just.fun.domain.schema.Metadata;
import just.fun.domain.schema.Row;
import just.fun.domain.testdata.PersonData;
import just.fun.domain.usecase.BaseTest;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;

import static just.fun.domain.testdata.PersonData.tableName;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

public class SelectBaseTest extends BaseTest {
    @BeforeEach
    public void setup() {
        initDummyPersonData();
        when(dataSerializer.deserialize(eq(tableName()))).thenReturn(DATA_MAP.get(tableName()));
    }

    protected void initDummyPersonData() {
        Metadata metadata = PersonData.metadata();
        String tableName = metadata.tableName();
        METADATA_MAP.putIfAbsent(tableName, metadata);
        DATA_MAP.putIfAbsent(tableName, dummyPersonData());
    }

    protected Data dummyPersonData() {
        return PersonData.someData(dummyPersonRows());
    }

    protected List<Row> dummyPersonRows() {
        return List.of(
                PersonData.aRow("a", "ayan", "Armenia", "20", "true"),
                PersonData.aRow("b", "byev", "Poland", "21", "false"),
                PersonData.aRow("c", "cyan", "Armenia", "22", "true"),
                PersonData.aRow("d", "dovski", "USA", "23", "true"),
                PersonData.aRow("e", "eyan", "Armenia", "20", "true"),
                PersonData.aRow("f", "fyev", "Poland", "21", "true"),
                PersonData.aRow("g", "gyan", "Armenia", "22", "false"),
                PersonData.aRow("h", "hyan", "Armenia", "23", "false"),
                PersonData.aRow("i", "iyan", "Armenia", "20", "false"),
                PersonData.aRow("j", "jovski", "USA", "21", "false"),
                PersonData.aRow("k", "kyan", "Armenia", "22", "true"),
                PersonData.aRow("l", "lovski", "USA", "23", "false"),
                PersonData.aRow("m", "myan", "Armenia", "24", "true"),
                PersonData.aRow("n", "nyan", "Armenia", "20", "false"),
                PersonData.aRow("o", "oyan", "Armenia", "21", "true"),
                PersonData.aRow("p", "povski", "USA", "21", "false"),
                PersonData.aRow("q", "qunknown", null, "66", "false")
        );
    }
}

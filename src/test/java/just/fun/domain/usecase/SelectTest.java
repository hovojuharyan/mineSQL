package just.fun.domain.usecase;

import just.fun.domain.response.ResponseWithData;
import just.fun.domain.response.Status;
import just.fun.domain.schema.*;
import just.fun.domain.testdata.PersonData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static just.fun.domain.testdata.PersonData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

public class SelectTest extends BaseTest {

    @BeforeEach
    public void setup() {
        initDummyPersonData();
        when(dataSerializer.deserialize(eq(tableName()))).thenReturn(DATA_MAP.get(tableName()));
    }

    @Test
    public void selectAllColumns() {
        Select selectAllColumns = new Select(tableName(), dataSerializer, PersonData.columns());
        ResponseWithData response = selectAllColumns.run();
        Data fetched = response.fetchedData();

        assertEquals(response.getStatus(), Status.OK);
        assertEquals(fetched.getRows(), dummyPersonRows());
    }

    @Test
    public void selectNameSurnameColumns() {
        Columns nameSurnameColumns = new Columns();
        nameSurnameColumns.add(nameColumn());
        nameSurnameColumns.add(surnameColumn());

        Select selectNameSurname = new Select(tableName(), dataSerializer, nameSurnameColumns);
        ResponseWithData response = selectNameSurname.run();
        List<Row> fetchedRows = response.fetchedData().getRows();

        assertEquals(response.getStatus(), Status.OK);
        assertEquals(fetchedRows.size(), 16);
        assertEquals(fetchedRows.get(0).cells().size(), 2);
        assertEquals(fetchedRows.get(0).columnValue(nameColumn()), "a");
        assertEquals(fetchedRows.get(1).columnValue(surnameColumn()), "byev");
    }

    @Test
    public void selectIsMarried() {
        Condition<Boolean> isMarriedCondition = Condition.isEqual(isMarriedColumn(), true);
        Conditions conditions = new Conditions();
        conditions.add(isMarriedCondition);
        Select selectMarried = new Select(tableName(), dataSerializer, PersonData.columns(), conditions);
        ResponseWithData response = selectMarried.run();
        List<Row> fetchedRows = response.fetchedData().getRows();

        assertEquals(response.getStatus(), Status.OK);
        assertEquals(fetchedRows.size(), 8);
        assertEquals(fetchedRows.get(0).columnValue(nameColumn()), "a");
        assertEquals(fetchedRows.get(1).columnValue(nameColumn()), "c");
    }

    @Test
    public void selectArmenian() {
        Condition<String> armenianCondition = Condition.isEqual(nationalityColumn(), "Armenia");
        Conditions conditions = new Conditions();
        conditions.add(armenianCondition);
        Select selectArmenians = new Select(tableName(), dataSerializer, PersonData.columns(), conditions);
        ResponseWithData response = selectArmenians.run();
        List<Row> fetchedRows = response.fetchedData().getRows();

        assertEquals(response.getStatus(), Status.OK);
        assertEquals(fetchedRows.size(), 10);
        assertEquals(fetchedRows.get(0).columnValue(ageColumn()), 20);
        assertEquals(fetchedRows.get(1).columnValue(surnameColumn()), "cyan");
    }

    private void initDummyPersonData() {
        Metadata metadata = PersonData.metadata();
        String tableName = metadata.tableName();
        METADATA_MAP.putIfAbsent(tableName, metadata);
        DATA_MAP.putIfAbsent(tableName, dummyPersonData());
    }

    private Data dummyPersonData() {
        return PersonData.someData(dummyPersonRows());
    }

    private List<Row> dummyPersonRows() {
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
                PersonData.aRow("p", "povski", "USA", "21", "false")
        );
    }
}

package just.fun.domain.usecase;

import just.fun.domain.response.Response;
import just.fun.domain.response.Status;
import just.fun.domain.schema.Column;
import just.fun.domain.schema.Data;
import just.fun.domain.schema.Row;
import just.fun.domain.schema.types.Bool;
import just.fun.domain.schema.types.Numeric;
import just.fun.domain.testdata.PersonData;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doAnswer;

public class InsertTest extends BaseTest {

    @Test
    public void insert() {
        String tableName = PersonData.metadata().tableName();
        Row row = PersonData.aRow("poxos", "poxosyan", "Armenia", "21", "false");
        Data data = new Data(Collections.emptyList());
        DATA_MAP.put(tableName, data);

        doAnswer(invocation -> {
            insertRow(tableName, row);
            return invocation;
        }).when(rowSerializer).serialize(eq(tableName), eq(row));

        Insert insert = new Insert(tableName, row, rowSerializer);
        Response response = insert.run();

        assertEquals(response.getStatus(), Status.OK);
        assertTrue(DATA_MAP.containsKey(tableName));
        assertTrue(DATA_MAP.get(tableName).getRows().contains(row));
        Row inserted = DATA_MAP.get(tableName).getRows().get(0);
        int age = inserted.columnValue(Column.with("age", new Numeric()));
        assertEquals(age, 21);
        assertFalse(row.columnValue(Column.with("isMarried", new Bool())));
    }
}

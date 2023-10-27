package just.fun.domain.usecase;

import just.fun.domain.error.NotNullConstraintViolatedExcpetion;
import just.fun.domain.response.Response;
import just.fun.domain.response.Status;
import just.fun.domain.schema.Data;
import just.fun.domain.schema.Row;
import just.fun.domain.testdata.PersonData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;

public class InsertTest extends BaseTest {

    @BeforeEach
    public void setup() {
        Data data = new Data(Collections.emptyList());
        DATA_MAP.put(PersonData.tableName(), data);
    }

    @Test
    public void insert() {
        String tableName = PersonData.tableName();
        List<String> values = Arrays.asList("poxos", "poxosyan", "Armenia", "21", "false");
        doNothing().when(rowSerializer).serialize(eq(tableName), any(Row.class));

        Insert insert = new Insert(tableName, rowSerializer, PersonData.columns(), values);
        Response response = insert.run();

        assertEquals(response.getStatus(), Status.OK);
    }

    @Test
    public void insertNullIntoNotNull() {
        String tableName = PersonData.tableName();
        List<String> values = Arrays.asList(null, "poxosyan", "Armenia", "21", "false");

        Insert insert = new Insert(tableName, rowSerializer, PersonData.columns(), values);
        Response response = insert.run();

        assertEquals(response.getStatus(), Status.ERROR);
        assertEquals(response.getThrowable().getClass(), NotNullConstraintViolatedExcpetion.class);
    }
}

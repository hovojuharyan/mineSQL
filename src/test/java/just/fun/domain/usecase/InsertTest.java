package just.fun.domain.usecase;

import just.fun.domain.error.NotNullConstraintViolatedExcpetion;
import just.fun.domain.error.UniqueColumnConstraintViolationException;
import just.fun.domain.response.Response;
import just.fun.domain.response.Status;
import just.fun.domain.schema.Row;
import just.fun.domain.schema.unique.UniqueConstraintsSet;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static just.fun.domain.testdata.PersonData.columns;
import static just.fun.domain.testdata.PersonData.tableName;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class InsertTest extends TestWithDummyData {

    @Test
    public void insert() {
        List<String> values = Arrays.asList("100", "poxos", "poxosyan", "Armenia", "21", "false");
        doNothing().when(rowSerializer).serialize(eq(tableName()), any(Row.class));
        when(uniqueConstraintsSetSerializer.deserialize(eq(tableName())))
                .thenReturn(UniqueConstraintsSet.empty());

        Insert insert = new Insert(tableName(), rowSerializer, columns(), values, uniqueConstraintsSetSerializer);
        Response response = insert.run();

        assertEquals(response.getStatus(), Status.OK);
    }

    @Test
    public void insertNullIntoNotNull() {
        List<String> values = Arrays.asList("101", null, "poxosyan", "Armenia", "21", "false");

        Insert insert = new Insert(tableName(), rowSerializer, columns(), values, uniqueConstraintsSetSerializer);
        Response response = insert.run();

        assertEquals(response.getStatus(), Status.ERROR);
        assertEquals(response.getThrowable().getClass(), NotNullConstraintViolatedExcpetion.class);
    }

    @Test
    public void insertViolatingUniqueConstraintId() {
        List<String> values = Arrays.asList("1", "poxos", "poxosyan", "Armenia", "21", "false");
        Insert insert = new Insert(tableName(), rowSerializer, columns(), values, uniqueConstraintsSetSerializer);
        Response response = insert.run();

        assertEquals(response.getStatus(), Status.ERROR);
        assertEquals(response.getThrowable().getClass(), UniqueColumnConstraintViolationException.class);
    }

    @Test
    public void insertFirstNormalThenViolatingUniqueness() {
        List<String> values = Arrays.asList("100", "poxos", "poxosyan", "Armenia", "21", "false");
        Insert insert = new Insert(tableName(), rowSerializer, columns(), values, uniqueConstraintsSetSerializer);

        Response response = insert.run();

        assertEquals(response.getStatus(), Status.OK);

        values = Arrays.asList("100", "petros", "petrosyan", "Armenia", "21", "false");
        insert = new Insert(tableName(), rowSerializer, columns(), values, uniqueConstraintsSetSerializer);
        response = insert.run();

        assertEquals(response.getStatus(), Status.ERROR);
        assertEquals(response.getThrowable().getClass(), UniqueColumnConstraintViolationException.class);
    }

    @Test
    public void insertViolatingCompoundUniqueConstraint() {
        List<String> values = Arrays.asList("100", "a", "not ayan", "Somewhere", "44", "false");
        Insert insert = new Insert(tableName(), rowSerializer, columns(), values, uniqueConstraintsSetSerializer);
        Response response = insert.run();

        assertEquals(response.getStatus(), Status.OK);

        values = Arrays.asList("101", "not a", "ayan", "Somewhere", "44", "false");
        insert = new Insert(tableName(), rowSerializer, columns(), values, uniqueConstraintsSetSerializer);
        response = insert.run();

        assertEquals(response.getStatus(), Status.OK);

        values = Arrays.asList("102", "b", "ayan", "Somewhere", "44", "false");
        insert = new Insert(tableName(), rowSerializer, columns(), values, uniqueConstraintsSetSerializer);
        response = insert.run();

        assertEquals(response.getStatus(), Status.OK);

        values = Arrays.asList("103", "a", "ayan", "Somewhere", "44", "false");
        insert = new Insert(tableName(), rowSerializer, columns(), values, uniqueConstraintsSetSerializer);
        response = insert.run();

        assertEquals(response.getStatus(), Status.ERROR);
        assertEquals(response.getThrowable().getClass(), UniqueColumnConstraintViolationException.class);
    }
}

package just.fun.domain.usecase.select;

import just.fun.domain.error.IllegalLimitOffsetException;
import just.fun.domain.response.ResponseWithData;
import just.fun.domain.response.Status;
import just.fun.domain.schema.Row;
import just.fun.domain.schema.condition.Where;
import just.fun.domain.schema.ordering.Ordering;
import just.fun.domain.usecase.Select;
import just.fun.domain.usecase.TestWithDummyData;
import org.junit.jupiter.api.Test;

import java.util.List;

import static just.fun.domain.testdata.PersonData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SelectLimitedTest extends TestWithDummyData {
    @Test
    public void selectLimit10() {
        int limit = 10;
        Select select = new Select(tableName(), dataSerializer, columns(), Where.none(), Ordering.none(), limit, 0);
        ResponseWithData response = select.run();
        List<Row> rows = response.fetchedData().getRows();

        assertEquals(response.getStatus(), Status.OK);
        assertEquals(rows.size(), limit);
        assertEquals(rows.get(0).columnValue(nameColumn()), "a");
        assertEquals(rows.get(9).columnValue(nameColumn()), "j");
    }

    @Test
    public void selectLimit10Offset5() {
        int limit = 10;
        int offset = 5;
        Select select = new Select(tableName(), dataSerializer, columns(), Where.none(), Ordering.none(), limit, offset);
        ResponseWithData response = select.run();
        List<Row> rows = response.fetchedData().getRows();

        assertEquals(response.getStatus(), Status.OK);
        assertEquals(rows.size(), limit);
        assertEquals(rows.get(0).columnValue(nameColumn()), "f");
        assertEquals(rows.get(9).columnValue(nameColumn()), "o");
    }

    @Test
    public void selectLimitOffsetIllegal() {
        int limit = -10;
        int offset = -5;
        Select select = new Select(tableName(), dataSerializer, columns(), Where.none(), Ordering.none(), limit, offset);
        ResponseWithData response = select.run();

        assertEquals(response.getStatus(), Status.ERROR);
        assertEquals(response.getThrowable().getClass(), IllegalLimitOffsetException.class);
    }

    @Test
    public void selectLimitMoreThanSize() {
        int limit = 20;
        Select select = new Select(tableName(), dataSerializer, columns(), Where.none(), Ordering.none(), limit, 0);
        ResponseWithData response = select.run();
        List<Row> rows = response.fetchedData().getRows();

        assertEquals(response.getStatus(), Status.OK);
        assertEquals(rows.size(), 17);
        assertEquals(rows.get(0).columnValue(nameColumn()), "a");
        assertEquals(rows.get(16).columnValue(nameColumn()), "q");
    }

    @Test
    public void selectOffsetMoreThanSize() {
        int offset = 20;
        Select select = new Select(tableName(), dataSerializer, columns(), Where.none(), Ordering.none(), 0, offset);
        ResponseWithData response = select.run();
        List<Row> rows = response.fetchedData().getRows();

        assertEquals(response.getStatus(), Status.OK);
        assertEquals(rows.size(), 0);
    }
}

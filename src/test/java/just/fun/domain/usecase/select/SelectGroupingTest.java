package just.fun.domain.usecase.select;

import just.fun.domain.response.ResponseWithData;
import just.fun.domain.response.Status;
import just.fun.domain.schema.Columns;
import just.fun.domain.schema.Row;
import just.fun.domain.schema.condition.Where;
import just.fun.domain.schema.ordering.Ordering;
import just.fun.domain.usecase.Select;
import just.fun.domain.usecase.TestWithDummyData;
import org.junit.jupiter.api.Test;

import java.util.List;

import static just.fun.domain.testdata.PersonData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class SelectGroupingTest extends TestWithDummyData {

    @Test
    public void selectGroupedByNationality() {
        Columns columnsNationality = Columns.empty().add(nationalityColumn());
        Select select = new Select(tableName(), dataSerializer, columnsNationality, Where.none(), columnsNationality, Ordering.none(), 0, 0);
        ResponseWithData response = select.run();
        List<Row> rows = response.fetchedData().getRows();

        assertEquals(response.getStatus(), Status.OK);
        assertEquals(rows.size(), 4);
        assertEquals(rows.get(0).columnValue(nationalityColumn()), "Armenia");
        assertEquals(rows.get(1).columnValue(nationalityColumn()), "Poland");
        assertEquals(rows.get(2).columnValue(nationalityColumn()), "USA");
        assertNull(rows.get(3).columnValue(nationalityColumn()));
    }

    @Test
    public void selectGroupedByAge() {
        Columns groupingColumns = Columns.empty().add(ageColumn());
        Select select = new Select(tableName(), dataSerializer, columns(), Where.none(), groupingColumns, Ordering.none(), 0, 0);
        ResponseWithData response = select.run();
        List<Row> rows = response.fetchedData().getRows();

        assertEquals(response.getStatus(), Status.OK);
        assertEquals(rows.size(), 6);
        assertEquals(rows.get(0).columnValue(ageColumn()), 20);
        assertEquals(rows.get(1).columnValue(ageColumn()), 21);
        assertEquals(rows.get(5).columnValue(ageColumn()), 66);
    }
}

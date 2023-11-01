package just.fun.domain.usecase.select;

import just.fun.domain.response.ResponseWithData;
import just.fun.domain.response.Status;
import just.fun.domain.schema.Row;
import just.fun.domain.schema.condition.And;
import just.fun.domain.schema.condition.Conditions;
import just.fun.domain.schema.condition.Where;
import just.fun.domain.schema.ordering.Ordering;
import just.fun.domain.usecase.Select;
import just.fun.domain.usecase.TestWithDummyData;
import org.junit.jupiter.api.Test;

import java.util.List;

import static just.fun.domain.testdata.PersonData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SelectOrderByTest extends TestWithDummyData {
    @Test
    public void orderByAgeAsc() {
        Ordering ageAsc = Ordering.byAsc(ageColumn());
        Select select = new Select(tableName(), dataSerializer, columns(), Where.none(), ageAsc);
        ResponseWithData response = select.run();
        List<Row> rows = response.fetchedData().getRows();

        assertEquals(response.getStatus(), Status.OK);
        assertEquals(rows.size(), 17);
        assertEquals(rows.get(0).columnValue(ageColumn()), 20);
        assertEquals(rows.get(4).columnValue(ageColumn()), 21);
        assertEquals(rows.get(16).columnValue(ageColumn()), 66);
    }

    @Test
    public void orderByAgeDesc() {
        Ordering ageAsc = Ordering.byDesc(ageColumn());
        Select select = new Select(tableName(), dataSerializer, columns(), Where.none(), ageAsc);
        ResponseWithData response = select.run();
        List<Row> rows = response.fetchedData().getRows();

        assertEquals(response.getStatus(), Status.OK);
        assertEquals(rows.size(), 17);
        assertEquals(rows.get(0).columnValue(ageColumn()), 66);
        assertEquals(rows.get(1).columnValue(ageColumn()), 24);
        assertEquals(rows.get(12).columnValue(ageColumn()), 21);
        assertEquals(rows.get(16).columnValue(ageColumn()), 20);
    }

    @Test
    public void isMarriedOrderByNationalityDescAndAgeAsc() {
        Where where = Where.begin(And.of(isMarriedColumn(), Conditions.isEqual(true)));
        Ordering nationalityDescAgeAsc = Ordering.byDesc(nationalityColumn()).thenAsc(ageColumn());
        Select select = new Select(tableName(), dataSerializer, columns(), where, nationalityDescAgeAsc);
        ResponseWithData response = select.run();
        List<Row> rows = response.fetchedData().getRows();

        assertEquals(response.getStatus(), Status.OK);
        assertEquals(rows.size(), 8);
        assertEquals(rows.get(0).columnValue(nationalityColumn()), "USA");
        assertEquals(rows.get(1).columnValue(nationalityColumn()), "Poland");
        assertEquals(rows.get(2).columnValue(nationalityColumn()), "Armenia");
        assertEquals(rows.get(2).columnValue(ageColumn()), 20);
        assertEquals(rows.get(7).columnValue(ageColumn()), 24);
    }
}

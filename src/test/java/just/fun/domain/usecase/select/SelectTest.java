package just.fun.domain.usecase.select;

import just.fun.domain.response.ResponseWithData;
import just.fun.domain.response.Status;
import just.fun.domain.schema.Columns;
import just.fun.domain.schema.Data;
import just.fun.domain.schema.ordering.Ordering;
import just.fun.domain.schema.Row;
import just.fun.domain.schema.condition.And;
import just.fun.domain.schema.condition.Conditions;
import just.fun.domain.schema.condition.Or;
import just.fun.domain.schema.condition.Where;
import just.fun.domain.testdata.PersonData;
import just.fun.domain.usecase.Select;
import org.junit.jupiter.api.Test;

import java.util.List;

import static just.fun.domain.testdata.PersonData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SelectTest extends SelectBaseTest {

    @Test
    public void selectAllColumns() {
        Select selectAllColumns = new Select(tableName(), dataSerializer, PersonData.columns(), Where.none(), Ordering.none());
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

        Select selectNameSurname = new Select(tableName(), dataSerializer, nameSurnameColumns, Where.none(), Ordering.none());
        ResponseWithData response = selectNameSurname.run();
        List<Row> fetchedRows = response.fetchedData().getRows();

        assertEquals(response.getStatus(), Status.OK);
        assertEquals(fetchedRows.size(), 17);
        assertEquals(fetchedRows.get(0).cells().size(), 2);
        assertEquals(fetchedRows.get(0).columnValue(nameColumn()), "a");
        assertEquals(fetchedRows.get(1).columnValue(surnameColumn()), "byev");
    }

    @Test
    public void selectIsMarried() {
        And andIsMarried = And.of(isMarriedColumn(), Conditions.isEqual(true));
        Where where = Where.begin(andIsMarried);
        Select selectMarried = new Select(tableName(), dataSerializer, PersonData.columns(), where, Ordering.none());
        ResponseWithData response = selectMarried.run();
        List<Row> fetchedRows = response.fetchedData().getRows();

        assertEquals(response.getStatus(), Status.OK);
        assertEquals(fetchedRows.size(), 8);
        assertEquals(fetchedRows.get(0).columnValue(nameColumn()), "a");
        assertEquals(fetchedRows.get(1).columnValue(nameColumn()), "c");
    }

    @Test
    public void selectArmenian() {
        And andIsArmenian = And.of(nationalityColumn(), Conditions.isEqual("Armenia"));
        Where where = Where.begin(andIsArmenian);
        Select selectArmenians = new Select(tableName(), dataSerializer, PersonData.columns(), where, Ordering.none());
        ResponseWithData response = selectArmenians.run();
        List<Row> fetchedRows = response.fetchedData().getRows();

        assertEquals(response.getStatus(), Status.OK);
        assertEquals(fetchedRows.size(), 10);
        assertEquals(fetchedRows.get(0).columnValue(ageColumn()), 20);
        assertEquals(fetchedRows.get(1).columnValue(surnameColumn()), "cyan");
    }

    @Test
    public void selectPolishOr23YearsOld() {
        And andIsPolish = And.of(nationalityColumn(), Conditions.isEqual("Poland"));
        Or orIs23YearsOld = Or.of(ageColumn(), Conditions.isEqual(23));
        Where where = Where.begin(andIsPolish).add(orIs23YearsOld);
        Select select = new Select(tableName(), dataSerializer, PersonData.columns(), where, Ordering.none());
        ResponseWithData response = select.run();
        List<Row> fetchedRows = response.fetchedData().getRows();

        assertEquals(response.getStatus(), Status.OK);
        assertEquals(fetchedRows.size(), 5);
        assertEquals(fetchedRows.get(0).columnValue(nameColumn()), "b");
        assertEquals(fetchedRows.get(1).columnValue(nameColumn()), "d");
    }

    @Test
    public void selectAgeLessThan22() {
        And andAgeIsLessThan22 = And.of(ageColumn(), Conditions.isLessThan(22));
        Where where = Where.begin(andAgeIsLessThan22);
        Select select = new Select(tableName(), dataSerializer, PersonData.columns(), where, Ordering.none());
        ResponseWithData response = select.run();
        List<Row> rows = response.fetchedData().getRows();

        assertEquals(response.getStatus(), Status.OK);
        assertEquals(rows.size(), 9);
        assertEquals(rows.get(0).columnValue(nameColumn()), "a");
        assertEquals(rows.get(2).columnValue(nameColumn()), "e");
    }

    @Test
    public void selectAgeGreaterThanOrEqual22() {
        And andAgeIsGreaterThanOrEqual22 = And.of(ageColumn(), Conditions.isGreaterThanOrEqual(22));
        Where where = Where.begin(andAgeIsGreaterThanOrEqual22);
        Select select = new Select(tableName(), dataSerializer, PersonData.columns(), where, Ordering.none());
        ResponseWithData response = select.run();
        List<Row> rows = response.fetchedData().getRows();

        assertEquals(response.getStatus(), Status.OK);
        assertEquals(rows.size(), 8);
        assertEquals(rows.get(0).columnValue(nameColumn()), "c");
        assertEquals(rows.get(2).columnValue(nameColumn()), "g");
    }

    @Test
    public void selectNotArmenian() {
        And andIsNotArmenian = And.of(nationalityColumn(), Conditions.isNotEqual("Armenia"));
        Where where = Where.begin(andIsNotArmenian);
        Select select = new Select(tableName(), dataSerializer, PersonData.columns(), where, Ordering.none());
        ResponseWithData response = select.run();
        List<Row> rows = response.fetchedData().getRows();

        assertEquals(response.getStatus(), Status.OK);
        assertEquals(rows.size(), 7);
        assertEquals(rows.get(0).columnValue(nationalityColumn()), "Poland");
        assertEquals(rows.get(1).columnValue(nationalityColumn()), "USA");
    }

    @Test
    public void selectNationalityNotNull() {
        And andNationalityIsNotNull = And.of(nationalityColumn(), Conditions.isNotNull());
        Where where = Where.begin(andNationalityIsNotNull);
        Select select = new Select(tableName(), dataSerializer, PersonData.columns(), where, Ordering.none());
        ResponseWithData response = select.run();
        List<Row> rows = response.fetchedData().getRows();

        assertEquals(response.getStatus(), Status.OK);
        assertEquals(rows.size(), 16);
    }

    @Test
    public void selectAmericanOrUnknown() {
        And andIsAmerican = And.of(nationalityColumn(), Conditions.isEqual("USA"));
        Or orIsFromUnknown = Or.of(nationalityColumn(), Conditions.isNull());
        Where where = Where.begin(andIsAmerican).add(orIsFromUnknown);
        Select select = new Select(tableName(), dataSerializer, PersonData.columns(), where, Ordering.none());
        ResponseWithData response = select.run();
        List<Row> rows = response.fetchedData().getRows();

        assertEquals(response.getStatus(), Status.OK);
        assertEquals(rows.size(), 5);
        assertEquals(rows.get(0).columnValue(nameColumn()), "d");
        assertEquals(rows.get(4).columnValue(nameColumn()), "q");
    }
}

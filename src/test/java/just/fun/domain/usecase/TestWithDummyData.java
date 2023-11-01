package just.fun.domain.usecase;

import just.fun.domain.schema.Data;
import just.fun.domain.schema.Metadata;
import just.fun.domain.schema.Row;
import just.fun.domain.schema.unique.UniqueConstraint;
import just.fun.domain.schema.unique.UniqueConstraintsSet;
import just.fun.domain.schema.unique.UniqueValues;
import just.fun.domain.testdata.PersonData;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;

import static just.fun.domain.testdata.PersonData.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.lenient;

public class TestWithDummyData extends BaseTest {
    @BeforeEach
    public void setup() {
        initDummyPersonData();
        initUniqueConstraints();
        lenient().when(dataSerializer.deserialize(eq(tableName()))).thenReturn(DATA_MAP.get(tableName()));
        lenient().when(uniqueConstraintsSetSerializer.deserialize(eq(tableName())))
                .thenReturn(UNIQUE_MAP.get(tableName()));
    }

    protected void initDummyPersonData() {
        Metadata metadata = PersonData.metadata();
        String tableName = metadata.tableName();
        METADATA_MAP.putIfAbsent(tableName, metadata);
        DATA_MAP.putIfAbsent(tableName, dummyPersonData());
    }

    protected void initUniqueConstraints() {
        UniqueConstraintsSet uniqueConstraintsSet = UniqueConstraintsSet.empty();
        initIdUniqueConstraint(uniqueConstraintsSet);
        initNameSurnameUniqueConstraint(uniqueConstraintsSet);
        UNIQUE_MAP.putIfAbsent(tableName(), uniqueConstraintsSet);
    }

    protected void initIdUniqueConstraint(UniqueConstraintsSet uniqueConstraintsSet) {
        UniqueConstraint idUniqueConstraint = UniqueConstraint.of(idColumn());
        uniqueConstraintsSet = uniqueConstraintsSet.add(idUniqueConstraint);
        for (int i = 1; i <= 17; i++) {
            uniqueConstraintsSet.merge(idUniqueConstraint, UniqueValues.single(i));
        }
    }

    protected void initNameSurnameUniqueConstraint(UniqueConstraintsSet uniqueConstraintsSet) {
        UniqueConstraint nameSurnameUniqueConstraint = UniqueConstraint
                .of(nameColumn()).add(surnameColumn());
        uniqueConstraintsSet = uniqueConstraintsSet.add(nameSurnameUniqueConstraint);
        uniqueConstraintsSet.merge(nameSurnameUniqueConstraint, UniqueValues.bunch("a", "ayan"));
        uniqueConstraintsSet.merge(nameSurnameUniqueConstraint, UniqueValues.bunch("b", "byev"));
        uniqueConstraintsSet.merge(nameSurnameUniqueConstraint, UniqueValues.bunch("c", "cyan"));
        uniqueConstraintsSet.merge(nameSurnameUniqueConstraint, UniqueValues.bunch("d", "dovski"));
        uniqueConstraintsSet.merge(nameSurnameUniqueConstraint, UniqueValues.bunch("e", "eyan"));
        uniqueConstraintsSet.merge(nameSurnameUniqueConstraint, UniqueValues.bunch("f", "fyev"));
        uniqueConstraintsSet.merge(nameSurnameUniqueConstraint, UniqueValues.bunch("g", "gyan"));
        uniqueConstraintsSet.merge(nameSurnameUniqueConstraint, UniqueValues.bunch("h", "hyan"));
        uniqueConstraintsSet.merge(nameSurnameUniqueConstraint, UniqueValues.bunch("i", "iyan"));
        uniqueConstraintsSet.merge(nameSurnameUniqueConstraint, UniqueValues.bunch("j", "jovski"));
        uniqueConstraintsSet.merge(nameSurnameUniqueConstraint, UniqueValues.bunch("k", "kyan"));
        uniqueConstraintsSet.merge(nameSurnameUniqueConstraint, UniqueValues.bunch("l", "lovski"));
        uniqueConstraintsSet.merge(nameSurnameUniqueConstraint, UniqueValues.bunch("m", "myan"));
        uniqueConstraintsSet.merge(nameSurnameUniqueConstraint, UniqueValues.bunch("n", "nyan"));
        uniqueConstraintsSet.merge(nameSurnameUniqueConstraint, UniqueValues.bunch("o", "oyan"));
        uniqueConstraintsSet.merge(nameSurnameUniqueConstraint, UniqueValues.bunch("p", "povski"));
        uniqueConstraintsSet.merge(nameSurnameUniqueConstraint, UniqueValues.bunch("q", "qunknown"));
    }

    protected Data dummyPersonData() {
        return PersonData.someData(dummyPersonRows());
    }

    protected List<Row> dummyPersonRows() {
        return List.of(
                PersonData.aRow("1", "a", "ayan", "Armenia", "20", "true"),
                PersonData.aRow("2", "b", "byev", "Poland", "21", "false"),
                PersonData.aRow("3", "c", "cyan", "Armenia", "22", "true"),
                PersonData.aRow("4", "d", "dovski", "USA", "23", "true"),
                PersonData.aRow("5", "e", "eyan", "Armenia", "20", "true"),
                PersonData.aRow("6", "f", "fyev", "Poland", "21", "true"),
                PersonData.aRow("7", "g", "gyan", "Armenia", "22", "false"),
                PersonData.aRow("8", "h", "hyan", "Armenia", "23", "false"),
                PersonData.aRow("9", "i", "iyan", "Armenia", "20", "false"),
                PersonData.aRow("10", "j", "jovski", "USA", "21", "false"),
                PersonData.aRow("11", "k", "kyan", "Armenia", "22", "true"),
                PersonData.aRow("12", "l", "lovski", "USA", "23", "false"),
                PersonData.aRow("13", "m", "myan", "Armenia", "24", "true"),
                PersonData.aRow("14", "n", "nyan", "Armenia", "20", "false"),
                PersonData.aRow("15", "o", "oyan", "Armenia", "21", "true"),
                PersonData.aRow("16", "p", "povski", "USA", "21", "false"),
                PersonData.aRow("17", "q", "qunknown", null, "66", "false")
        );
    }
}

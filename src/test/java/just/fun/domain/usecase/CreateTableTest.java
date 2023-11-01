package just.fun.domain.usecase;

import just.fun.domain.response.Response;
import just.fun.domain.response.Status;
import just.fun.domain.schema.Metadata;
import just.fun.domain.schema.unique.UniqueConstraint;
import just.fun.domain.schema.unique.UniqueConstraintsSet;
import org.junit.jupiter.api.Test;

import java.util.List;

import static just.fun.domain.testdata.PersonData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doAnswer;

public class CreateTableTest extends BaseTest {

    @Test
    public void createTable() {
        Metadata metadata = metadata();
        String tableName = tableName();
        UniqueConstraint uniqueConstraint = UniqueConstraint.of(idColumn());
        UniqueConstraintsSet uniqueConstraintsSet = UniqueConstraintsSet.single(uniqueConstraint);

        doAnswer(invocation -> METADATA_MAP.put(tableName, metadata))
                .when(metadataSerializer).serialize(eq(tableName), eq(metadata));
        doAnswer(invocation -> UNIQUE_MAP.put(tableName, uniqueConstraintsSet))
                .when(uniqueConstraintsSetSerializer).serialize(eq(tableName), eq(uniqueConstraintsSet));

        CreateTable createTable =
                new CreateTable(tableName,
                        metadata,
                        metadataSerializer,
                        List.of(uniqueConstraint),
                        uniqueConstraintsSetSerializer);
        Response response = createTable.run();

        assertEquals(response.getStatus(), Status.OK);
        assertTrue(METADATA_MAP.containsKey(tableName));
        assertEquals(METADATA_MAP.get(tableName), metadata);
        assertEquals(UNIQUE_MAP.get(tableName), uniqueConstraintsSet);
    }

}

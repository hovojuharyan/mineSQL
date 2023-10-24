package just.fun.domain.usecase;

import just.fun.domain.response.Response;
import just.fun.domain.response.Status;
import just.fun.domain.schema.Metadata;
import just.fun.domain.testdata.PersonData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doAnswer;

public class CreateTableTest extends BaseTest {

    @Test
    public void createTable() {
        Metadata metadata = PersonData.metadata();
        String tableName = metadata.tableName();

        doAnswer(invocation -> METADATA_MAP.put(tableName, metadata))
                .when(metadataSerializer).serialize(eq(tableName), eq(metadata));

        CreateTable createTable = new CreateTable(tableName, metadata, metadataSerializer);
        Response response = createTable.run();

        assertEquals(response.getStatus(), Status.OK);
        assertTrue(METADATA_MAP.containsKey(tableName));
        assertEquals(METADATA_MAP.get(tableName), metadata);
    }

}

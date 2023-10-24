package just.fun.domain.usecase;

import just.fun.domain.response.Response;
import just.fun.domain.response.Status;
import just.fun.domain.schema.Metadata;
import just.fun.domain.testdata.PersonData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doAnswer;

public class DropTableTest extends BaseTest {

    @Test
    public void drop() {
        Metadata metadata = PersonData.metadata();
        String tableName = metadata.tableName();

        METADATA_MAP.put(tableName, metadata);
        doAnswer(invocation -> METADATA_MAP.remove(tableName)).when(dropper).drop(eq(tableName));

        DropTable dropTable = new DropTable(tableName, dropper);
        Response response = dropTable.run();

        assertEquals(response.getStatus(), Status.OK);
        assertNull(METADATA_MAP.get(tableName));
    }

}

package just.fun.domain.usecase;

import just.fun.domain.schema.Data;
import just.fun.domain.schema.Metadata;
import just.fun.domain.schema.Row;
import just.fun.serialization.DataSerializer;
import just.fun.serialization.Dropper;
import just.fun.serialization.MetadataSerializer;
import just.fun.serialization.RowSerializer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
public class BaseTest {

    protected static final Map<String, Metadata> METADATA_MAP = new HashMap<>();
    protected static final Map<String, Data> DATA_MAP = new HashMap<>();

    @Mock
    protected MetadataSerializer metadataSerializer;
    @Mock
    protected DataSerializer dataSerializer;
    @Mock
    protected RowSerializer rowSerializer;
    @Mock
    protected Dropper dropper;

    @BeforeEach
    public void setUp() {
        METADATA_MAP.clear();
        DATA_MAP.clear();
    }

    protected void insertRow(String tableName, Row row) {
        Data data = DATA_MAP.get(tableName);
        data.getRows().add(row);
    }
}

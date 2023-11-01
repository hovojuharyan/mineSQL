package just.fun.domain.usecase;

import just.fun.domain.schema.Data;
import just.fun.domain.schema.Metadata;
import just.fun.domain.schema.unique.UniqueConstraintsSet;
import just.fun.serialization.*;
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
    protected static final Map<String, UniqueConstraintsSet> UNIQUE_MAP = new HashMap<>();

    @Mock
    protected MetadataSerializer metadataSerializer;
    @Mock
    protected DataSerializer dataSerializer;
    @Mock
    protected RowSerializer rowSerializer;
    @Mock
    protected Dropper dropper;
    @Mock
    protected UniqueConstraintsSetSerializer uniqueConstraintsSetSerializer;

    @BeforeEach
    public void setUp() {
        METADATA_MAP.clear();
        DATA_MAP.clear();
        UNIQUE_MAP.clear();
    }
}

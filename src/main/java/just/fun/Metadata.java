package just.fun;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Metadata implements Persistable {
    private final Map<String, ColumnType> columns;

    private Metadata() {
        columns = new HashMap<>();
    }

    @Override
    public String getContent() {
        return columns.entrySet().stream()
                .map(e -> e.getKey() + "-" + e.getValue())
                .collect(Collectors.joining("|"));
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final Metadata metadata;

        public Builder() {
            metadata = new Metadata();
        }

        public Builder aColumn(String name, ColumnType type) {
            metadata.columns.put(name, type);
            return this;
        }

        public Metadata build() {
            return metadata;
        }
    }
}

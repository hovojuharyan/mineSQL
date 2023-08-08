package just.fun;

import java.util.ArrayList;
import java.util.List;

public class Data implements Persistable {

    private final List<String> rows;

    private Data() {
        rows = new ArrayList<>();
    }

    @Override
    public String getContent() {
        return String.join("\n", rows);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final Data data;

        Builder() {
            data = new Data();
        }

        public Builder aRow(String row) {
            data.rows.add(row);
            return this;
        }

        public Data build() {
            return data;
        }
    }
}

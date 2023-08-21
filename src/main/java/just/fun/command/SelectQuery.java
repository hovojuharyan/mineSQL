package just.fun.command;

import just.fun.domain.schema.Columns;
import just.fun.domain.schema.Conditions;
import just.fun.serialization.DataSerializer;

import java.util.Objects;

public final class SelectQuery {
    private DataSerializer dataDeserializer;
    private Columns queriedColumns;
    private Conditions conditions;

    private SelectQuery() {
    }

    public DataSerializer dataDeserializer() {
        return dataDeserializer;
    }

    public Columns queriedColumns() {
        return queriedColumns;
    }

    public Conditions conditions() {
        return conditions;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (SelectQuery) obj;
        return Objects.equals(this.dataDeserializer, that.dataDeserializer) &&
                Objects.equals(this.queriedColumns, that.queriedColumns) &&
                Objects.equals(this.conditions, that.conditions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dataDeserializer, queriedColumns, conditions);
    }

    @Override
    public String toString() {
        return "SelectQuery[" +
                "dataDeserializer=" + dataDeserializer + ", " +
                "queriedColumns=" + queriedColumns + ", " +
                "conditions=" + conditions + ']';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder implements CommandBuilder<SelectQuery> {
        private final SelectQuery selectQuery;

        private Builder() {
            this.selectQuery = new SelectQuery();
        }

        public Builder dataDeserializer(DataSerializer dataDeserializer) {
            selectQuery.dataDeserializer = dataDeserializer;
            return this;
        }

        public Builder columns(Columns columns) {
            selectQuery.queriedColumns = columns;
            return this;
        }

        public Builder conditions(Conditions conditions) {
            selectQuery.conditions = conditions;
            return this;
        }

        public SelectQuery build() {
            return selectQuery;
        }
    }
}

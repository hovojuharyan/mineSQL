package just.fun.command;

import just.fun.domain.schema.Row;
import just.fun.serialization.RowSerializer;
import just.fun.serialization.Serializer;

import java.util.Objects;

public final class InsertCommand {
    private Row row;
    private RowSerializer rowSerializer;

    private InsertCommand() {
    }

    public Row row() {
        return row;
    }

    public RowSerializer rowSerializer() {
        return rowSerializer;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (InsertCommand) obj;
        return Objects.equals(this.row, that.row) &&
                Objects.equals(this.rowSerializer, that.rowSerializer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, rowSerializer);
    }

    @Override
    public String toString() {
        return "InsertCommand[" +
                "row=" + row + ", " +
                "rowSerializer=" + rowSerializer + ']';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder implements CommandBuilder<InsertCommand> {
        private final InsertCommand insertCommand;

        private Builder() {
            insertCommand = new InsertCommand();
        }

        public Builder row(Row row) {
            insertCommand.row = row;
            return this;
        }

        public Builder rowSerializer(RowSerializer rowSerializer) {
            insertCommand.rowSerializer = rowSerializer;
            return this;
        }

        public InsertCommand build() {
            return insertCommand;
        }
    }

}
